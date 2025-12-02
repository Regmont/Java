import java.util.*;

public class ClientRunner {

    public static void main(String[] args) {
        Task1();
        Task2();
        Task3();
        Task4();
    }

    static void Task1() {
        List<Integer> numbers = new Random().ints(20, -1000, 1000)
                .boxed().toList();

        long positiveCount = numbers.stream().filter(n -> n > 0).count();
        long negativeCount = numbers.stream().filter(n -> n < 0).count();
        long twoDigitCount = numbers.stream().filter(n -> Math.abs(n) >= 10 && Math.abs(n) <= 99).count();
        long mirrorCount = numbers.stream().filter(n -> {
            String s = Integer.toString(Math.abs(n));
            return new StringBuilder(s).reverse().toString().equals(s);
        }).count();

        System.out.println("Numbers: " + numbers);
        System.out.println("Positive: " + positiveCount);
        System.out.println("Negative: " + negativeCount);
        System.out.println("Two-digit: " + twoDigitCount);
        System.out.println("Mirror numbers: " + mirrorCount);
    }

    static void Task2() {
        List<String> products = Arrays.asList(
                "Milk", "Bread", "Cheese", "Milk", "Butter", "Egg", "Yogurt", "Milk", "Bread", "Water");

        System.out.println("All products: " + products);
        System.out.println("Products with name length < 5: " +
                products.stream().filter(p -> p.length() < 5).toList());

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter product name to count: ");
        String search = scanner.nextLine();
        long count = products.stream().filter(p -> p.equalsIgnoreCase(search)).count();
        System.out.println("Product '" + search + "' appears " + count + " times");

        System.out.print("Enter starting letter: ");
        char letter = scanner.nextLine().charAt(0);
        System.out.println("Products starting with '" + letter + "': " + products.stream()
                 .filter(p -> !p.isEmpty() && Character.toLowerCase(p.charAt(0)) == Character.toLowerCase(letter))
                 .toList());

        System.out.println("Products from category 'Milk': " +
                products.stream().filter(p -> p.equalsIgnoreCase("Milk")).toList());
    }

    static class Device {
        String name;
        int year;
        double price;
        String color;
        String type;

        Device(String name, int year, double price, String color, String type) {
            this.name = name;
            this.year = year;
            this.price = price;
            this.color = color;
            this.type = type;
        }

        @Override
        public String toString() {
            return name + " (" + year + ", " + price + ", " + color + ", " + type + ")";
        }
    }

    static void Task3() {
        List<Device> devices = Arrays.asList(
                new Device("Phone", 2022, 699.99, "Black", "Mobile"),
                new Device("Laptop", 2021, 1299.99, "Silver", "Computer"),
                new Device("Tablet", 2023, 399.99, "Black", "Mobile"),
                new Device("Monitor", 2020, 299.99, "White", "Display"),
                new Device("Keyboard", 2023, 89.99, "Black", "Peripheral")
        );

        System.out.println("All devices:");
        devices.forEach(System.out::println);

        System.out.println("Devices of color Black:");
        devices.stream().filter(d -> d.color.equalsIgnoreCase("Black")).forEach(System.out::println);

        System.out.println("Devices from year 2023:");
        devices.stream().filter(d -> d.year == 2023).forEach(System.out::println);

        double priceLimit = 500.0;
        System.out.println("Devices more expensive than " + priceLimit + ":");
        devices.stream().filter(d -> d.price > priceLimit).forEach(System.out::println);

        System.out.println("Devices of type Mobile:");
        devices.stream().filter(d -> d.type.equalsIgnoreCase("Mobile")).forEach(System.out::println);

        int startYear = 2021, endYear = 2023;
        System.out.println("Devices with year between " + startYear + " and " + endYear + ":");
        devices.stream().filter(d -> d.year >= startYear && d.year <= endYear).forEach(System.out::println);
    }

    static class Projector {
        String name;
        int year;
        double price;
        String manufacturer;

        Projector(String name, int year, double price, String manufacturer) {
            this.name = name;
            this.year = year;
            this.price = price;
            this.manufacturer = manufacturer;
        }

        @Override
        public String toString() {
            return name + " (" + year + ", " + price + ", " + manufacturer + ")";
        }
    }

    static void Task4() {
        List<Projector> projectors = Arrays.asList(
                new Projector("ProX", 2023, 1200.0, "Epson"),
                new Projector("LiteBeam", 2022, 800.0, "BenQ"),
                new Projector("UltraView", 2024, 1500.0, "Epson"),
                new Projector("MiniPro", 2023, 600.0, "ViewSonic"),
                new Projector("BrightMax", 2021, 900.0, "BenQ")
        );

        System.out.println("All projectors:");
        projectors.forEach(System.out::println);

        System.out.println("Projectors by manufacturer Epson:");
        projectors.stream().filter(p -> p.manufacturer.equalsIgnoreCase("Epson"))
                .forEach(System.out::println);

        int currentYear = 2024;
        System.out.println("Projectors from current year " + currentYear + ":");
        projectors.stream().filter(p -> p.year == currentYear).forEach(System.out::println);

        double priceLimit = 1000.0;
        System.out.println("Projectors more expensive than " + priceLimit + ":");
        projectors.stream().filter(p -> p.price > priceLimit).forEach(System.out::println);

        System.out.println("Projectors sorted by price ascending:");
        projectors.stream().sorted(Comparator.comparingDouble(p -> p.price)).forEach(System.out::println);

        System.out.println("Projectors sorted by price descending:");
        projectors.stream().sorted(Comparator.comparingDouble((Projector p) -> p.price).reversed())
                .forEach(System.out::println);

        System.out.println("Projectors sorted by year ascending:");
        projectors.stream().sorted(Comparator.comparingInt(p -> p.year)).forEach(System.out::println);

        System.out.println("Projectors sorted by year descending:");
        projectors.stream().sorted(Comparator.comparingInt((Projector p) -> p.year).reversed())
                .forEach(System.out::println);
    }
}