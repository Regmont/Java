import java.util.*;

/**
 * Класс для работы с коллекцией автомобилей.
 * Имитирует подключение к базе данных и предоставляет методы для работы с данными.
 */
public class CarDatabase {
    private final List<Car> cars;

    /**
     * Создает объект CarDatabase и инициализирует его тестовыми данными.
     */
    public CarDatabase() {
        this.cars = new ArrayList<>();
        initializeSampleData();
    }

    /**
     * Имитирует подключение к базе данных автомобилей.
     */
    public void connect() {
        System.out.println("Подключение к базе данных автомобилей...");
    }

    /**
     * Имитирует отключение от базы данных автомобилей.
     */
    public void disconnect() {
        System.out.println("Отключение от базы данных автомобилей...");
    }

    private void initializeSampleData() {
        cars.add(new Car("Toyota", "Camry", 2.5, 2020, "Black", "седан"));
        cars.add(new Car("Toyota", "Corolla", 1.8, 2021, "White", "седан"));
        cars.add(new Car("BMW", "X5", 3.0, 2022, "Blue", "универсал"));
        cars.add(new Car("BMW", "X3", 2.0, 2021, "Black", "хетчбек"));
        cars.add(new Car("Audi", "A4", 2.0, 2019, "Red", "седан"));
        cars.add(new Car("Toyota", "RAV4", 2.5, 2020, "Silver", "универсал"));
        cars.add(new Car("Mercedes", "C-Class", 2.0, 2021, "Black", "седан"));
    }

    /**
     * Отображает все автомобили в коллекции.
     */
    public void displayAllCars() {
        System.out.println("\nВсе автомобили:");
        for (Car car : cars) {
            System.out.println(car);
        }
    }

    /**
     * Отображает уникальных производителей автомобилей.
     */
    public void displayAllManufacturers() {
        Set<String> manufacturers = new HashSet<>();
        for (Car car : cars) {
            manufacturers.add(car.getManufacturer());
        }

        System.out.println("\nВсе производители:");
        for (String m : manufacturers) {
            System.out.println(m);
        }
    }

    /**
     * Отображает производителей и количество автомобилей каждого производителя.
     */
    public void displayManufacturersWithCount() {
        Map<String, Integer> manufacturerCount = new HashMap<>();
        for (Car car : cars) {
            manufacturerCount.put(
                    car.getManufacturer(),
                    manufacturerCount.getOrDefault(car.getManufacturer(), 0) + 1
            );
        }

        System.out.println("\nПроизводители и количество автомобилей:");
        for (Map.Entry<String, Integer> entry : manufacturerCount.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " авто");
        }
    }
}
