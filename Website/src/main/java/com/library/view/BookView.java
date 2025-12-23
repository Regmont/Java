package com.library.view;

import com.library.model.Book;
import java.util.ArrayList;

public class BookView {
    public static String renderBookTable(ArrayList<Book> books, String title) {
        if (books.isEmpty()) {
            return "<p>" + title + "</p>";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("<h2>").append(title).append("</h2>");
        sb.append("<table border='1'><tr><th>Название</th><th>Автор</th><th>Год</th><th>ISBN</th><th>Статус</th></tr>");

        for (Book book : books) {
            sb.append(book.toHtmlRow());
        }

        sb.append("</table>");

        return sb.toString();
    }

    public static String renderBookTable(Book book, String title) {
        if (book == null) {
            return "<p>Книга не найдена</p>";
        }

        ArrayList<Book> list = new ArrayList<>();
        list.add(book);

        return renderBookTable(list, title);
    }
}