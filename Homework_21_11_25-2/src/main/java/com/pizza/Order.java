package com.pizza;

import java.util.Date;

public class Order {
    private final String customerName;
    private final String phone;
    private final String email;
    private final String address;
    private final String pizzaName;
    private final double totalPrice;
    private final Date orderDate;

    public Order(String customerName, String phone, String email, String address, String pizzaName, double totalPrice) {
        this.customerName = customerName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.pizzaName = pizzaName;
        this.totalPrice = totalPrice;
        this.orderDate = new Date();
    }

    public String getCustomerName() { return customerName; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }
    public String getPizzaName() { return pizzaName; }
    public double getTotalPrice() { return totalPrice; }
    public Date getOrderDate() { return orderDate; }
}
