package Dunin;

import classes.*;

public class ClientRunner {
    public static void main(String[] args) {

        System.out.println("=== Person Class Demo ===");
        Person person1 = new Person("John", "Doe", 30);
        Person person2 = new Person("Jane", "Smith");
        person1.displayInfo();
        person1.displayInfo(true);

        System.out.println("\n=== City Class Demo ===");
        City city = new City("New York", "USA", 8419000, 783.8);
        city.displayInfo();
        city.displayInfo(true);

        System.out.println("\n=== Country Class Demo ===");
        Country country = new Country("United States", "Washington D.C.", 331000000, 9834000, "USD");
        country.displayInfo();
        country.displayInfo(true);

        System.out.println("\n=== Fraction Class Demo ===");
        Fraction frac1 = new Fraction(1, 2);
        Fraction frac2 = new Fraction(3, 4);
        frac1.display();
        frac1.display(true);

        Fraction sum = frac1.add(frac2);
        System.out.print("1/2 + 3/4 = ");
        sum.display();

        System.out.println("\n=== Book Class Demo ===");
        Book book = new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925,
                "Scribner", "Novel", 180);
        book.displayData();
        book.displayData(true);
        book.displayData("detailed");

        System.out.println("\n=== Car Class Demo ===");
        Car car = new Car("Camry", "Toyota", 2022, 2.5);
        car.displayData();
        car.displayData(true);
        car.displayData("short");
    }
}