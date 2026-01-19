package main;

import database.CoffeeShopDatabase;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class ClientRunner {
    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

        CoffeeShopDatabase db = new CoffeeShopDatabase();

        System.out.println("Тест добавления данных:");
        int coffeeOrderId = db.addCoffeeOrder("Капучино", 2, "Иван Петров");
        System.out.println("Создан заказ кофе #" + coffeeOrderId);

        int dessertOrderId = db.addDessertOrder("Тирамису", 1, "Мария Сидорова");
        System.out.println("Создан заказ десерта #" + dessertOrderId);

        db.addOrUpdateWorkSchedule("Понедельник", "08:00-20:00");
        System.out.println("График на понедельник добавлен");

        System.out.println("\nТест обновления данных:");
        db.updateWorkSchedule("Вторник", "09:00-21:00");
        System.out.println("График на вторник обновлён");

        int coffeeTypeId = db.addCoffeeType("Латте", "Кофе с молоком", 180.0);
        db.updateCoffeeTypeName(coffeeTypeId, "Латте Премиум");
        System.out.println("Название кофе изменено на 'Латте Премиум'");

        db.updateCoffeeOrder(coffeeOrderId, "Двойной капучино", 1, "Иван Петров");
        System.out.println("Заказ кофе обновлён");

        db.updateDessertName("Тирамису", "Тирамису классический");
        System.out.println("Название десерта изменено во всех заказах");

        System.out.println("\nТест обработки ошибок:");
        try {
            db.updateCoffeeOrder(999, "Эспрессо", 1, "Тест");
        } catch (IllegalArgumentException e) {
            System.out.println("Поймана ошибка: " + e.getMessage());
        }

        System.out.println("\nВсе тесты выполнены.");
    }
}
