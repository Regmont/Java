package People;

public class Builder extends Human {
    private String specialty;

    public Builder(String name, int age, String specialty) {
        super(name, age);
        this.specialty = specialty;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    @Override
    public String toString() {
        return super.toString() + ", specialty: " + specialty;
    }
}