package model;

/**
 * Вспомогательный класс для представления вида кофе.
 */
public class CoffeeType {
    private final int id;
    private String name;
    private final String description;
    private final double price;

    public CoffeeType(int id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
}
