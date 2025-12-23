package com.library.service;

import com.library.model.Book;
import java.util.ArrayList;

public class Library {
    private final ArrayList<Book> books = new ArrayList<>();

    public Library() {
        Book book1 = new Book("Война и мир", "Лев Толстой", 1869,
                "978-5-123456-78-9");
        book1.setAvailable(true);

        Book book2 = new Book("Преступление и наказание", "Фёдор Достоевский", 1866,
                "978-5-987654-32-1");
        book2.setAvailable(true);

        Book book3 = new Book("Мастер и Маргарита", "Михаил Булгаков", 1967,
                "978-5-111222-33-4");
        book3.setAvailable(true);

        Book book4 = new Book("1984", "Джордж Оруэлл", 1949,
                "978-5-555444-33-2");
        book4.setAvailable(false);

        Book book5 = new Book("Маленький принц", "Антуан де Сент-Экзюпери", 1943,
                "978-5-888888-88-8");
        book5.setAvailable(false);

        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book5);
    }

    public void addBook(Book book) { books.add(book); }

    public Book findBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }

        return null;
    }

    public ArrayList<Book> findBooksByAuthor(String author) {
        ArrayList<Book> result = new ArrayList<>();

        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                result.add(book);
            }
        }

        return result;
    }

    public String borrowBook(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                if (book.isAvailable()) {
                    book.setAvailable(false);

                    return "Книга успешно взята";
                }

                return "Книга уже взята";
            }
        }

        return "Книга не найдена";
    }

    public String returnBook(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                if (!book.isAvailable()) {
                    book.setAvailable(true);

                    return "Книга успешно возвращена";
                }

                return "Книга уже доступна";
            }
        }

        return "Книга не найдена";
    }

    public ArrayList<Book> getAllBooks() { return books; }

    public ArrayList<Book> getAvailableBooks() {
        ArrayList<Book> result = new ArrayList<>();

        for (Book book : books) {
            if (book.isAvailable()) {
                result.add(book);
            }
        }

        return result;
    }
}