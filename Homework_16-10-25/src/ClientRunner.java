import java.util.Random;
import java.util.Scanner;

public class ClientRunner {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        task1();
        task2();
        task3();
        task4();
        task5();
        task6();
        task7();
        task8();
        task9();
        task10();
        task11();
        task12();
    }

    static void task1() {
        System.out.println("\"Your time is limited,");
        System.out.println("\tso don't waste it");
        System.out.println("\t\tliving someone else's life\"");
        System.out.println("\t\t\tSteve Jobs");
    }

    static void task2() {
        System.out.print("Enter number: ");
        double num = scanner.nextDouble();

        System.out.print("Enter percentage: ");
        double percent = scanner.nextDouble();

        double result = num * percent / 100;
        System.out.println("Result: " + result);
    }

    static void task3() {
        System.out.print("Enter the first digit: ");
        int a = scanner.nextInt();

        System.out.print("Enter the second digit: ");
        int b = scanner.nextInt();

        System.out.print("Enter the third digit: ");
        int c = scanner.nextInt();

        int number = a * 100 + b * 10 + c;
        System.out.println("Your number: " + number);
    }

    static void task4() {
        System.out.print("Enter a six-digit number: ");
        int num = scanner.nextInt();
        if (num < 100000 || num > 999999) {
            System.out.println("Error: not a six-digit number");
            return;
        }

        int digit1 = num / 100000;
        int digit2 = (num / 10000) % 10;
        int digit3 = (num / 1000) % 10;
        int digit4 = (num / 100) % 10;
        int digit5 = (num / 10) % 10;
        int digit6 = num % 10;

        int result = digit6 * 100000 + digit5 * 10000 + digit3 * 1000 + digit4 * 100 + digit2 * 10 + digit1;
        System.out.println("Result: " + result);
    }

    static void task5() {
        System.out.print("Enter month number (1-12): ");
        int month = scanner.nextInt();

        if (month < 1 || month > 12) {
            System.out.println("Error: invalid month number");
            return;
        }

        if (month == 12 || month <= 2) {
            System.out.println("Winter");
        } else if (month <= 5) {
            System.out.println("Spring");
        } else if (month <= 8) {
            System.out.println("Summer");
        } else {
            System.out.println("Autumn");
        }
    }

    static void task6() {
        System.out.print("Enter meters: ");
        double meters = scanner.nextDouble();

        System.out.println("Select conversion unit:");
        System.out.println("1 - Miles");
        System.out.println("2 - Inches");
        System.out.println("3 - Yards");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.println(meters + " meters = " + (meters * 0.000621371) + " miles");
                break;
            case 2:
                System.out.println(meters + " meters = " + (meters * 39.3701) + " inches");
                break;
            case 3:
                System.out.println(meters + " meters = " + (meters * 1.09361) + " yards");
                break;
            default:
                System.out.println("Error: invalid choice");
        }
    }

    static void task7() {
        System.out.print("Enter first number: ");
        int a = scanner.nextInt();

        System.out.print("Enter second number: ");
        int b = scanner.nextInt();

        int start = Math.min(a, b);
        int end = Math.max(a, b);

        System.out.println("Odd numbers from " + start + " to " + end + ":");

        for (int i = start; i <= end; i++) {
            if (i % 2 != 0) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }

    static void task8() {
        System.out.print("Enter range start: ");
        int start = scanner.nextInt();

        System.out.print("Enter range end: ");
        int end = scanner.nextInt();

        for (int i = start; i <= end; i++) {
            for (int j = 1; j <= 10; j++) {
                System.out.printf("%2d * %-2d = %-4d", i, j, i * j);
            }
            System.out.println();
        }
    }

    static void task9() {
        Random random = new Random();

        System.out.print("Enter array size: ");
        int size = scanner.nextInt();

        System.out.print("Enter min random value: ");
        int minRandom = scanner.nextInt();

        System.out.print("Enter max random value: ");
        int maxRandom = scanner.nextInt();

        int[] array = new int[size];
        int range = maxRandom - minRandom + 1;

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(range) + minRandom;
        }

        System.out.print("Array: ");
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();

        int min = array[0];
        int max = array[0];
        int negative = 0;
        int positive = 0;
        int zero = 0;

        for (int num : array) {
            if (num < min) {
                min = num;
            }
            if (num > max) {
                max = num;
            }
            if (num < 0) {
                negative++;
            } else if (num > 0) {
                positive++;
            } else {
                zero++;
            }
        }

        System.out.println("Min element: " + min);
        System.out.println("Max element: " + max);
        System.out.println("Negative count: " + negative);
        System.out.println("Positive count: " + positive);
        System.out.println("Zero count: " + zero);
    }

    static void task10() {
        Random random = new Random();

        System.out.print("Enter array size: ");
        int size = scanner.nextInt();

        System.out.print("Enter min random value: ");
        int minRandom = scanner.nextInt();

        System.out.print("Enter max random value: ");
        int maxRandom = scanner.nextInt();

        int[] original = new int[size];
        int range = maxRandom - minRandom + 1;

        for (int i = 0; i < original.length; i++) {
            original[i] = random.nextInt(range) + minRandom;
        }

        System.out.print("Original array: ");
        for (int num : original) {
            System.out.print(num + " ");
        }
        System.out.println();

        int evenCount = 0;
        int oddCount = 0;
        int negativeCount = 0;
        int positiveCount = 0;

        for (int num : original) {
            if (num % 2 == 0) {
                evenCount++;
            } else {
                oddCount++;
            }
            if (num < 0) {
                negativeCount++;
            } else if (num > 0) {
                positiveCount++;
            }
        }

        int[] even = new int[evenCount];
        int[] odd = new int[oddCount];
        int[] negative = new int[negativeCount];
        int[] positive = new int[positiveCount];
        int e = 0, o = 0, n = 0, p = 0;

        for (int num : original) {
            if (num % 2 == 0) {
                even[e++] = num;
            } else {
                odd[o++] = num;
            }
            if (num < 0) {
                negative[n++] = num;
            } else if (num > 0) {
                positive[p++] = num;
            }
        }

        System.out.print("Even: ");
        for (int num : even) {
            System.out.print(num + " ");
        }
        System.out.println();

        System.out.print("Odd: ");
        for (int num : odd) {
            System.out.print(num + " ");
        }
        System.out.println();

        System.out.print("Negative: ");
        for (int num : negative) {
            System.out.print(num + " ");
        }
        System.out.println();

        System.out.print("Positive: ");
        for (int num : positive) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    static void task11() {
        System.out.print("Enter line length: ");
        int length = scanner.nextInt();

        System.out.print("Choose direction (1 - horizontal, 2 - vertical): ");
        int direction = scanner.nextInt();

        if (direction != 1 && direction != 2) {
            System.out.println("Error: invalid direction");
            return;
        }

        System.out.print("Enter symbol: ");
        char symbol = scanner.next().charAt(0);

        if (direction == 1) {
            for (int i = 0; i < length; i++) {
                System.out.print(symbol);
            }
            System.out.println();
        } else {
            for (int i = 0; i < length; i++) {
                System.out.println(symbol);
            }
        }
    }

    static void task12() {
        Random random = new Random();

        System.out.print("Enter array size: ");
        int size = scanner.nextInt();
        System.out.print("Enter min random value: ");
        int minRandom = scanner.nextInt();
        System.out.print("Enter max random value: ");
        int maxRandom = scanner.nextInt();

        int[] array = new int[size];
        int range = maxRandom - minRandom + 1;
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(range) + minRandom;
        }

        System.out.print("Original array: ");
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();

        System.out.print("Choose sorting (1 - ascending, 2 - descending): ");
        int choice = scanner.nextInt();

        if (choice != 1 && choice != 2) {
            System.out.println("Error: invalid choice");
            return;
        }

        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                boolean shouldSwap = (choice == 1) ? array[j] > array[j + 1] : array[j] < array[j + 1];

                if (shouldSwap) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }

        System.out.print("Sorted array: ");
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
