package graphics;

import graphics.config.AppConfig;
import scene.SceneSystem;

/**
 * Система рендеринга, управляющая главным окном.
 * <p>
 * Координирует создание и обновление окна рендеринга,
 * предоставляет интерфейс для запроса перерисовки сцены.
 *
 * @author Дунин Михаил Сергеевич
 * @version 1.0
 */
public class RenderingSystem {
    private final MainWindow window;

    /**
     * Создает систему рендеринга с указанной сценой.
     * <p>
     * Инициализирует главное окно с размерами из {@link AppConfig}.
     *
     * @param sceneSystem система сцены для отображения
     */
    public RenderingSystem(SceneSystem sceneSystem) {
        window = new MainWindow(sceneSystem, AppConfig.WINDOW_WIDTH, AppConfig.WINDOW_HEIGHT);
    }

    /**
     * Возвращает главное окно рендеринга.
     *
     * @return экземпляр {@link MainWindow}
     */
    public MainWindow getWindow() {
        return window;
    }

    /**
     * Запрашивает перерисовку сцены в окне.
     * <p>
     * Вызывает {@link MainWindow#repaint()} для обновления изображения.
     */
    public void requestRepaint() {
        window.repaint();
    }
}
