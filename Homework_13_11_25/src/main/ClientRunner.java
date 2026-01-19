package main;

import database.CoffeeShopDatabase;
import model.Client;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

public class ClientRunner {
    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

        CoffeeShopDatabase db = new CoffeeShopDatabase();

        db.addClient(new Client("Иван Петров", LocalDate.of(1990, 5, 15),
                "ivan@mail.ru", 5.0));
        db.addClient(new Client("Мария Сидорова", LocalDate.of(1985, 3, 22),
                "maria@gmail.com", 15.0));
        db.addClient(new Client("Алексей Иванов", LocalDate.of(2000, 12, 10),
                null, 10.0));
        db.addClient(new Client("Елена Ковалёва", LocalDate.of(1978, 8, 8),
                "elena@yandex.ru", 20.0));
        db.addClient(new Client("Дмитрий Смирнов", LocalDate.of(1995, 3, 22),
                "", 5.0));

        System.out.println("Информация по скидкам клиентов");

        System.out.println("\nОбщая статистика:");
        System.out.println("Минимальная скидка: " + db.getMinDiscount() + "%");
        System.out.println("Максимальная скидка: " + db.getMaxDiscount() + "%");
        System.out.println("Средняя скидка: " + String.format("%.1f", db.getAverageDiscount()) + "%");

        System.out.println("\nКлиенты с минимальной скидкой (" + db.getMinDiscount() + "%):");
        for (Client client : db.getClientsWithMinDiscount()) {
            System.out.println("  " + client.getName());
        }

        System.out.println("\nКлиенты с максимальной скидкой (" + db.getMaxDiscount() + "%):");
        for (Client client : db.getClientsWithMaxDiscount()) {
            System.out.println("  " + client.getName());
        }

        System.out.println("\nИнформация по клиентам");

        Client youngest = db.getYoungestClient();
        Client oldest = db.getOldestClient();

        if (youngest != null) {
            System.out.println("\nСамый молодой клиент: " +
                    youngest.getName() + ", возраст: " + youngest.getAge() + " лет");
        }

        if (oldest != null) {
            System.out.println("Самый возрастной клиент: " +
                    oldest.getName() + ", возраст: " + oldest.getAge() + " лет");
        }

        System.out.println("\nКлиенты с днем рождения сегодня:");
        var birthdayClients = db.getClientsWithBirthdayToday();
        if (birthdayClients.isEmpty()) {
            System.out.println("  нет клиентов с днем рождения сегодня");
        } else {
            for (Client client : birthdayClients) {
                System.out.println("  " + client.getName());
            }
        }

        System.out.println("\nКлиенты без email:");
        var clientsWithoutEmail = db.getClientsWithoutEmail();
        if (clientsWithoutEmail.isEmpty()) {
            System.out.println("  все клиенты указали email");
        } else {
            for (Client client : clientsWithoutEmail) {
                System.out.println("  " + client.getName());
            }
        }
    }
}
