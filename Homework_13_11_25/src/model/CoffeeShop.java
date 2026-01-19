package model;

/**
 * Класс представляет кофейню в сети.
 */
public class CoffeeShop {
    private final int id;
    private final String name;
    private final String address;
    private final String phone;

    public CoffeeShop(int id, String name, String address, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }

    @Override
    public String toString() {
        return name + " (адрес: " + address + ", тел.: " + phone + ")";
    }
}
