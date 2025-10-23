import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Random random = new Random();
        int randomNumber = random.nextInt(10);

        int number;

        do {
            System.out.print("Введите целое число от 1 до 10: ");
            number = scanner.nextInt();
        }
        while (number != randomNumber);

        System.out.print("Вы угадали!");

        scanner.close();
    }
}