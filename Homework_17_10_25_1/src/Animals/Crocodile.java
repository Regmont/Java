package Animals;

public class Crocodile extends Animal {
    private float length;

    public Crocodile(String name, double weight, float length) {
        super(name, weight);
        this.length = length;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return super.toString() + ", length = " + length + " cm";
    }
}
