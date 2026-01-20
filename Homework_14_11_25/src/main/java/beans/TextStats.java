package beans;

import java.util.List;

/**
 * Класс для хранения статистики текста.
 */
public class TextStats {
    private int vowelCount;
    private List<Character> vowels;
    private int consonantCount;
    private List<Character> consonants;
    private int punctuationCount;
    private List<Character> punctuation;

    public TextStats(int vowelCount, List<Character> vowels,
                     int consonantCount, List<Character> consonants,
                     int punctuationCount, List<Character> punctuation) {
        this.vowelCount = vowelCount;
        this.vowels = vowels;
        this.consonantCount = consonantCount;
        this.consonants = consonants;
        this.punctuationCount = punctuationCount;
        this.punctuation = punctuation;
    }

    public int getVowelCount() { return vowelCount; }
    public void setVowelCount(int vowelCount) { this.vowelCount = vowelCount; }

    public List<Character> getVowels() { return vowels; }
    public void setVowels(List<Character> vowels) { this.vowels = vowels; }

    public int getConsonantCount() { return consonantCount; }
    public void setConsonantCount(int consonantCount) { this.consonantCount = consonantCount; }

    public List<Character> getConsonants() { return consonants; }
    public void setConsonants(List<Character> consonants) { this.consonants = consonants; }

    public int getPunctuationCount() { return punctuationCount; }
    public void setPunctuationCount(int punctuationCount) { this.punctuationCount = punctuationCount; }

    public List<Character> getPunctuation() { return punctuation; }
    public void setPunctuation(List<Character> punctuation) { this.punctuation = punctuation; }
}
