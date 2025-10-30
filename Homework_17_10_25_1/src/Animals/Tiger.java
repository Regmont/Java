package Animals;

public class Tiger extends Animal {
    private float speed;

    public Tiger(String name, double weight, float speed) {
        super(name, weight);
        this.speed = speed;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return super.toString() + " speed = " + speed + " m/s";
    }
}