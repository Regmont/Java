import java.util.*;

public class Dictionary {
    private final Map<String, WordEntry> dictionary;
    private final Scanner scanner;

    public Dictionary() {
        dictionary = new HashMap<>();
        scanner = new Scanner(System.in);
    }

    private static class WordEntry {
        private List<String> translations;
        private int accessCount;

        public WordEntry(List<String> translations) {
            this.translations = new ArrayList<>(translations);
            this.accessCount = 0;
        }

        public List<String> getTranslations() {
            accessCount++;
            return new ArrayList<>(translations);
        }

        public void addTranslation(String translation) {
            if (!translations.contains(translation)) {
                translations.add(translation);
            }
        }

        public void removeTranslation(String translation) {
            translations.remove(translation);
        }

        public void setTranslations(List<String> newTranslations) {
            this.translations = new ArrayList<>(newTranslations);
        }

        public int getAccessCount() {
            return accessCount;
        }

        public boolean hasTranslations() {
            return !translations.isEmpty();
        }
    }

    public void initializeDictionary() {
        addInitialWord("привет", Arrays.asList("hello", "hi"));
        addInitialWord("дом", Arrays.asList("house", "home"));
        addInitialWord("кот", Arrays.asList("cat"));
        addInitialWord("собака", Arrays.asList("dog"));
        addInitialWord("книга", Arrays.asList("book"));
        addInitialWord("стол", Arrays.asList("table", "desk"));
        addInitialWord("окно", Arrays.asList("window"));
        addInitialWord("машина", Arrays.asList("car", "vehicle"));
        addInitialWord("город", Arrays.asList("city", "town"));
        addInitialWord("работа", Arrays.asList("work", "job"));

        System.out.println("Dictionary initialized with 10 words.");
    }

    private void addInitialWord(String russianWord, List<String> englishTranslations) {
        dictionary.put(russianWord.toLowerCase(), new WordEntry(englishTranslations));
    }

    public void showWordTranslation() {
        System.out.print("Enter Russian word: ");
        String word = scanner.nextLine().toLowerCase();

        WordEntry entry = dictionary.get(word);
        if (entry != null) {
            List<String> translations = entry.getTranslations();
            System.out.println("Translations of '" + word + "': " + String.join(", ", translations));
            System.out.println("Access count: " + entry.getAccessCount());
        } else {
            System.out.println("Word '" + word + "' not found in dictionary.");
        }
    }

    public void addWord() {
        System.out.print("Enter new Russian word: ");
        String word = scanner.nextLine().toLowerCase();

        if (dictionary.containsKey(word)) {
            System.out.println("Word '" + word + "' already exists in dictionary.");
            return;
        }

        System.out.print("Enter translations separated by commas: ");
        String translationsInput = scanner.nextLine();
        List<String> translations = parseTranslations(translationsInput);

        if (translations.isEmpty()) {
            System.out.println("Error: must specify at least one translation.");
            return;
        }

        dictionary.put(word, new WordEntry(translations));
        System.out.println("Word '" + word + "' successfully added.");
    }

    public void replaceWord() {
        System.out.print("Enter Russian word to replace: ");
        String oldWord = scanner.nextLine().toLowerCase();

        if (!dictionary.containsKey(oldWord)) {
            System.out.println("Word '" + oldWord + "' not found in dictionary.");
            return;
        }

        System.out.print("Enter new Russian word: ");
        String newWord = scanner.nextLine().toLowerCase();

        if (dictionary.containsKey(newWord)) {
            System.out.println("Word '" + newWord + "' already exists in dictionary.");
            return;
        }

        WordEntry entry = dictionary.remove(oldWord);
        dictionary.put(newWord, entry);
        System.out.println("Word '" + oldWord + "' replaced with '" + newWord + "'.");
    }

    public void deleteWord() {
        System.out.print("Enter Russian word to delete: ");
        String word = scanner.nextLine().toLowerCase();

        if (dictionary.containsKey(word)) {
            dictionary.remove(word);
            System.out.println("Word '" + word + "' deleted from dictionary.");
        } else {
            System.out.println("Word '" + word + "' not found in dictionary.");
        }
    }

