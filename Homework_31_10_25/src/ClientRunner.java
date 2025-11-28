import java.io.*;
import java.util.*;

public class ClientRunner {

    public static void Task1() {
        final String FILE_PATH1 = "file1.txt";
        final String FILE_PATH2 = "file2.txt";

        try (BufferedReader reader1 = new BufferedReader(new FileReader(FILE_PATH1));
             BufferedReader reader2 = new BufferedReader(new FileReader(FILE_PATH2))) {

            String line1, line2;
            int lineNumber = 1;

            while ((line1 = reader1.readLine()) != null && (line2 = reader2.readLine()) != null) {
                if (!line1.equals(line2)) {
                    System.out.println("Mismatch at line " + lineNumber);
                    System.out.println("File 1: " + line1);
                    System.out.println("File 2: " + line2);
                    return;
                }
                lineNumber++;
            }

            if (reader1.readLine() != null || reader2.readLine() != null) {
                System.out.println("Files have different number of lines");
            } else {
                System.out.println("Files are identical");
            }

        } catch (IOException e) {
            System.out.println("Error reading files: " + e.getMessage());
        }
    }

    public static void Task2() {
        final String FILE_PATH = "file3.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String longestLine = "";
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.length() > longestLine.length()) {
                    longestLine = currentLine;
                }
            }

            System.out.println("Longest line length: " + longestLine.length());
            System.out.println("Longest line: " + longestLine);

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public static void Task3() {
        final String FILE_PATH = "arrays.txt";

        List<int[]> arrays = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] numbers = line.trim().split("\\s+");
                int[] array = new int[numbers.length];

                for (int i = 0; i < numbers.length; i++) {
                    array[i] = Integer.parseInt(numbers[i]);
                }

                arrays.add(array);
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        } catch (NumberFormatException e) {
            System.out.println("Error parsing numbers: " + e.getMessage());
            return;
        }

        if (arrays.isEmpty()) {
            System.out.println("No arrays found in file");
            return;
        }

        int globalMax = Integer.MIN_VALUE;
        int globalMin = Integer.MAX_VALUE;
        int totalSum = 0;

        for (int i = 0; i < arrays.size(); i++) {
            int[] array = arrays.get(i);
            System.out.println("Array " + (i + 1) + ": " + Arrays.toString(array));

            int arrayMax = Integer.MIN_VALUE;
            int arrayMin = Integer.MAX_VALUE;
            int arraySum = 0;

            for (int num : array) {
                if (num > arrayMax) arrayMax = num;
                if (num < arrayMin) arrayMin = num;
                arraySum += num;
            }

            System.out.println("Max: " + arrayMax + ", Min: " + arrayMin + ", Sum: " + arraySum);

            if (arrayMax > globalMax) globalMax = arrayMax;
            if (arrayMin < globalMin) globalMin = arrayMin;
            totalSum += arraySum;
        }

        System.out.println("Global max: " + globalMax);
        System.out.println("Global min: " + globalMin);
        System.out.println("Total sum: " + totalSum);
    }

    public static void main(String[] args) {
        Task1();
        System.out.println();
        Task2();
        System.out.println();
        Task3();
    }
}