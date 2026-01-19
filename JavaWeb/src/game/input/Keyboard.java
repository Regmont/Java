package game.input;

/**
 * Компонент для отслеживания состояния клавиатуры.
 * <p>
 * Хранит текущее состояние клавиш движения (WASD) и специальной клавиши Escape.
 * Предоставляет методы для проверки нажатых клавиш, установки состояния и сброса.
 *
 * @author Дунин Михаил Сергеевич
 * @version 1.2
 */
public class Keyboard {
    private boolean wPressed = false;
    private boolean aPressed = false;
    private boolean sPressed = false;
    private boolean dPressed = false;
    private boolean escapePressed = false;

    public boolean isWPressed() { return wPressed; }
    public boolean isAPressed() { return aPressed; }
    public boolean isSPressed() { return sPressed; }
    public boolean isDPressed() { return dPressed; }
    public boolean isEscapePressed() { return escapePressed; }

    public void setWPressed(boolean pressed) { wPressed = pressed; }
    public void setAPressed(boolean pressed) { aPressed = pressed; }
    public void setSPressed(boolean pressed) { sPressed = pressed; }
    public void setDPressed(boolean pressed) { dPressed = pressed; }
    public void setEscapePressed(boolean pressed) { escapePressed = pressed; }

    /**
     * Сбрасывает состояние клавиши Escape.
     * <p>
     * Должен вызываться после обработки события Escape для предотвращения
     * повторной обработки в следующем кадре.
     */
    public void resetEscape() {
        escapePressed = false;
    }

    /**
     * Проверяет, нажата ли хотя бы одна клавиша движения.
     * <p>
     * Используется для определения необходимости обновления позиции камеры.
     * Клавиша Escape не считается клавишей движения.
     *
     * @return {@code true} если нажата W, A, S или D
     */
    public boolean isAnyMovementPressed() {
        return wPressed || aPressed || sPressed || dPressed;
    }
}