import java.util.ArrayList;
import java.util.List;

/**
 * Класс, представляющий страну с её городами.
 */
public class Country {
    private String name;
    private final List<City> cities;
    private int totalPopulation;

    /**
     * Создает объект страны.
     *
     * @param name название страны
     */
    public Country(String name) {
        this.name = name;
        this.cities = new ArrayList<>();
        this.totalPopulation = 0;
    }

    /**
     * Добавляет город в страну.
     *
     * @param city объект города
     */
    public void addCity(City city) {
        cities.add(city);
        totalPopulation += city.getPopulation();
    }

    /**
     * Возвращает столицу страны.
     *
     * @return столица или null, если не найдена
     */
    public City getCapital() {
        for (City city : cities) {
            if (city.isCapital()) {
                return city;
            }
        }
        return null;
    }

    /**
     * Возвращает список крупных городов (не столиц).
     *
     * @return список крупных городов
     */
    public List<City> getMajorCities() {
        List<City> majorCities = new ArrayList<>();
        for (City city : cities) {
            if (!city.isCapital()) {
                majorCities.add(city);
            }
        }
        return majorCities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<City> getCities() {
        return cities;
    }

    public int getTotalPopulation() {
        return totalPopulation;
    }

    public int getCityCount() {
        return cities.size();
    }

    @Override
    public String toString() {
        return name + " (население: " + totalPopulation + ", городов: " + cities.size() + ")";
    }
}
