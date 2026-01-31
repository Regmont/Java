import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * Главный класс приложения для работы с базой данных стран.
 */
public class ClientRunner {
    /**
     * Точка входа в приложение.
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

        CountryDatabase db = new CountryDatabase();

        db.connect();

        db.displayAllCountries();

        db.displayCitiesOfCountry("Россия");
        db.displayCitiesOfCountry("США");

        db.displayAllCapitals();

        db.displayCapitalOfCountry("Германия");
        db.displayCapitalOfCountry("Япония");

        db.disconnect();
    }
}
