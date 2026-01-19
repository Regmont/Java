package model;

import java.time.LocalDate;

/**
 * Класс представляет клиента кофейни.
 */
public class Client {
    private final String name;
    private final LocalDate birthDate;
    private final String email;
    private final double discount;

    public Client(String name, LocalDate birthDate, String email, double discount) {
        this.name = name;
        this.birthDate = birthDate;
        this.email = email;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getEmail() {
        return email;
    }

    public double getDiscount() {
        return discount;
    }

    public int getAge() {
        return LocalDate.now().getYear() - birthDate.getYear();
    }

    public boolean hasBirthdayToday() {
        LocalDate today = LocalDate.now();
        return birthDate.getMonth() == today.getMonth() &&
                birthDate.getDayOfMonth() == today.getDayOfMonth();
    }

    public boolean hasEmail() {
        return email != null && !email.trim().isEmpty();
    }

    @Override
    public String toString() {
        return "Клиент: " + name +
                ", Возраст: " + getAge() +
                ", Email: " + (email != null ? email : "не указан") +
                ", Скидка: " + discount + "%";
    }
}
