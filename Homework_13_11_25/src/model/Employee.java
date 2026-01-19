package model;

/**
 * Класс представляет сотрудника кофейни.
 */
public class Employee {
    private final int id;
    private final String name;
    private final String position;
    private int coffeeShopId;

    public Employee(int id, String name, String position, int coffeeShopId) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.coffeeShopId = coffeeShopId;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getPosition() { return position; }
    public int getCoffeeShopId() { return coffeeShopId; }
    public void setCoffeeShopId(int coffeeShopId) { this.coffeeShopId = coffeeShopId; }

    @Override
    public String toString() {
        return name + " (" + position + ")";
    }
}
