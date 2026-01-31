import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * Главный класс для запуска приложения по работе с базой данных автомобилей.
 */
public class ClientRunner {
    /**
     * Точка входа в приложение.
     *
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

        CarDatabase db = new CarDatabase();

        db.connect();

        db.displayAllCars();

        db.displayAllManufacturers();

        db.displayManufacturersWithCount();

        db.disconnect();
    }
}
