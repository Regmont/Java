import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double income;
        double foodExpenses;
        double houseExpenses;
        double transportExpenses;

        System.out.print("Введите месячный доход: ");
        income = scanner.nextDouble();

        System.out.print("Введите сумма расходов на еду: ");
        foodExpenses = scanner.nextDouble();

        System.out.print("Введите сумма расходов на жилье: ");
        houseExpenses = scanner.nextDouble();

        System.out.print("Введите сумма расходов на транспорт: ");
        transportExpenses = scanner.nextDouble();

        double remainder = income - (foodExpenses + houseExpenses + transportExpenses);

        System.out.println("Остаток бюджета:" + remainder + " рублей");

	scanner.close();
    }
}