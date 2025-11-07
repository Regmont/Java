package classes;

abstract class Shape {
    String color;

    Shape(String color) {
        this.color = color;
    }

    abstract void draw();
}
