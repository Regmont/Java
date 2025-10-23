import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("введите вашу оценку: ");
        int score = scanner.nextInt();

        String qualification = "";

        if (score >= 90) {
            qualification = "A";
        }
        else {
            if (score >= 80) {
                qualification = "B";
            }
            else {
                if (score >= 70) {
                    qualification = "C";
                }
                else {
                    if (score >= 60) {
                        qualification = "D";
                    }
                    else {
                        qualification = "F";
                    }
                }
            }
        }

        System.out.println("Ваша оценка: " + score + ", ее квалификация: " +  qualification);

        scanner.close();
    }
}