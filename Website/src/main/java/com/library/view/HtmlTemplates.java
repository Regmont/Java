package com.library.view;

public class HtmlTemplates {
    public static String getHeader() {
        return "<html><head><title>Библиотека</title></head><body>" +
                "<h1>Система учёта библиотеки</h1>" +
                "<div>" +
                "<a href='/'>Главная</a> | " +
                "<a href='/add'>Добавить книгу</a> | " +
                "<a href='/search-title'>Найти по названию</a> | " +
                "<a href='/search-author'>Найти по автору</a> | " +
                "<a href='/available'>Доступные книги</a>" +
                "</div>";
    }

    public static String getFooter() {
        return "</body></html>";
    }

    public static String getAddBookForm() {
        return "<h1>Добавить книгу</h1>" +
                "<form method='POST'>" +
                "Название: <input type='text' name='title' required><br>" +
                "Автор: <input type='text' name='author' required><br>" +
                "Год: <input type='number' name='year' required><br>" +
                "ISBN: <input type='text' name='isbn' required><br>" +
                "<input type='submit' value='Добавить'>" +
                "</form>" +
                "<a href='/'>На главную</a>";
    }

    public static String getSearchForm(String type, String placeholder) {
        return "<h1>Поиск по " + type + "</h1>" +
                "<form method='POST'>" +
                placeholder + ": <input type='text' name='" + type + "' required><br>" +
                "<input type='submit' value='Найти'>" +
                "</form>" +
                "<a href='/'>На главную</a>";
    }
}