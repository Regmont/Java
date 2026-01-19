package main;

import database.CoffeeShopDatabase;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class ClientRunner {
    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

        CoffeeShopDatabase db = new CoffeeShopDatabase();

        System.out.println("Часть 3: Сеть кофеен");

        System.out.println("\n1. Все кофейни сети:");
        db.getAllCoffeeShops().forEach(shop ->
                System.out.println("   " + shop));

        System.out.println("\n2. Напитки, которые есть во всех кофейнях:");
        var drinksInAll = db.getDrinksAvailableInAllShops();
        if (drinksInAll.isEmpty()) {
            System.out.println("   нет напитков, доступных во всех кофейнях");
        } else {
            drinksInAll.forEach(drink ->
                    System.out.println("   " + drink));
        }

        System.out.println("\n3. Десерты, которые есть во всех кофейнях:");
        var dessertsInAll = db.getDessertsAvailableInAllShops();
        if (dessertsInAll.isEmpty()) {
            System.out.println("   нет десертов, доступных во всех кофейнях");
        } else {
            dessertsInAll.forEach(dessert ->
                    System.out.println("   " + dessert));
        }

        System.out.println("\n4. Все бариста сети:");
        db.getAllBaristas().forEach(barista ->
                System.out.println("   " + barista.getName() + " (кофейня #" + barista.getCoffeeShopId() + ")"));

        System.out.println("\n5. Все официанты сети:");
        db.getAllWaiters().forEach(waiter ->
                System.out.println("   " + waiter.getName() + " (кофейня #" + waiter.getCoffeeShopId() + ")"));

        System.out.println("\n6. Популярные позиции:");
        System.out.println("   Самый популярный напиток: " + db.getMostPopularDrink());
        System.out.println("   Самый популярный десерт: " + db.getMostPopularDessert());
        System.out.println("   ТОП-3 напитков: " + String.join(", ", db.getTop3Drinks()));
        System.out.println("   ТОП-3 десертов: " + String.join(", ", db.getTop3Desserts()));

        System.out.println("\n7. Демонстрация работы триггеров:");
        db.removeDrink(1, 1);
        System.out.println("   Удален напиток в кофейне #1 (архивирован)");

        db.transferEmployee(1, 2);
        System.out.println("   Сотрудник #1 переведен из кофейни #1 в #2");

        System.out.println("\n8. Черный список:");
        db.addToBlacklist(3);
        System.out.println("   Сотрудник #3 добавлен в черный список");

        var blacklisted = db.getBlacklistedEmployees();
        if (blacklisted.isEmpty()) {
            System.out.println("   Черный список пуст");
        } else {
            System.out.println("   В черном списке:");
            blacklisted.forEach(emp ->
                    System.out.println("   " + emp.getName()));
        }

        System.out.println("\n9. Бариста, работавшие во всех кофейнях:");
        var baristasAllShops = db.getBaristasWorkedInAllShops();
        if (baristasAllShops.isEmpty()) {
            System.out.println("   нет таких бариста");
        } else {
            baristasAllShops.forEach(barista ->
                    System.out.println("   " + barista.getName()));
        }

        System.out.println("\nДемонстрация завершена");
    }
}
