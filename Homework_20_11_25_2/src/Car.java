import java.util.Objects;

/**
 * Класс, представляющий автомобиль с базовыми характеристиками.
 */
public class Car {
    private String manufacturer;
    private String model;
    private double engineVolume;
    private int year;
    private String color;
    private String type;

    /**
     * Создает новый объект Car.
     *
     * @param manufacturer производитель автомобиля
     * @param model модель автомобиля
     * @param engineVolume объем двигателя в литрах
     * @param year год выпуска
     * @param color цвет автомобиля
     * @param type тип автомобиля
     */
    public Car(String manufacturer, String model, double engineVolume, int year, String color, String type) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.engineVolume = engineVolume;
        this.year = year;
        this.color = color;
        this.type = type;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getEngineVolume() {
        return engineVolume;
    }

    public void setEngineVolume(double engineVolume) {
        this.engineVolume = engineVolume;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("%s %s, %d, %.1fL, %s, %s",
                manufacturer, model, year, engineVolume, color, type);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Double.compare(car.engineVolume, engineVolume) == 0 &&
                year == car.year &&
                Objects.equals(manufacturer, car.manufacturer) &&
                Objects.equals(model, car.model) &&
                Objects.equals(color, car.color) &&
                Objects.equals(type, car.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(manufacturer, model, engineVolume, year, color, type);
    }
}
