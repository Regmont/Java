package com.example.coffeeshop.model;

import java.util.Date;

public class CoffeeOrder {
    private int orderId;
    private int clientId;
    private int waiterId;
    private int coffeeId;
    private Date orderDate;
    private int quantity;

    public CoffeeOrder() {}

    public CoffeeOrder(int clientId, int waiterId, int coffeeId, Date orderDate, int quantity) {
        this.clientId = clientId;
        this.waiterId = waiterId;
        this.coffeeId = coffeeId;
        this.orderDate = orderDate;
        this.quantity = quantity;
    }
}