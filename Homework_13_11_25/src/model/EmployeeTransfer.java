package model;

import java.time.LocalDateTime;

/**
 * Класс представляет запись о переводе сотрудника между кофейнями.
 */
public class EmployeeTransfer {
    private final int employeeId;
    private final int fromCoffeeShopId;
    private final int toCoffeeShopId;
    private final LocalDateTime transferDate;

    public EmployeeTransfer(int employeeId, int fromCoffeeShopId, int toCoffeeShopId) {
        this.employeeId = employeeId;
        this.fromCoffeeShopId = fromCoffeeShopId;
        this.toCoffeeShopId = toCoffeeShopId;
        this.transferDate = LocalDateTime.now();
    }

    public int getEmployeeId() { return employeeId; }
    public int getFromCoffeeShopId() { return fromCoffeeShopId; }
    public int getToCoffeeShopId() { return toCoffeeShopId; }
    public LocalDateTime getTransferDate() { return transferDate; }

    @Override
    public String toString() {
        return "Перевод сотрудника #" + employeeId +
                " из кофейни #" + fromCoffeeShopId +
                " в кофейню #" + toCoffeeShopId +
                " (" + transferDate.toLocalDate() + ")";
    }
}
