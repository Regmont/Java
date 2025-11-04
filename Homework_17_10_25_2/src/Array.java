import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Random;

public class Array<T extends Comparable<T>> {
    private final T[] array;
    private final int size;
    private final Scanner scanner;
    private final Random random;

    @SuppressWarnings("unchecked")
    public Array(int size) {
        this.size = size;
        this.array = (T[]) new Comparable[size];
        this.scanner = new Scanner(System.in);
        this.random = new Random();
    }

    public void fillFromKeyboard(Class<T> type) {
        System.out.println("Enter " + size + " elements of the array:");
        for (int i = 0; i < size; i++) {
            System.out.print("Element " + (i + 1) + ": ");
            if (type == Integer.class) {
                array[i] = type.cast(scanner.nextInt());
            } else if (type == Double.class) {
                array[i] = type.cast(scanner.nextDouble());
            } else if (type == String.class) {
                array[i] = type.cast(scanner.next());
            }
        }
    }

    @SuppressWarnings("unchecked")
    public void fillWithRandomNumbers(Class<T> type) {
        for (int i = 0; i < size; i++) {
            if (type == Integer.class) {
                array[i] = (T) Integer.valueOf(random.nextInt(100));
            } else if (type == Double.class) {
                array[i] = (T) Double.valueOf(random.nextDouble() * 100);
            }
        }
    }

    public void display() {
        System.out.print("Array: [");
        for (int i = 0; i < size; i++) {
            System.out.print(array[i]);
            if (i < size - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    public T findMax() {
        if (size == 0) return null;

        T max = array[0];
        for (int i = 1; i < size; i++) {
            if (array[i].compareTo(max) > 0) {
                max = array[i];
            }
        }
        return max;
    }

    public T findMin() {
        if (size == 0) return null;

        T min = array[0];
        for (int i = 1; i < size; i++) {
            if (array[i].compareTo(min) < 0) {
                min = array[i];
            }
        }
        return min;
    }

    public double calculateAverage() {
        if (size == 0) return 0;

        if (array[0] instanceof Number) {
            double sum = 0;
            for (int i = 0; i < size; i++) {
                sum += ((Number) array[i]).doubleValue();
            }
            return sum / size;
        }
        return 0;
    }

    public void sortAscending() {
        Arrays.sort(array, 0, size);
    }

    public void sortDescending() {
        Arrays.sort(array, 0, size, Comparator.reverseOrder());
    }

    public int binarySearch(T key) {
        return Arrays.binarySearch(array, 0, size, key);
    }

    public void replaceValue(int index, T newValue) {
        if (index >= 0 && index < size) {
            array[index] = newValue;
        } else {
            System.out.println("Wrong index!");
        }
    }

    public T[] getArray() {
        return array;
    }

    public int getSize() {
        return size;
    }
}