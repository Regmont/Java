public class ClientRunner {
    public static void main(String[] args) {
        Array<Double> doubleArray = new Array<>(5);
        doubleArray.fillWithRandomNumbers(1.0, 100.0);
        System.out.print("Array elements: ");
        doubleArray.display();
        System.out.println("Max value: " + doubleArray.findMax());
        System.out.println("Min value: " + doubleArray.findMin());
        System.out.printf("Average: %.2f\n\n", doubleArray.calculateAverage());

        Array<Integer> intArray = new Array<>(5);
        intArray.fillWithRandomNumbers(1, 100);
        System.out.print("Array elements: ");
        intArray.display();
        System.out.println("Max value: " + intArray.findMax());
        System.out.println("Min value: " + intArray.findMin());
        System.out.printf("Average: %.2f\n", intArray.calculateAverage());
    }
}