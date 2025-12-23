package com.library.web.handler;

import com.library.model.Book;
import com.library.service.Library;
import com.library.view.HtmlTemplates;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class AddBookHandler implements HttpHandler {
    private final Library library;

    public AddBookHandler(Library library) {
        this.library = library;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if ("GET".equals(exchange.getRequestMethod())) {
            String html = HtmlTemplates.getHeader() + HtmlTemplates.getAddBookForm() + HtmlTemplates.getFooter();
            sendResponse(exchange, html);
        } else if ("POST".equals(exchange.getRequestMethod())) {
            String requestBody = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
            Map<String, String> params = parseFormData(requestBody);

            String title = params.get("title");
            String author = params.get("author");
            int year = Integer.parseInt(params.get("year"));
            String isbn = params.get("isbn");

            Book newBook = new Book(title, author, year, isbn);
            library.addBook(newBook);

            exchange.getResponseHeaders().set("Location", "/");
            exchange.sendResponseHeaders(303, -1);
            exchange.close();
        }
    }

    private Map<String, String> parseFormData(String formData) {
        return java.util.stream.Stream.of(formData.split("&"))
                .map(pair -> pair.split("="))
                .collect(java.util.stream.Collectors.toMap(
                        arr -> java.net.URLDecoder.decode(arr[0], StandardCharsets.UTF_8),
                        arr -> arr.length > 1 ? java.net.URLDecoder.decode(arr[1], StandardCharsets.UTF_8) : ""
                ));
    }

    private void sendResponse(HttpExchange exchange, String content) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
        exchange.sendResponseHeaders(200, content.getBytes(StandardCharsets.UTF_8).length);

        try (OutputStream os = exchange.getResponseBody()) {
            os.write(content.getBytes(StandardCharsets.UTF_8));
        }
    }
}