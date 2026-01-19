package game.input;

import game.configuration.GameConfig;

/**
 * Компонент для отслеживания состояния мыши.
 * <p>
 * Обрабатывает захват мыши, накопление смещений и применение сглаживания.
 * Используется для управления вращением камеры в 3D -пространстве.
 *
 * @author Дунин Михаил Сергеевич
 * @version 1.0
 */
public class Mouse {
    boolean captured = false;
    int deltaX = 0;
    int deltaY = 0;
    int lastDeltaX = 0;
    int lastDeltaY = 0;

    /**
     * Обновляет состояние мыши, применяя сглаживание к накопленным смещениям.
     * <p>
     * Сохраняет текущие смещения в {@code lastDeltaX/Y} для использования в кадре,
     * затем уменьшает текущие смещения с коэффициентом сглаживания.
     * Должен вызываться каждый кадр.
     */
    public void update() {
        lastDeltaX = deltaX;
        lastDeltaY = deltaY;
        deltaX = (int)(deltaX * GameConfig.MOUSE_SMOOTHING_FACTOR);
        deltaY = (int)(deltaY * GameConfig.MOUSE_SMOOTHING_FACTOR);
    }

    public int getDeltaX() { return lastDeltaX; }
    public int getDeltaY() { return lastDeltaY; }

    /**
     * Освобождает захват мыши.
     * <p>
     * Прекращает накопление смещений мыши для вращения камеры.
     */
    public void release() { captured = false; }

    /**
     * Проверяет, захвачена ли мышь (зажата кнопка).
     *
     * @return {@code true} если мышь захвачена и влияет на вращение камеры
     */
    public boolean isCaptured() { return captured; }
}
