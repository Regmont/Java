public class ClientRunner {
    public static void main(String[] args) {
        Array<Integer> intArray = new Array<>(5);
        intArray.fillWithRandomNumbers(Integer.class);
        intArray.display();

        System.out.println("Max: " + intArray.findMax());
        System.out.println("Min: " + intArray.findMin());
        System.out.println("Average: " + intArray.calculateAverage());

        intArray.sortAscending();
        System.out.print("Sorted: ");
        intArray.display();

        Matrix<Integer> matrix1 = new Matrix<>(2, 2);
        matrix1.fillWithRandomValues(Integer.class);
        System.out.println("Matrix 1:");
        matrix1.display();

        Matrix<Integer> matrix2 = new Matrix<>(2, 2);
        matrix2.fillWithRandomValues(Integer.class);
        System.out.println("Matrix 2:");
        matrix2.display();

        Matrix<Integer> sumMatrix = matrix1.add(matrix2);
        System.out.println("Addition result:");
        sumMatrix.display();
    }
}