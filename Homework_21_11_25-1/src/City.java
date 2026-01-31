/**
 * Класс, представляющий крупный город.
 */
public class City {
    private String name;
    private int population;
    private boolean isCapital;

    /**
     * Создает объект города.
     *
     * @param name название города
     * @param population население города
     * @param isCapital является ли город столицей
     */
    public City(String name, int population, boolean isCapital) {
        this.name = name;
        this.population = population;
        this.isCapital = isCapital;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public boolean isCapital() {
        return isCapital;
    }

    public void setCapital(boolean capital) {
        isCapital = capital;
    }

    @Override
    public String toString() {
        return name + " (население: " + population + ")" + (isCapital ? " [столица]" : "");
    }
}
