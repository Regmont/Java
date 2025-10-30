package Math;

public class Array implements IMath {
    private final int[] elements;

    public Array(int[] elements) {
        this.elements = elements != null ? elements.clone() : new int[0];
    }

    public int[] getElements() {
        return elements.clone();
    }

    public int getLength() {
        return elements.length;
    }

    @Override
    public int Max() {
        if (elements.length == 0) {
            throw new IllegalStateException("Array is empty");
        }

        int max = elements[0];

        for (int i = 1; i < elements.length; i++) {
            if (elements[i] > max) {
                max = elements[i];
            }
        }

        return max;
    }

    @Override
    public int Min() {
        if (elements.length == 0) {
            throw new IllegalStateException("Array is empty");
        }

        int min = elements[0];

        for (int i = 1; i < elements.length; i++) {
            if (elements[i] < min) {
                min = elements[i];
            }
        }

        return min;
    }

    @Override
    public float Avg() {
        if (elements.length == 0) {
            throw new IllegalStateException("Array is empty");
        }

        int sum = 0;

        for (int element : elements) {
            sum += element;
        }

        return (float) sum / elements.length;
    }
}