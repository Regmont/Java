package model;

/**
 * Вспомогательный класс для представления заказа кофе.
 */
public class CoffeeOrder {
    private final int id;
    private String coffeeName;
    private int quantity;
    private String customer;

    public CoffeeOrder(int id, String coffeeName, int quantity, String customer) {
        this.id = id;
        this.coffeeName = coffeeName;
        this.quantity = quantity;
        this.customer = customer;
    }

    public int getId() { return id; }
    public String getCoffeeName() { return coffeeName; }
    public void setCoffeeName(String coffeeName) { this.coffeeName = coffeeName; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public String getCustomer() { return customer; }
    public void setCustomer(String customer) { this.customer = customer; }
}
