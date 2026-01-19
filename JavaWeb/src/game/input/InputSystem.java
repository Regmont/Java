package game.input;

/**
 * Система обработки пользовательского ввода (клавиатура и мышь).
 * <p>
 * Координирует взаимодействие между внешними событиями ввода (от HTTP-сервера)
 * и внутренними компонентами управления камерой.
 * Разделяет обработку клавиатуры и мыши на отдельные компоненты.
 *
 * @author Дунин Михаил Сергеевич
 * @version 1.0
 */
public class InputSystem {
    private final Keyboard keyboard;
    private final Mouse mouse;

    /**
     * Создает систему ввода с инициализированными компонентами клавиатуры и мыши.
     */
    public InputSystem() {
        this.keyboard = new Keyboard();
        this.mouse = new Mouse();
    }

    /**
     * Обрабатывает событие нажатия клавиши.
     * <p>
     * Определяет, какая клавиша была нажата, и обновляет состояние клавиатуры.
     * Поддерживает клавиши WASD для движения и Escape для выхода.
     *
     * @param key идентификатор клавиши в нижнем регистре ("w", "a", "s", "d", "escape")
     */
    public void handleKeyDown(String key) {
        switch (key) {
            case "w" -> keyboard.setWPressed(true);
            case "a" -> keyboard.setAPressed(true);
            case "s" -> keyboard.setSPressed(true);
            case "d" -> keyboard.setDPressed(true);
            case "escape" -> keyboard.setEscapePressed(true);
        }
    }

    /**
     * Обрабатывает событие отпускания клавиши.
     * <p>
     * Сбрасывает состояние соответствующей клавиши движения.
     * Escape не обрабатывается, так как является одноразовым событием.
     *
     * @param key идентификатор клавиши в нижнем регистре ("w", "a", "s", "d")
     */
    public void handleKeyUp(String key) {
        switch (key) {
            case "w" -> keyboard.setWPressed(false);
            case "a" -> keyboard.setAPressed(false);
            case "s" -> keyboard.setSPressed(false);
            case "d" -> keyboard.setDPressed(false);
        }
    }

    /**
     * Обрабатывает перемещение мыши.
     * <p>
     * Аккумулирует смещение мыши только если мышь захвачена (зажата кнопка).
     * Смещения суммируются для сглаживания.
     *
     * @param dx смещение по оси X в пикселях (положительное - вправо)
     * @param dy смещение по оси Y в пикселях (положительное - вниз)
     */
    public void handleMouseMove(int dx, int dy) {
        if (mouse.captured) {
            mouse.deltaX += dx;
            mouse.deltaY += dy;
        }
    }

    /**
     * Обрабатывает нажатие кнопки мыши.
     * <p>
     * Захватывает управление мышью и сбрасывает накопленные смещения.
     * После вызова этого метода мышь начинает влиять на вращение камеры.
     */
    public void handleMouseDown() {
        mouse.captured = true;
        mouse.deltaX = 0;
        mouse.deltaY = 0;
        mouse.lastDeltaX = 0;
        mouse.lastDeltaY = 0;
    }

    /**
     * Обрабатывает отпускание кнопки мыши.
     * <p>
     * Освобождает управление мышью. Вращение камеры прекращается.
     */
    public void handleMouseUp() {
        mouse.captured = false;
    }

    public Keyboard getKeyboard() { return keyboard; }
    public Mouse getMouse() { return mouse; }

    /**
     * Обновляет внутреннее состояние системы ввода.
     * <p>
     * Вызывает обновление состояния мыши для применения сглаживания движения.
     * Должен вызываться каждый кадр в игровом цикле.
     */
    public void update() {
        mouse.update();
    }
}
