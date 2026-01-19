package game;

import game.configuration.GameConfig;
import game.input.InputSystem;
import core.math.Vector3D;
import graphics.RenderingSystem;
import scene.SceneSystem;
import scene.gameObjects.Camera;

import java.util.Arrays;

/**
 * Контроллер игрового цикла, обрабатывающий ввод и обновление состояния.
 * <p>
 * Управляет движением камеры, обработкой пользовательского ввода
 * и синхронизацией с системой рендеринга. Реализует механизм таймаута
 * для автоматического сброса состояний клавиш при потере HTTP-запросов.
 *
 * @author Дунин Михаил Сергеевич
 * @version 1.1
 */
public class GameController {
    private final double FRAME_TIME_MS = 1000.0 / GameConfig.TARGET_FPS;
    private final RenderingSystem renderingSystem;
    private final Camera camera;
    private final InputSystem inputSystem;
    private volatile boolean running;
    private Thread gameThread;
    private boolean cameraMoved = false;

    /**
     * Время последнего нажатия для каждой клавиши движения (W, A, S, D).
     * Используется для реализации таймаута автоматического сброса.
     */
    private final long[] keyPressTime = new long[4];

    /**
     * Таймаут в миллисекундах для автоматического сброса состояния клавиши.
     * Если клавиша находится в состоянии "нажата" дольше этого времени
     * без получения события keyup, она автоматически сбрасывается.
     * Это предотвращает "залипание" клавиш при потере HTTP-запросов.
     */
    private static final long KEY_TIMEOUT_MS = 200;

    /**
     * Индексы клавиш в массиве keyPressTime.
     */
    private static final int KEY_W = 0;
    private static final int KEY_A = 1;
    private static final int KEY_S = 2;
    private static final int KEY_D = 3;

    /**
     * Создает контроллер игры с указанной сценой и системой рендеринга.
     *
     * @param sceneSystem система сцены с объектами и камерой
     * @param renderingSystem система рендеринга для отображения сцены
     */
    public GameController(SceneSystem sceneSystem, RenderingSystem renderingSystem) {
        this.renderingSystem = renderingSystem;
        this.camera = sceneSystem.getCamera();
        this.inputSystem = new InputSystem();

        Arrays.fill(keyPressTime, 0);
    }

    /**
     * Обрабатывает нажатие клавиши.
     * <p>
     * Обновляет время последнего нажатия для соответствующей клавиши.
     *
     * @param key идентификатор клавиши (например, "w", "a", "s", "d", "escape")
     */
    public void keyDown(String key) {
        long currentTime = System.currentTimeMillis();

        switch (key) {
            case "w" -> {
                inputSystem.getKeyboard().setWPressed(true);
                keyPressTime[KEY_W] = currentTime;
            }
            case "a" -> {
                inputSystem.getKeyboard().setAPressed(true);
                keyPressTime[KEY_A] = currentTime;
            }
            case "s" -> {
                inputSystem.getKeyboard().setSPressed(true);
                keyPressTime[KEY_S] = currentTime;
            }
            case "d" -> {
                inputSystem.getKeyboard().setDPressed(true);
                keyPressTime[KEY_D] = currentTime;
            }
            case "escape" -> inputSystem.getKeyboard().setEscapePressed(true);
        }
    }

    /**
     * Обрабатывает отпускание клавиши.
     * <p>
     * Сбрасывает состояние соответствующей клавиши движения.
     * Escape не обрабатывается, так как является одноразовым событием.
     *
     * @param key идентификатор клавиши в нижнем регистре ("w", "a", "s", "d")
     */
    public void keyUp(String key) {
        switch (key) {
            case "w" -> inputSystem.getKeyboard().setWPressed(false);
            case "a" -> inputSystem.getKeyboard().setAPressed(false);
            case "s" -> inputSystem.getKeyboard().setSPressed(false);
            case "d" -> inputSystem.getKeyboard().setDPressed(false);
        }
    }

    /**
     * Обрабатывает перемещение мыши.
     *
     * @param dx смещение по оси X в пикселях
     * @param dy смещение по оси Y в пикселях
     */
    public void mouseMove(int dx, int dy) {
        inputSystem.handleMouseMove(dx, dy);
    }

    /**
     * Обрабатывает нажатие кнопки мыши.
     */
    public void mouseDown() {
        inputSystem.handleMouseDown();
    }

    /**
     * Обрабатывает отпускание кнопки мыши.
     */
    public void mouseUp() {
        inputSystem.handleMouseUp();
    }

    /**
     * Запускает игровой цикл в отдельном потоке.
     */
    public void startGameLoop() {
        running = true;
        gameThread = new Thread(this::gameLoop, "Game-Thread");
        gameThread.start();
    }

