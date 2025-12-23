package com.library.model;

public class Book {
    private final String title;
    private final String author;
    private final int year;
    private final String isbn;
    private boolean isAvailable;

    public Book(String title, String author, int year, String isbn) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.isbn = isbn;
        this.isAvailable = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return title + " | " + author + " | " + year + " | " + isbn + " | " + (isAvailable ? "Доступна" : "Взята");
    }

    public String toHtmlRow() {
        String color = isAvailable ? "green" : "red";
        String status = isAvailable ? "Доступна" : "Взята";

        return "<tr><td>" + title + "</td><td>" + author + "</td><td>" + year + "</td><td>" + isbn +
                "</td><td><span style=\"color:" + color + ";\">" + status + "</span></td></tr>";
    }
}