import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("введите ваш возраст: ");
        int age = scanner.nextInt();

        if (age < 18) {
            System.out.println("вы ребенок");
        }
        else if (age <= 65) {
            System.out.println("вы взрослый");
        }
        else {
            System.out.println("вы пенсионер");
        }

        scanner.close();
    }
}