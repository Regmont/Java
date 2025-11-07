package classes;

import interfaces.Resizable;

public class Circle extends Shape implements Resizable {
    double radius;

    public Circle(String color, double radius) {
        super(color);
        this.radius = radius;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a " + color + " circle");
    }

    @Override
    public void resize(double factor) {
        this.radius *= factor;
    }

    public double getRadius() {
        return radius;
    }
}
