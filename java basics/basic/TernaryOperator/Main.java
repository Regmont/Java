import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("введите число: ");
        int number = scanner.nextInt();

        String string = number % 2 == 0 ? "четное" : "нечетное";

        System.out.println("Число " + number + " " + string);

        scanner.close();
    }
}