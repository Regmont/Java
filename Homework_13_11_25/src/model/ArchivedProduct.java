package model;

import java.time.LocalDateTime;

/**
 * Класс представляет архивную запись удаленного напитка или десерта.
 */
public class ArchivedProduct {
    private final int originalId;
    private final String name;
    private final String type;
    private final double price;
    private final LocalDateTime archivedAt;
    private final int archivedByCoffeeShopId;

    public ArchivedProduct(int originalId, String name, String type, double price,
                           int archivedByCoffeeShopId) {
        this.originalId = originalId;
        this.name = name;
        this.type = type;
        this.price = price;
        this.archivedAt = LocalDateTime.now();
        this.archivedByCoffeeShopId = archivedByCoffeeShopId;
    }

    public int getOriginalId() { return originalId; }
    public String getName() { return name; }
    public String getType() { return type; }
    public double getPrice() { return price; }
    public LocalDateTime getArchivedAt() { return archivedAt; }
    public int getArchivedByCoffeeShopId() { return archivedByCoffeeShopId; }

    @Override
    public String toString() {
        return "Архив: " + name + " (" + type + "), цена: " + price +
                ", удалено: " + archivedAt.toLocalDate();
    }
}
