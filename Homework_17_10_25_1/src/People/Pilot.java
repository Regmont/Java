package People;

public class Pilot extends Human {
    private int flightHours;

    public Pilot(String name, int age, int flightHours) {
        super(name, age);
        this.flightHours = flightHours;
    }

    public int getFlightHours() {
        return flightHours;
    }

    public void setFlightHours(int flightHours) {
        this.flightHours = flightHours;
    }

    @Override
    public String toString() {
        return super.toString() + ", flight hours = " + flightHours;
    }
}