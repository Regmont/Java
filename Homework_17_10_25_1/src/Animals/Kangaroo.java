package Animals;

public class Kangaroo extends Animal {
    private float jumpHeight;

    public Kangaroo(String name, double weight, float jumpHeight) {
        super(name, weight);
        this.jumpHeight = jumpHeight;
    }

    public float getJumpHeight() {
        return jumpHeight;
    }

    public void setJumpHeight(float jumpHeight) {
        this.jumpHeight = jumpHeight;
    }

    @Override
    public String toString() {
        return super.toString() + ", jump height = " + jumpHeight + " cm";
    }
}
