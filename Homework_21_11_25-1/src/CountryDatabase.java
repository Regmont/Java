import java.util.ArrayList;
import java.util.List;

/**
 * Класс для работы с данными о странах и городах.
 * Имитирует подключение к базе данных.
 */
public class CountryDatabase {
    private final List<Country> countries;

    /**
     * Создает объект базы данных и инициализирует тестовыми данными.
     */
    public CountryDatabase() {
        this.countries = new ArrayList<>();
        initializeSampleData();
    }

    /**
     * Имитирует подключение к базе данных.
     */
    public void connect() {
        System.out.println("Подключение к базе данных 'Страны'...");
    }

    /**
     * Имитирует отключение от базы данных.
     */
    public void disconnect() {
        System.out.println("Отключение от базы данных 'Страны'...");
    }

    /**
     * Инициализирует тестовые данные.
     */
    private void initializeSampleData() {
        Country russia = new Country("Россия");
        russia.addCity(new City("Москва", 13000000, true));
        russia.addCity(new City("Санкт-Петербург", 5600000, false));
        russia.addCity(new City("Новосибирск", 1600000, false));
        russia.addCity(new City("Екатеринбург", 1500000, false));
        countries.add(russia);

        Country usa = new Country("США");
        usa.addCity(new City("Вашингтон", 700000, true));
        usa.addCity(new City("Нью-Йорк", 8500000, false));
        usa.addCity(new City("Лос-Анджелес", 4000000, false));
        usa.addCity(new City("Чикаго", 2700000, false));
        usa.addCity(new City("Хьюстон", 2300000, false));
        countries.add(usa);

        Country germany = new Country("Германия");
        germany.addCity(new City("Берлин", 3700000, true));
        germany.addCity(new City("Гамбург", 1800000, false));
        germany.addCity(new City("Мюнхен", 1500000, false));
        germany.addCity(new City("Кёльн", 1100000, false));
        germany.addCity(new City("Франкфурт", 800000, false));
        countries.add(germany);

        Country japan = new Country("Япония");
        japan.addCity(new City("Токио", 14000000, true));
        japan.addCity(new City("Осака", 2700000, false));
        japan.addCity(new City("Киото", 1400000, false));
        countries.add(japan);
    }

    /**
     * Отображает все страны.
     */
    public void displayAllCountries() {
        System.out.println("\nВсе страны:");
        for (Country country : countries) {
            System.out.println(country);
        }
    }

    /**
     * Отображает все города конкретной страны.
     *
     * @param countryName название страны
     */
    public void displayCitiesOfCountry(String countryName) {
        System.out.println("\nГорода страны: " + countryName);
        Country country = findCountryByName(countryName);
        if (country != null) {
            List<City> cities = country.getCities();
            if (cities.isEmpty()) {
                System.out.println("Городов не найдено");
            } else {
                for (City city : cities) {
                    System.out.println("  " + city);
                }
            }
        } else {
            System.out.println("Страна не найдена");
        }
    }

    /**
     * Отображает все столицы.
     */
    public void displayAllCapitals() {
        System.out.println("\nВсе столицы:");
        for (Country country : countries) {
            City capital = country.getCapital();
            if (capital != null) {
                System.out.println(country.getName() + ": " + capital.getName() +
                        " (население: " + capital.getPopulation() + ")");
            }
        }
    }

    /**
     * Отображает столицу конкретной страны.
     *
     * @param countryName название страны
     */
    public void displayCapitalOfCountry(String countryName) {
        System.out.println("\nСтолица страны: " + countryName);
        Country country = findCountryByName(countryName);
        if (country != null) {
            City capital = country.getCapital();
            if (capital != null) {
                System.out.println(capital.getName() + " (население: " + capital.getPopulation() + ")");
            } else {
                System.out.println("Столица не найдена");
            }
        } else {
            System.out.println("Страна не найдена");
        }
    }

    /**
     * Находит страну по имени.
     *
     * @param name название страны
     * @return объект страны или null
     */
    private Country findCountryByName(String name) {
        for (Country country : countries) {
            if (country.getName().equalsIgnoreCase(name)) {
                return country;
            }
        }
        return null;
    }
}
