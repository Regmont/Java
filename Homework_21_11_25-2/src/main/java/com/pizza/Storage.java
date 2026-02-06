package com.pizza;

import java.util.*;

public class Storage {
    private static final List<Pizza> pizzas = new ArrayList<>();
    private static final List<Order> orders = new ArrayList<>();
    private static int orderCounter = 1;

    static {
        pizzas.add(new Pizza("Маргарита", 450.0));
        pizzas.add(new Pizza("Четыре сыра", 550.0));
        pizzas.add(new Pizza("Капричоза", 500.0));
        pizzas.add(new Pizza("Гавайская", 480.0));
    }

    public static List<Pizza> getAllPizzas() {
        return new ArrayList<>(pizzas);
    }

    public static Pizza getPizzaByName(String name) {
        for (Pizza pizza : pizzas) {
            if (pizza.getName().equals(name)) {
                return pizza;
            }
        }
        return null;
    }

    public static void saveOrder(Order order) {
        orders.add(order);
        orderCounter++;
    }

    public static List<Order> getAllOrders() {
        return new ArrayList<>(orders);
    }

    public static int getOrdersCount() {
        return orders.size();
    }
}
