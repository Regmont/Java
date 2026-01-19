package model;

/**
 * Вспомогательный класс для представления заказа десерта.
 */
public class DessertOrder {
    private final int id;
    private String dessertName;
    private int quantity;
    private String customer;

    public DessertOrder(int id, String dessertName, int quantity, String customer) {
        this.id = id;
        this.dessertName = dessertName;
        this.quantity = quantity;
        this.customer = customer;
    }

    public int getId() { return id; }
    public String getDessertName() { return dessertName; }
    public void setDessertName(String dessertName) { this.dessertName = dessertName; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public String getCustomer() { return customer; }
    public void setCustomer(String customer) { this.customer = customer; }
}
