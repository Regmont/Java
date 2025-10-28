package classes;

public class Country {
    private String name;
    private String capital;
    private long population;
    private double area;
    private String currency;

    public Country() {
        this.name = "";
        this.capital = "";
        this.population = 0;
        this.area = 0.0;
        this.currency = "";
    }

    public Country(String name) {
        this.name = name;
        this.capital = "";
        this.population = 0;
        this.area = 0.0;
        this.currency = "";
    }

    public Country(String name, String capital) {
        this.name = name;
        this.capital = capital;
        this.population = 0;
        this.area = 0.0;
        this.currency = "";
    }

    public Country(String name, String capital, long population, double area, String currency) {
        this.name = name;
        this.capital = capital;
        this.population = population;
        this.area = area;
        this.currency = currency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Country{name='" + name + "', capital='" + capital +
                "', population=" + population + ", area=" + area +
                ", currency='" + currency + "'}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Country country = (Country) obj;
        return population == country.population &&
                Double.compare(country.area, area) == 0 &&
                name.equals(country.name) &&
                capital.equals(country.capital) &&
                currency.equals(country.currency);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(name, capital, population, area, currency);
    }

    public double getPopulationDensity() {
        return population > 0 && area > 0 ? (double) population / area : 0;
    }

    public void displayInfo() {
        System.out.println(this.toString());
    }

    public void displayInfo(boolean detailed) {
        if (detailed) {
            System.out.println("Country Details:");
            System.out.println("  Name: " + name);
            System.out.println("  Capital: " + capital);
            System.out.println("  Population: " + population);
            System.out.println("  Area: " + area + " km²");
            System.out.println("  Currency: " + currency);
            System.out.println("  Population Density: " + getPopulationDensity() + " people/km²");
        } else {
            displayInfo();
        }
    }
}
