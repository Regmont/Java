import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ClientRunner {
    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

        Dictionary dictionary = new Dictionary();
        Scanner scanner = new Scanner(System.in);

        dictionary.initializeDictionary();

        while (true) {
            System.out.println("\nRussian-English dictionary");
            System.out.println("1. Show word translation");
            System.out.println("2. Add word");
            System.out.println("3. Replace word");
            System.out.println("4. Delete word");
            System.out.println("5. Add translation");
            System.out.println("6. Replace translations");
            System.out.println("7. Delete translation");
            System.out.println("8. Top 10 popular words");
            System.out.println("9. Top 10 unpopular words");
            System.out.println("0. Exit");
            System.out.print("Select action: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> dictionary.showWordTranslation();
                    case 2 -> dictionary.addWord();
                    case 3 -> dictionary.replaceWord();
                    case 4 -> dictionary.deleteWord();
                    case 5 -> dictionary.addTranslation();
                    case 6 -> dictionary.replaceTranslations();
                    case 7 -> dictionary.deleteTranslation();
                    case 8 -> dictionary.showTopPopular();
                    case 9 -> dictionary.showTopUnpopular();
                    case 0 -> {
                        return;
                    }
                    default -> System.out.println("Invalid choice!");
                }
            } catch (Exception e) {
                System.out.println("Error: enter a number!");
                scanner.nextLine();
            }
        }
    }
}