package model;

/**
 * Класс представляет продукт (напиток или десерт) в конкретной кофейне.
 */
public class ShopProduct {
    private final int coffeeShopId;
    private final int productId;
    private final String name;
    private final String type;
    private final double price;
    private boolean available;

    public ShopProduct(int coffeeShopId, int productId, String name,
                       String type, double price) {
        this.coffeeShopId = coffeeShopId;
        this.productId = productId;
        this.name = name;
        this.type = type;
        this.price = price;
        this.available = true;
    }

    public int getCoffeeShopId() { return coffeeShopId; }
    public int getProductId() { return productId; }
    public String getName() { return name; }
    public String getType() { return type; }
    public double getPrice() { return price; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    @Override
    public String toString() {
        return name + " (" + type + ") - " + price + " руб. " +
                (available ? "[в наличии]" : "[не доступен]");
    }
}
