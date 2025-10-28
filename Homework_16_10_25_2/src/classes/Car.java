package classes;

public class Car {
    private String model;
    private String manufacturer;
    private int year;
    private double engineVolume;

    public Car() {
        this.model = "";
        this.manufacturer = "";
        this.year = 0;
        this.engineVolume = 0.0;
    }

    public Car(String model) {
        this.model = model;
        this.manufacturer = "";
        this.year = 0;
        this.engineVolume = 0.0;
    }

    public Car(String model, String manufacturer) {
        this.model = model;
        this.manufacturer = manufacturer;
        this.year = 0;
        this.engineVolume = 0.0;
    }

    public Car(String model, String manufacturer, int year, double engineVolume) {
        this.model = model;
        this.manufacturer = manufacturer;
        this.year = year;
        this.engineVolume = engineVolume;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getEngineVolume() {
        return engineVolume;
    }

    public void setEngineVolume(double engineVolume) {
        this.engineVolume = engineVolume;
    }

    @Override
    public String toString() {
        return "Car{model='" + model + "', manufacturer='" + manufacturer +
                "', year=" + year + ", engineVolume=" + engineVolume + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Car car = (Car) obj;
        return year == car.year &&
                Double.compare(car.engineVolume, engineVolume) == 0 &&
                model.equals(car.model) &&
                manufacturer.equals(car.manufacturer);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(model, manufacturer, year, engineVolume);
    }

    public void inputData(String model, String manufacturer, int year, double engineVolume) {
        this.model = model;
        this.manufacturer = manufacturer;
        this.year = year;
        this.engineVolume = engineVolume;
    }

    public void inputData(String model, String manufacturer) {
        this.model = model;
        this.manufacturer = manufacturer;
    }

    public void displayData() {
        System.out.println(this.toString());
    }

    public void displayData(boolean technical) {
        if (technical) {
            System.out.println("Car Technical Specifications:");
            System.out.println("  Model: " + model);
            System.out.println("  Manufacturer: " + manufacturer);
            System.out.println("  Year: " + year);
            System.out.println("  Engine Volume: " + engineVolume + "L");
        } else {
            displayData();
        }
    }

    public void displayData(String infoType) {
        if ("short".equalsIgnoreCase(infoType)) {
            System.out.println(manufacturer + " " + model + " (" + year + ")");
        } else {
            displayData();
        }
    }
}