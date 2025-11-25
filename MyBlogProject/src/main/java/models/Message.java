package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {
    private final String text;
    private final String author;
    private final String time;

    public Message(String text, String author) {
        this.text = text;
        this.author = author;
        this.time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
    }

    public String getText() {
        return text;
    }

    public String getAuthor() {
        return author;
    }

    public String getTime() {
        return time;
    }
}