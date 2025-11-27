import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

public class Array<T extends Comparable<T>> {
    private final T[] data;
    private final Random random;
    private final Scanner scanner;

    @SuppressWarnings("unchecked")
    public Array(int capacity) {
        this.data = (T[]) new Comparable[capacity];
        this.random = new Random();
        this.scanner = new Scanner(System.in);
    }

    public Array(T[] array) {
        this.data = Arrays.copyOf(array, array.length);
        this.random = new Random();
        this.scanner = new Scanner(System.in);
    }

    @SuppressWarnings("unchecked")
    public void fillFromKeyboard() {
        Class<?> componentType = data.getClass().getComponentType();
        for (int i = 0; i < data.length; i++) {
            if (componentType == Integer.class) {
                data[i] = (T) Integer.valueOf(scanner.nextInt());
            } else if (componentType == Double.class) {
                data[i] = (T) Double.valueOf(scanner.nextDouble());
            } else {
                data[i] = (T) scanner.next();
            }
        }
    }

    @SuppressWarnings("unchecked")
    public void fillWithRandomNumbers(T min, T max) {
        if (min instanceof Integer) {
            int minInt = (Integer) min;
            int maxInt = (Integer) max;
            for (int i = 0; i < data.length; i++) {
                data[i] = (T) Integer.valueOf(random.nextInt(maxInt - minInt + 1) + minInt);
            }
        } else if (min instanceof Double) {
            double minDouble = (Double) min;
            double maxDouble = (Double) max;
            for (int i = 0; i < data.length; i++) {
                data[i] = (T) Double.valueOf(minDouble + (maxDouble - minDouble) * random.nextDouble());
            }
        }
    }

    public void display() {
        System.out.println(Arrays.toString(data));
    }

    public T findMax() {
        T max = data[0];
        for (int i = 1; i < data.length; i++) {
            if (data[i].compareTo(max) > 0) max = data[i];
        }
        return max;
    }

    public T findMin() {
        T min = data[0];
        for (int i = 1; i < data.length; i++) {
            if (data[i].compareTo(min) < 0) min = data[i];
        }
        return min;
    }

    public double calculateAverage() {
        double sum = 0.0;
        for (T element : data) {
            sum += ((Number) element).doubleValue();
        }
        return sum / data.length;
    }

    public void sortAscending() {
        Arrays.sort(data);
    }

    public void sortDescending() {
        Arrays.sort(data, Comparator.reverseOrder());
    }

    public int binarySearch(T value) {
        return Arrays.binarySearch(data, value);
    }

    public void replaceValue(T oldValue, T newValue) {
        for (int i = 0; i < data.length; i++) {
            if (data[i].equals(oldValue)) data[i] = newValue;
        }
    }

    public T get(int index) {
        return data[index];
    }

    public int getSize() {
        return data.length;
    }
}