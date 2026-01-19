package database;

import model.CoffeeOrder;
import model.CoffeeType;
import model.DessertOrder;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс представляет модель базы данных "Кофейня".
 * Использует коллекцию Map для хранения данных о заказах, десертах, графике работы и видах кофе.
 * Автор: Дунин Михаил
 */
public class CoffeeShopDatabase {

    private final Map<Integer, CoffeeOrder> coffeeOrders;
    private final Map<Integer, DessertOrder> dessertOrders;
    private final Map<String, String> workSchedule;
    private final Map<Integer, CoffeeType> coffeeTypes;
    private int coffeeOrderIdCounter;
    private int dessertOrderIdCounter;
    private int coffeeTypeIdCounter;

    /**
     * Конструктор инициализирует все коллекции и счётчики идентификаторов.
     */
    public CoffeeShopDatabase() {
        coffeeOrders = new HashMap<>();
        dessertOrders = new HashMap<>();
        workSchedule = new HashMap<>();
        coffeeTypes = new HashMap<>();
        coffeeOrderIdCounter = 1;
        dessertOrderIdCounter = 1;
        coffeeTypeIdCounter = 1;
    }

    /**
     * Добавляет новый заказ кофе в базу данных.
     *
     * @param coffeeName название кофе
     * @param quantity   количество порций
     * @param customer   имя заказчика
     * @return идентификатор созданного заказа
     */
    public int addCoffeeOrder(String coffeeName, int quantity, String customer) {
        int id = coffeeOrderIdCounter++;
        coffeeOrders.put(id, new CoffeeOrder(id, coffeeName, quantity, customer));
        return id;
    }

    /**
     * Добавляет новый заказ десерта в базу данных.
     *
     * @param dessertName название десерта
     * @param quantity    количество порций
     * @param customer    имя заказчика
     * @return идентификатор созданного заказа
     */
    public int addDessertOrder(String dessertName, int quantity, String customer) {
        int id = dessertOrderIdCounter++;
        dessertOrders.put(id, new DessertOrder(id, dessertName, quantity, customer));
        return id;
    }

    /**
     * Добавляет или обновляет график работы на указанный день недели.
     *
     * @param dayOfWeek день недели (например, "Понедельник")
     * @param schedule  строка с расписанием (например, "09:00-18:00")
     */
    public void addOrUpdateWorkSchedule(String dayOfWeek, String schedule) {
        workSchedule.put(dayOfWeek, schedule);
    }

    /**
     * Добавляет новый вид кофе в базу данных.
     *
     * @param name        название вида кофе
     * @param description описание
     * @param price       цена за порцию
     * @return идентификатор созданного вида кофе
     */
    public int addCoffeeType(String name, String description, double price) {
        int id = coffeeTypeIdCounter++;
        coffeeTypes.put(id, new CoffeeType(id, name, description, price));
        return id;
    }

    /**
     * Изменяет расписание работы на указанный день недели.
     *
     * @param dayOfWeek день недели (например, "Вторник")
     * @param newSchedule новое расписание
     */
    public void updateWorkSchedule(String dayOfWeek, String newSchedule) {
        workSchedule.put(dayOfWeek, newSchedule);
    }

    /**
     * Изменяет название существующего вида кофе.
     *
     * @param coffeeTypeId идентификатор вида кофе
     * @param newName      новое название
     * @throws IllegalArgumentException если вид кофе с указанным ID не найден
     */
    public void updateCoffeeTypeName(int coffeeTypeId, String newName) {
        CoffeeType coffeeType = coffeeTypes.get(coffeeTypeId);
        if (coffeeType == null) {
            throw new IllegalArgumentException("Вид кофе с ID " + coffeeTypeId + " не найден.");
        }
        coffeeType.setName(newName);
    }

    /**
     * Обновляет информацию в существующем заказе кофе.
     *
     * @param orderId     идентификатор заказа
     * @param coffeeName  новое название кофе (может быть null, если не нужно менять)
     * @param quantity    новое количество (может быть -1, если не нужно менять)
     * @param customer    новое имя заказчика (может быть null, если не нужно менять)
     * @throws IllegalArgumentException если заказ с указанным ID не найден
     */
    public void updateCoffeeOrder(int orderId, String coffeeName, int quantity, String customer) {
        CoffeeOrder order = coffeeOrders.get(orderId);
        if (order == null) {
            throw new IllegalArgumentException("Заказ кофе с ID " + orderId + " не найден.");
        }
        if (coffeeName != null) order.setCoffeeName(coffeeName);
        if (quantity >= 0) order.setQuantity(quantity);
        if (customer != null) order.setCustomer(customer);
    }

    /**
     * Изменяет название существующего десерта во всех заказах.
     * В данной модели десерты хранятся только в заказах, поэтому изменение затрагивает все заказы.
     *
     * @param oldDessertName текущее название десерта
     * @param newDessertName новое название десерта
     */
    public void updateDessertName(String oldDessertName, String newDessertName) {
        for (DessertOrder order : dessertOrders.values()) {
            if (order.getDessertName().equals(oldDessertName)) {
                order.setDessertName(newDessertName);
            }
        }
    }

    public Map<Integer, CoffeeOrder> getCoffeeOrders() {
        return new HashMap<>(coffeeOrders);
    }

    public Map<Integer, DessertOrder> getDessertOrders() {
        return new HashMap<>(dessertOrders);
    }

    public Map<String, String> getWorkSchedule() {
        return new HashMap<>(workSchedule);
    }

    public Map<Integer, CoffeeType> getCoffeeTypes() {
        return new HashMap<>(coffeeTypes);
    }
}
