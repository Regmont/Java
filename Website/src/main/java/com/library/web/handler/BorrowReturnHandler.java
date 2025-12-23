package com.library.web.handler;

import com.library.service.Library;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class BorrowReturnHandler implements HttpHandler {
    private final Library library;
    private final String actionType;

    public BorrowReturnHandler(Library library, String actionType) {
        this.library = library;
        this.actionType = actionType;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String query = exchange.getRequestURI().getQuery();
        Map<String, String> params = parseQuery(query);
        String isbn = params.get("isbn");

        if (isbn != null && !isbn.isEmpty()) {
            String message;

            if (actionType.equals("borrow")) {
                message = library.borrowBook(isbn);
            } else {
                message = library.returnBook(isbn);
            }

            String redirectUrl = "/?message=" + URLEncoder.encode(message, StandardCharsets.UTF_8);
            exchange.getResponseHeaders().set("Location", redirectUrl);
            exchange.sendResponseHeaders(303, -1);
        }

        exchange.close();
    }

    private Map<String, String> parseQuery(String query) {
        if (query == null) {
            return java.util.Collections.emptyMap();
        }

        return java.util.stream.Stream.of(query.split("&"))
                .map(pair -> pair.split("="))
                .filter(arr -> arr.length > 1)
                .collect(java.util.stream.Collectors.toMap(
                        arr -> arr[0],
                        arr -> arr[1]
                ));
    }
}