    /**
     * Останавливает игровой цикл и ожидает завершения потока.
     */
    public void stopGameLoop() {
        running = false;
        if (gameThread != null) {
            try {
                gameThread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * Основной игровой цикл с фиксированным временным шагом.
     * <p>
     * Реализует паттерн "фиксированный временной шаг" для стабильного обновления физики.
     * Обрабатывает синхронизацию кадров и запросы перерисовки при движении камеры.
     * Включает проверку таймаута клавиш для предотвращения "залипания".
     */
    private void gameLoop() {
        long lastTime = System.nanoTime();
        double accumulator = 0;

        while (running) {
            long currentTime = System.nanoTime();
            double elapsedMs = (currentTime - lastTime) / 1_000_000.0;
            lastTime = currentTime;

            if (elapsedMs > GameConfig.MAX_FRAME_TIME_MS) {
                elapsedMs = GameConfig.MAX_FRAME_TIME_MS;
            }

            accumulator += elapsedMs;

            while (accumulator >= FRAME_TIME_MS) {
                update(FRAME_TIME_MS / 1000.0);
                accumulator -= FRAME_TIME_MS;
            }

            if (cameraMoved) {
                renderingSystem.requestRepaint();
                cameraMoved = false;
            }

            long sleepTimeMs = (long)(FRAME_TIME_MS - (System.nanoTime() - lastTime) / 1_000_000.0);
            if (sleepTimeMs > 0) {
                try {
                    Thread.sleep(sleepTimeMs);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    running = false;
                }
            }
        }
    }

    /**
     * Обновляет состояние игры за один временной шаг.
     * <p>
     * Обрабатывает ввод пользователя, вращение и перемещение камеры.
     * Включает проверку таймаута для клавиш движения.
     *
     * @param deltaTime время, прошедшее с предыдущего обновления (в секундах)
     */
    private void update(double deltaTime) {
        // Проверяем таймаут клавиш перед обработкой ввода
        checkKeyTimeouts();

        if (inputSystem.getKeyboard().isEscapePressed()) {
            if (inputSystem.getMouse().isCaptured()) {
                inputSystem.getMouse().release();
            }
            inputSystem.getKeyboard().resetEscape();
        }

        if (inputSystem.getMouse().isCaptured()) {
            int deltaX = inputSystem.getMouse().getDeltaX();
            int deltaY = inputSystem.getMouse().getDeltaY();

            if (deltaX != 0 || deltaY != 0) {
                double deltaYaw = -deltaX * GameConfig.MOUSE_SENSITIVITY;
                double deltaPitch = deltaY * GameConfig.MOUSE_SENSITIVITY;

                double newPitch = camera.getTransform().getRotation().x() + deltaPitch;
                double newYaw = camera.getTransform().getRotation().y() + deltaYaw;

                newPitch = Math.max(GameConfig.MIN_PITCH, Math.min(GameConfig.MAX_PITCH, newPitch));

                camera.getTransform().setRotation(new Vector3D(newPitch, newYaw,
                        camera.getTransform().getRotation().z()));
                cameraMoved = true;
            }
        }

        if (inputSystem.getKeyboard().isAnyMovementPressed()) {
            updateCameraPosition(deltaTime);
            cameraMoved = true;
        }

        inputSystem.update();
    }

    /**
     * Проверяет таймаут для клавиш движения.
     * <p>
     * Если клавиша находится в состоянии "нажата" дольше KEY_TIMEOUT_MS
     * без получения события keyup, она автоматически сбрасывается.
     * Это предотвращает "залипание" клавиш при потере HTTP-запросов.
     */
    private void checkKeyTimeouts() {
        long currentTime = System.currentTimeMillis();

        // Проверяем клавишу W
        if (inputSystem.getKeyboard().isWPressed() &&
                currentTime - keyPressTime[KEY_W] > KEY_TIMEOUT_MS) {
            inputSystem.getKeyboard().setWPressed(false);
        }

        // Проверяем клавишу A
        if (inputSystem.getKeyboard().isAPressed() &&
                currentTime - keyPressTime[KEY_A] > KEY_TIMEOUT_MS) {
            inputSystem.getKeyboard().setAPressed(false);
        }

        // Проверяем клавишу S
        if (inputSystem.getKeyboard().isSPressed() &&
                currentTime - keyPressTime[KEY_S] > KEY_TIMEOUT_MS) {
            inputSystem.getKeyboard().setSPressed(false);
        }

        // Проверяем клавишу D
        if (inputSystem.getKeyboard().isDPressed() &&
                currentTime - keyPressTime[KEY_D] > KEY_TIMEOUT_MS) {
            inputSystem.getKeyboard().setDPressed(false);
        }
    }

    /**
     * Обновляет позицию камеры на основе ввода с клавиатуры.
     * <p>
     * Вычисляет смещение вперед/назад и вправо/влево с учетом текущего направления камеры.
     * Применяет нормализацию для диагонального движения.
     *
     * @param deltaTime время, прошедшее с предыдущего обновления (в секундах)
     */
    private void updateCameraPosition(double deltaTime) {
        double forward = 0;
        double right = 0;

        if (inputSystem.getKeyboard().isWPressed()) forward += 1;
        if (inputSystem.getKeyboard().isSPressed()) forward -= 1;
        if (inputSystem.getKeyboard().isAPressed()) right += 1;
        if (inputSystem.getKeyboard().isDPressed()) right -= 1;

        double factor = Math.sqrt(2) / 2;
        if (forward != 0 && right != 0) {
            forward *= factor;
            right *= factor;
        }

        forward *= camera.getSpeed() * deltaTime;
        right *= camera.getSpeed() * deltaTime;

        moveCamera(forward, right);
    }

    /**
     * Перемещает камеру на указанное расстояние вперед и вправо.
     * <p>
     * Вычисляет новую позицию камеры с учетом текущего рысканья (yaw).
     *
     * @param forward расстояние для перемещения вперед (отрицательное - назад)
     * @param right расстояние для перемещения вправо (отрицательное - влево)
     */
    private void moveCamera(double forward, double right) {
        Vector3D pos = camera.getTransform().getPosition();
        double yaw = camera.getTransform().getRotation().y();

        double x = pos.x() + forward * Math.sin(yaw) + right * Math.sin(yaw + Math.PI / 2);
        double z = pos.z() + forward * Math.cos(yaw) + right * Math.cos(yaw + Math.PI / 2);

        camera.getTransform().setPosition(new Vector3D(x, pos.y(), z));
    }
}