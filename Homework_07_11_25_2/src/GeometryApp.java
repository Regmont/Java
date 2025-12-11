import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class GeometryApp {

    public static class Triangle {
        private final double base;
        private final double height;

        public Triangle(double base, double height) {
            this.base = base;
            this.height = height;
        }

        public double getArea() {
            return 0.5 * base * height;
        }
    }

    public static class Rectangle {
        private final double width;
        private final double height;

        public Rectangle(double width, double height) {
            this.width = width;
            this.height = height;
        }

        public double getArea() {
            return width * height;
        }
    }

    public static class Square {
        private final double side;

        public Square(double side) {
            this.side = side;
        }

        public double getArea() {
            return side * side;
        }
    }

    public static class Rhombus {
        private final double diagonal1;
        private final double diagonal2;

        public Rhombus(double diagonal1, double diagonal2) {
            this.diagonal1 = diagonal1;
            this.diagonal2 = diagonal2;
        }

        public double getArea() {
            return (diagonal1 * diagonal2) / 2;
        }
    }

    public static class GeometryTest {

        @Test
        public void testTriangleArea() {
            Triangle triangle = new Triangle(10, 5);
            assertEquals(25.0, triangle.getArea(), 0.001);
        }

        @Test
        public void testRectangleArea() {
            Rectangle rectangle = new Rectangle(10, 5);
            assertEquals(50.0, rectangle.getArea(), 0.001);
        }

        @Test
        public void testSquareArea() {
            Square square = new Square(7);
            assertEquals(49.0, square.getArea(), 0.001);
        }

        @Test
        public void testRhombusArea() {
            Rhombus rhombus = new Rhombus(8, 6);
            assertEquals(24.0, rhombus.getArea(), 0.001);
        }

        @Test
        public void testTriangleAreaZero() {
            Triangle triangle = new Triangle(0, 5);
            assertEquals(0.0, triangle.getArea(), 0.001);
        }

        @Test
        public void testRectangleAreaZero() {
            Rectangle rectangle = new Rectangle(0, 5);
            assertEquals(0.0, rectangle.getArea(), 0.001);
        }

        @Test
        public void testSquareAreaZero() {
            Square square = new Square(0);
            assertEquals(0.0, square.getArea(), 0.001);
        }

        @Test
        public void testRhombusAreaZero() {
            Rhombus rhombus = new Rhombus(0, 6);
            assertEquals(0.0, rhombus.getArea(), 0.001);
        }
    }
}