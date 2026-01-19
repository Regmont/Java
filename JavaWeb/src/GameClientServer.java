import core.math.Vector3D;
import game.GameController;
import graphics.RenderingSystem;
import scene.SceneSystem;
import scene.gameObjects.Camera;
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Главный класс сервера игры, совмещающий 3D-рендеринг и HTTP-сервер.
 * <p>
 * Запускает игровой цикл в отдельном потоке и предоставляет HTTP-интерфейс
 * для удаленного управления камерой и получения кадров рендера.
 * Поддерживает обработку клавиатуры, мыши и потоковую передачу изображений.
 *
 * @author Дунин Михаил Сергеевич
 * @version 1.0
 */
public class GameClientServer {
    private static GameController gameController;
    private static RenderingSystem renderingSystem;

    /**
     * Точка входа в приложение.
     * <p>
     * Инициализирует сцену, рендерер, игровой контроллер и HTTP-сервер.
     * Создает контексты для обработки запросов:
     * <ul>
     *   <li>/ - главная страница с управлением</li>
     *   <li>/frame - получение текущего кадра в base64</li>
     *   <li>/keydown, /keyup - обработка клавиш</li>
     *   <li>/mousedown, /mouseup, /mousemove - обработка мыши</li>
     *   <li>/escape - специальная клавиша выхода</li>
     * </ul>
     *
     * @param args аргументы командной строки (не используются)
     * @throws Exception если произошла ошибка при запуске сервера
     */
    public static void main(String[] args) throws Exception {
        Camera camera = new Camera(new Vector3D(0, 2, -10), Vector3D.zeroVector);

        SceneSystem sceneSystem = new SceneSystem();
        sceneSystem.setCamera(camera);
        sceneSystem.addObjectsToScene(SceneCreator.createObjects());
        sceneSystem.addPointLights(SceneCreator.createDefaultLight());

        renderingSystem = new RenderingSystem(sceneSystem);
        gameController = new GameController(sceneSystem, renderingSystem);

        new Thread(() -> gameController.startGameLoop()).start();

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/", exchange -> {
            try {
                byte[] htmlBytes = Files.readAllBytes(
                        Paths.get("src/resources/index.html")
                );

                exchange.getResponseHeaders().set("Content-Type", "text/html");
                exchange.sendResponseHeaders(200, htmlBytes.length);
                exchange.getResponseBody().write(htmlBytes);
            } catch (Exception e) {
                e.printStackTrace();
                String errorHtml = """
                    <html>
                    <body>
                        <h1>Error loading page</h1>
                        <p>Make sure index.html exists in src/resources/</p>
                        <p>Error: %s</p>
                    </body>
                    </html>
                    """.formatted(e.getMessage());

                exchange.getResponseHeaders().set("Content-Type", "text/html");
                exchange.sendResponseHeaders(500, errorHtml.getBytes().length);
                exchange.getResponseBody().write(errorHtml.getBytes());
            } finally {
                exchange.getResponseBody().close();
            }
        });

        server.createContext("/frame", exchange -> {
            if (renderingSystem != null && renderingSystem.getWindow() != null) {
                var frame = renderingSystem.getWindow().getCurrentFrame();

                if (frame != null) {
                    try (var baos = new java.io.ByteArrayOutputStream()) {
                        javax.imageio.ImageIO.write(frame, "png", baos);
                        String base64 = java.util.Base64.getEncoder()
                                .encodeToString(baos.toByteArray());

                        exchange.getResponseHeaders().set("Content-Type", "text/plain");
                        exchange.sendResponseHeaders(200, base64.getBytes().length);
                        exchange.getResponseBody().write(base64.getBytes());
                        exchange.getResponseBody().close();

                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            exchange.sendResponseHeaders(404, 0);
            exchange.getResponseBody().close();
        });

        server.createContext("/keydown", exchange -> {
            String query = exchange.getRequestURI().getQuery();

            if (query != null && query.startsWith("key=")) {
                String key = query.substring(4);
                gameController.keyDown(key);
            }

            exchange.sendResponseHeaders(200, 0);
            exchange.getResponseBody().close();
        });

        server.createContext("/keyup", exchange -> {
            String query = exchange.getRequestURI().getQuery();

            if (query != null && query.startsWith("key=")) {
                String key = query.substring(4);
                gameController.keyUp(key);
            }

            exchange.sendResponseHeaders(200, 0);
            exchange.getResponseBody().close();
        });

        server.createContext("/mousedown", exchange -> {
            gameController.mouseDown();
            exchange.sendResponseHeaders(200, 0);
            exchange.getResponseBody().close();
        });

        server.createContext("/mouseup", exchange -> {
            gameController.mouseUp();
            exchange.sendResponseHeaders(200, 0);
            exchange.getResponseBody().close();
        });

        server.createContext("/mousemove", exchange -> {
            String query = exchange.getRequestURI().getQuery();

            if (query != null) {
                String[] parts = query.split("&");
                int dx = 0, dy = 0;

                for (String part : parts) {
                    if (part.startsWith("dx=")) dx = Integer.parseInt(part.substring(3));
                    if (part.startsWith("dy=")) dy = Integer.parseInt(part.substring(3));
                }

                gameController.mouseMove(dx, dy);
            }

            exchange.sendResponseHeaders(200, 0);
            exchange.getResponseBody().close();
        });

        server.createContext("/escape", exchange -> {
            gameController.keyDown("escape");
            exchange.sendResponseHeaders(200, 0);
            exchange.getResponseBody().close();
        });

        server.start();
        System.out.println("Server started at http://localhost:8080");
    }
}
