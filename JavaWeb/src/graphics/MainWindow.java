package graphics;

import graphics.renderer.GameRenderer;
import scene.SceneSystem;

import java.awt.image.BufferedImage;

/**
 * Главное окно приложения, отвечающее за рендеринг кадров.
 * <p>
 * Связывает систему сцены с рендерером и предоставляет интерфейс
 * для получения текущего кадра и запроса перерисовки.
 *
 * @author Дунин Михаил Сергеевич
 * @version 1.0
 */
public class MainWindow {
    private final GameRenderer gameRenderer;
    private final int width;
    private final int height;

    /**
     * Создает главное окно с указанными параметрами.
     *
     * @param sceneSystem система сцены для рендеринга
     * @param width ширина окна в пикселях
     * @param height высота окна в пикселях
     */
    public MainWindow(SceneSystem sceneSystem, int width, int height) {
        this.gameRenderer = new GameRenderer(sceneSystem);
        this.width = width;
        this.height = height;
    }

    /**
     * Запрашивает перерисовку окна.
     * <p>
     * Вызывает рендеринг нового кадра через {@link GameRenderer}.
     */
    public void repaint() {
        gameRenderer.renderFrame(width, height);
    }

    /**
     * Возвращает текущий отрендеренный кадр.
     *
     * @return изображение текущего кадра или {@code null}, если рендеринг не выполнен
     */
    public BufferedImage getCurrentFrame() {
        return gameRenderer.getCurrentFrame();
    }
}
