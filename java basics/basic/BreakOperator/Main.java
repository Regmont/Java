import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите целое число больше 1: ");
        int number = scanner.nextInt();

        boolean isPrime = true;
        int divider = 2;

        for (int i = 2; i <= number / 2; i++) {
            if (number % i == 0) {
                isPrime = false;
                divider = i;
                break;
            }
        }

        if (isPrime) {
            System.out.println("Число " + number + " - простое");
        }
        else {
            System.out.println("Первый делитель числа " + number + " = " + divider);
        }

        scanner.close();
    }
}