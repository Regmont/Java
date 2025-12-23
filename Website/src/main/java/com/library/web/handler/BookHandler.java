package com.library.web.handler;

import com.library.service.Library;
import com.library.view.BookView;
import com.library.view.HtmlTemplates;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class BookHandler implements HttpHandler {
    private final Library library;

    public BookHandler(Library library) {
        this.library = library;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String html = HtmlTemplates.getHeader() +
                BookView.renderBookTable(library.getAllBooks(), "Все книги:") +
                HtmlTemplates.getFooter();

        sendResponse(exchange, html);
    }

    protected void sendResponse(HttpExchange exchange, String content) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
        exchange.sendResponseHeaders(200, content.getBytes(StandardCharsets.UTF_8).length);

        try (OutputStream os = exchange.getResponseBody()) {
            os.write(content.getBytes(StandardCharsets.UTF_8));
        }
    }
}