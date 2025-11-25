import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.function.Predicate;

public class ClientRunner {
    public static void main(String[] args) {
        Task1();
        Task2();
        Task3();
        Task4();
    }

    public static void Task1() {
        LeapYearCheck leapYearCheck = year -> (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
        System.out.println("Is 2024 leap year? " + leapYearCheck.isLeap(2024));

        DaysBetween daysBetween = (date1, date2) -> ChronoUnit.DAYS.between(date1, date2);
        LocalDate date1 = LocalDate.of(2024, 1, 1);
        LocalDate date2 = LocalDate.of(2024, 12, 31);
        System.out.println("Days between " + date1 + " and " + date2 + ": " + daysBetween.calculate(date1, date2));

        WeeksBetween weeksBetween = (start, end) -> ChronoUnit.WEEKS.between(start, end);
        System.out.println("Weeks between " + date1 + " and " + date2 + ": " + weeksBetween.calculate(date1, date2));

        DayOfWeekCalculator dayOfWeekCalculator = date -> date.getDayOfWeek();
        LocalDate moonLanding = LocalDate.of(1969, 7, 20);
        System.out.println("Day of week for " + moonLanding + ": " + dayOfWeekCalculator.calculate(moonLanding));
    }

    public static void Task2() {
        FractionOperation add = (a, b, c, d) -> a * d + b * c + "/" + b * d;
        System.out.println("1/2 + 1/3 = " + add.operate(1, 2, 1, 3));

        FractionOperation subtract = (a, b, c, d) -> a * d - b * c + "/" + b * d;
        System.out.println("1/2 - 1/3 = " + subtract.operate(1, 2, 1, 3));

        FractionOperation multiply = (a, b, c, d) -> a * c + "/" + b * d;
        System.out.println("1/2 * 1/3 = " + multiply.operate(1, 2, 1, 3));

        FractionOperation divide = (a, b, c, d) -> a * d + "/" + b * c;
        System.out.println("1/2 / 1/3 = " + divide.operate(1, 2, 1, 3));
    }

    public static void Task3() {
        QuadFunction<Integer, Integer, Integer, Integer, Integer> max4 = (a, b, c, d) -> Math.max(Math.max(a, b), Math.max(c, d));
        System.out.println("Max of 1, 5, 3, 2: " + max4.apply(1, 5, 3, 2));

        QuadFunction<Integer, Integer, Integer, Integer, Integer> min4 = (a, b, c, d) -> Math.min(Math.min(a, b), Math.min(c, d));
        System.out.println("Min of 1, 5, 3, 2: " + min4.apply(1, 5, 3, 2));
    }

    public static void Task4() {
        int[] numbers = {1, 5, 3, -4, 5, 10, 15};

        int sumEquals = sumWithCondition(numbers, n -> n == 5);
        System.out.println("Sum of numbers equal to 5: " + sumEquals);

        int sumNotInRange = sumWithCondition(numbers, n -> n < 2 || n > 10);
        System.out.println("Sum of numbers not in range 2-10: " + sumNotInRange);

        int sumPositive = sumWithCondition(numbers, n -> n > 0);
        System.out.println("Sum of positive numbers: " + sumPositive);

        int sumNegative = sumWithCondition(numbers, n -> n < 0);
        System.out.println("Sum of negative numbers: " + sumNegative);
    }

    private static int sumWithCondition(int[] array, Predicate<Integer> condition) {
        return Arrays.stream(array).filter(condition::test).sum();
    }
}
