import java.util.Scanner;
import java.util.Random;

public class Matrix<T extends Number> {
    private final T[][] matrix;
    private final int rows;
    private final int cols;
    private final Scanner scanner;
    private final Random random;

    @SuppressWarnings("unchecked")
    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.matrix = (T[][]) new Number[rows][cols];
        this.scanner = new Scanner(System.in);
        this.random = new Random();
    }

    public void fillFromKeyboard(Class<T> type) {
        System.out.println("Enter elements of matrix " + rows + "x" + cols + ":");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print("Element [" + i + "][" + j + "]: ");
                if (type == Integer.class) {
                    matrix[i][j] = type.cast(scanner.nextInt());
                } else if (type == Double.class) {
                    matrix[i][j] = type.cast(scanner.nextDouble());
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    public void fillWithRandomValues(Class<T> type) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (type == Integer.class) {
                    matrix[i][j] = (T) Integer.valueOf(random.nextInt(100));
                } else if (type == Double.class) {
                    matrix[i][j] = (T) Double.valueOf(random.nextDouble() * 100);
                }
            }
        }
    }

    public void display() {
        System.out.println("Matrix " + rows + "x" + cols + ":");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.printf("%8.2f", matrix[i][j].doubleValue());
            }
            System.out.println();
        }
    }

    @SuppressWarnings("unchecked")
    public Matrix<T> add(Matrix<T> other) {
        if (this.rows != other.rows || this.cols != other.cols) {
            throw new IllegalArgumentException("Matrices must be of the same size!");
        }

        Matrix<T> result = new Matrix<>(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                double sum = this.matrix[i][j].doubleValue() + other.matrix[i][j].doubleValue();
                if (matrix[i][j] instanceof Integer) {
                    result.matrix[i][j] = (T) Integer.valueOf((int) sum);
                } else {
                    result.matrix[i][j] = (T) Double.valueOf(sum);
                }
            }
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public Matrix<T> subtract(Matrix<T> other) {
        if (this.rows != other.rows || this.cols != other.cols) {
            throw new IllegalArgumentException("Matrices must be of the same size!");
        }

        Matrix<T> result = new Matrix<>(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                double diff = this.matrix[i][j].doubleValue() - other.matrix[i][j].doubleValue();
                if (matrix[i][j] instanceof Integer) {
                    result.matrix[i][j] = (T) Integer.valueOf((int) diff);
                } else {
                    result.matrix[i][j] = (T) Double.valueOf(diff);
                }
            }
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public Matrix<T> multiply(Matrix<T> other) {
        if (this.cols != other.rows) {
            throw new IllegalArgumentException("The number of columns of the first matrix must be equal to the number of rows of the second matrix!");
        }

        Matrix<T> result = new Matrix<>(this.rows, other.cols);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < other.cols; j++) {
                double sum = 0;
                for (int k = 0; k < this.cols; k++) {
                    sum += this.matrix[i][k].doubleValue() * other.matrix[k][j].doubleValue();
                }
                if (matrix[i][j] instanceof Integer) {
                    result.matrix[i][j] = (T) Integer.valueOf((int) sum);
                } else {
                    result.matrix[i][j] = (T) Double.valueOf(sum);
                }
            }
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public Matrix<T> divideByNumber(double divisor) {
        if (divisor == 0) {
            throw new IllegalArgumentException("Division by zero is impossible!");
        }

        Matrix<T> result = new Matrix<>(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                double quotient = this.matrix[i][j].doubleValue() / divisor;
                if (matrix[i][j] instanceof Integer) {
                    result.matrix[i][j] = (T) Integer.valueOf((int) quotient);
                } else {
                    result.matrix[i][j] = (T) Double.valueOf(quotient);
                }
            }
        }
        return result;
    }

    public T findMax() {
        T max = matrix[0][0];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j].doubleValue() > max.doubleValue()) {
                    max = matrix[i][j];
                }
            }
        }
        return max;
    }

    public T findMin() {
        T min = matrix[0][0];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j].doubleValue() < min.doubleValue()) {
                    min = matrix[i][j];
                }
            }
        }
        return min;
    }

    public double calculateAverage() {
        double sum = 0;
        int totalElements = rows * cols;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sum += matrix[i][j].doubleValue();
            }
        }
        return sum / totalElements;
    }

    public T[][] getMatrix() {
        return matrix;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
}