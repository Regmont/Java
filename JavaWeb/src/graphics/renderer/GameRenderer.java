package graphics.renderer;

import graphics.utils.ColorUtils;
import scene.SceneSystem;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

/**
 * Основной рендерер игры, управляющий буферами и кадрами.
 * <p>
 * Создает и обновляет цветовой и глубинный буферы,
 * координирует рендеринг сцены и преобразование в изображение.
 *
 * @author Дунин Михаил Сергеевич
 * @version 1.0
 */
public class GameRenderer {
    private Color[][] colorBuffer;
    private double[][] depthBuffer;
    private BufferedImage frame;
    private final SceneSystem sceneSystem;
    private int currentWidth = -1;
    private int currentHeight = -1;

    /**
     * Создает рендерер для указанной системы сцены.
     *
     * @param sceneSystem система сцены для рендеринга
     */
    public GameRenderer(SceneSystem sceneSystem) {
        this.sceneSystem = sceneSystem;
    }

    /**
     * Рендерит кадр с указанными размерами.
     * <p>
     * Если размеры изменились, пересоздает буферы и изображение.
     * Вызывает {@link MainRenderer} для рендеринга сцены.
     *
     * @param width ширина кадра в пикселях
     * @param height высота кадра в пикселях
     * @return отрендеренное изображение кадра
     */
    public BufferedImage renderFrame(int width, int height) {
        if (width != currentWidth || height != currentHeight) {
            colorBuffer = new Color[width][height];
            depthBuffer = new double[width][height];
            frame = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            currentWidth = width;
            currentHeight = height;
        }

        MainRenderer.renderScene(colorBuffer, depthBuffer,
                ColorUtils.getSkyColor(sceneSystem.getSky()), sceneSystem);

        copyColorBufferToImage(width, height);

        return frame;
    }

    /**
     * Возвращает текущий отрендеренный кадр.
     *
     * @return изображение последнего кадра или {@code null}, если рендеринг не выполнен
     */
    public BufferedImage getCurrentFrame() {
        return frame;
    }

    /**
     * Копирует содержимое цветового буфера в изображение.
     * <p>
     * Использует фоновый цвет неба для незаполненных пикселей.
     *
     * @param width ширина буфера
     * @param height высота буфера
     */
    private void copyColorBufferToImage(int width, int height) {
        int[] pixels = ((DataBufferInt) frame.getRaster().getDataBuffer()).getData();
        int backgroundRGB = ColorUtils.getSkyColor(sceneSystem.getSky()).getRGB();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = colorBuffer[x][y];
                pixels[y * width + x] = (color != null) ? color.getRGB() : backgroundRGB;
            }
        }
    }
}
