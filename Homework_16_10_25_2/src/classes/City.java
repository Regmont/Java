package classes;

public class City {
    private String name;
    private String country;
    private int population;
    private double area;

    public City() {
        this.name = "";
        this.country = "";
        this.population = 0;
        this.area = 0.0;
    }

    public City(String name) {
        this.name = name;
        this.country = "";
        this.population = 0;
        this.area = 0.0;
    }

    public City(String name, String country) {
        this.name = name;
        this.country = country;
        this.population = 0;
        this.area = 0.0;
    }

    public City(String name, String country, int population, double area) {
        this.name = name;
        this.country = country;
        this.population = population;
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "City{name='" + name + "', country='" + country +
                "', population=" + population + ", area=" + area + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        City city = (City) obj;
        return population == city.population &&
                Double.compare(city.area, area) == 0 &&
                name.equals(city.name) &&
                country.equals(city.country);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(name, country, population, area);
    }

    public double getPopulationDensity() {
        return population > 0 && area > 0 ? population / area : 0;
    }

    public void displayInfo() {
        System.out.println(this.toString());
    }

    public void displayInfo(boolean includeDensity) {
        if (includeDensity) {
            System.out.println("City: " + name);
            System.out.println("Country: " + country);
            System.out.println("Population: " + population);
            System.out.println("Area: " + area + " km²");
            System.out.println("Population Density: " + getPopulationDensity() + " people/km²");
        } else {
            displayInfo();
        }
    }
}
