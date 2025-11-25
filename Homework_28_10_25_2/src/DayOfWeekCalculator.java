import java.time.DayOfWeek;
import java.time.LocalDate;

public interface DayOfWeekCalculator {
    DayOfWeek calculate(LocalDate date);
}
