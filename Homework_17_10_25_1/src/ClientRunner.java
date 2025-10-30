import Math.Array;
import Animals.Crocodile;
import Animals.Kangaroo;
import Animals.Tiger;
import People.Builder;
import People.Pilot;
import People.Sailor;

public class ClientRunner {
    public static void main(String[] args) {
        ClientRunner.Exercise_1();
        ClientRunner.Exercise_2();
        ClientRunner.Exercise_6();
    }

    private static void Exercise_1() {
        Builder builder = new Builder("Steve", 43, "foreman");
        Sailor sailor = new Sailor("Bob", 29, "Lieutenant");
        Pilot pilot = new Pilot("Greg", 34, 2200);

        System.out.println(builder);
        System.out.println(sailor);
        System.out.println(pilot);
    }

    private static void Exercise_2() {
        Tiger tiger = new Tiger("Stripe", 160, 55);
        Crocodile crocodile = new Crocodile("Brutus", 570, 450);
        Kangaroo kangaroo = new Kangaroo("Pogo", 47, 150);

        System.out.println(tiger);
        System.out.println(crocodile);
        System.out.println(kangaroo);
    }

    private static void Exercise_6() {
        int[] testData = {5, 2, 8, 1, 9, 3};
        Array array = new Array(testData);

        System.out.println("Array elements: " + java.util.Arrays.toString(array.getElements()));
        System.out.println("Array length: " + array.getLength());
        System.out.println("Maximum value: " + array.Max());
        System.out.println("Minimum value: " + array.Min());
        System.out.println("Average value: " + array.Avg());
    }
}