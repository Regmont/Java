package com.library.web;

import com.library.service.Library;
import com.library.view.BookView;
import com.library.web.handler.*;
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.io.IOException;

public class LibraryWebApp {
    public static void main(String[] args) throws IOException {
        Library library = new Library();

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/", new BookHandler(library));
        server.createContext("/add", new AddBookHandler(library));
        server.createContext("/search-title", new SearchHandler(library, "title"));
        server.createContext("/search-author", new SearchHandler(library, "author"));
        server.createContext("/borrow", new BorrowReturnHandler(library, "borrow"));
        server.createContext("/return", new BorrowReturnHandler(library, "return"));
        server.createContext("/available", exchange -> {
            String html = com.library.view.HtmlTemplates.getHeader() +
                    BookView.renderBookTable(library.getAvailableBooks(), "Доступные книги") +
                    com.library.view.HtmlTemplates.getFooter();

            exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
            exchange.sendResponseHeaders(200, html.getBytes(java.nio.charset.StandardCharsets.UTF_8).length);

            try (java.io.OutputStream os = exchange.getResponseBody()) {
                os.write(html.getBytes(java.nio.charset.StandardCharsets.UTF_8));
            }
        });

        server.start();
        System.out.println("http://localhost:8080/");
    }
}