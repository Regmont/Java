import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int currentNumber = 1;
        int sum = 0;

        while (currentNumber != 0) {
            System.out.print("введите число: ");
            currentNumber = scanner.nextInt();

            sum += currentNumber;
        }

        System.out.println("Сумма введенных чисел: " + sum);

        scanner.close();
    }
}