    public void addTranslation() {
        System.out.print("Enter Russian word: ");
        String word = scanner.nextLine().toLowerCase();

        WordEntry entry = dictionary.get(word);
        if (entry == null) {
            System.out.println("Word '" + word + "' not found in dictionary.");
            return;
        }

        System.out.print("Enter new translation: ");
        String newTranslation = scanner.nextLine().trim();

        if (newTranslation.isEmpty()) {
            System.out.println("Error: translation cannot be empty.");
            return;
        }

        entry.addTranslation(newTranslation);
        System.out.println("Translation '" + newTranslation + "' added to word '" + word + "'.");
    }

    public void replaceTranslations() {
        System.out.print("Enter Russian word: ");
        String word = scanner.nextLine().toLowerCase();

        WordEntry entry = dictionary.get(word);
        if (entry == null) {
            System.out.println("Word '" + word + "' not found in dictionary.");
            return;
        }

        System.out.print("Enter new translations separated by commas: ");
        String translationsInput = scanner.nextLine();
        List<String> newTranslations = parseTranslations(translationsInput);

        if (newTranslations.isEmpty()) {
            System.out.println("Error: must specify at least one translation.");
            return;
        }

        entry.setTranslations(newTranslations);
        System.out.println("Translations of word '" + word + "' successfully replaced.");
    }

    public void deleteTranslation() {
        System.out.print("Enter Russian word: ");
        String word = scanner.nextLine().toLowerCase();

        WordEntry entry = dictionary.get(word);
        if (entry == null) {
            System.out.println("Word '" + word + "' not found in dictionary.");
            return;
        }

        List<String> currentTranslations = entry.getTranslations();
        if (currentTranslations.isEmpty()) {
            System.out.println("Word '" + word + "' has no translations.");
            return;
        }

        System.out.println("Current translations: " + String.join(", ", currentTranslations));
        System.out.print("Enter translation to delete: ");
        String translationToRemove = scanner.nextLine().trim();

        if (entry.hasTranslations()) {
            entry.removeTranslation(translationToRemove);
            System.out.println("Translation '" + translationToRemove + "' deleted.");

            if (!entry.hasTranslations()) {
                System.out.println("Warning: word has no more translations.");
            }
        } else {
            System.out.println("Translation '" + translationToRemove + "' not found.");
        }
    }

    public void showTopPopular() {
        List<Map.Entry<String, WordEntry>> entries = new ArrayList<>(dictionary.entrySet());

        entries.sort((a, b) -> Integer.compare(b.getValue().getAccessCount(),
                a.getValue().getAccessCount()));

        System.out.println("\nTop 10 popular words");
        displayTopWords(entries, 10);
    }

    public void showTopUnpopular() {
        List<Map.Entry<String, WordEntry>> entries = new ArrayList<>(dictionary.entrySet());

        entries.sort((a, b) -> Integer.compare(a.getValue().getAccessCount(),
                b.getValue().getAccessCount()));

        System.out.println("\nTop 10 unpopular words");
        displayTopWords(entries, 10);
    }

    private void displayTopWords(List<Map.Entry<String, WordEntry>> entries, int limit) {
        if (entries.isEmpty()) {
            System.out.println("Dictionary is empty.");
            return;
        }

        int count = Math.min(limit, entries.size());
        for (int i = 0; i < count; i++) {
            Map.Entry<String, WordEntry> entry = entries.get(i);
            String word = entry.getKey();
            WordEntry wordEntry = entry.getValue();
            List<String> translations = wordEntry.getTranslations();

            System.out.printf("%d. %s - %s (access count: %d)%n",
                    i + 1, word, String.join(", ", translations), wordEntry.getAccessCount());
        }
    }

    private List<String> parseTranslations(String input) {
        if (input == null || input.trim().isEmpty()) {
            return new ArrayList<>();
        }

        String[] parts = input.split(",");
        List<String> translations = new ArrayList<>();

        for (String part : parts) {
            String translation = part.trim();
            if (!translation.isEmpty()) {
                translations.add(translation);
            }
        }

        return translations;
    }
}