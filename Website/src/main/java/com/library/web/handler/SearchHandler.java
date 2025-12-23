package com.library.web.handler;

import com.library.service.Library;
import com.library.view.BookView;
import com.library.view.HtmlTemplates;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class SearchHandler implements HttpHandler {
    private final Library library;
    private final String searchType;

    public SearchHandler(Library library, String searchType) {
        this.library = library;
        this.searchType = searchType;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if ("GET".equals(exchange.getRequestMethod())) {
            String placeholder = searchType.equals("title") ? "Название" : "Автор";
            String html = HtmlTemplates.getHeader() +
                    HtmlTemplates.getSearchForm(searchType, placeholder) +
                    HtmlTemplates.getFooter();
            sendResponse(exchange, html);
        } else if ("POST".equals(exchange.getRequestMethod())) {
            String requestBody = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
            Map<String, String> params = parseFormData(requestBody);
            String searchValue = params.get(searchType);

            String resultHtml;

            if (searchType.equals("title")) {
                resultHtml = BookView.renderBookTable(library.findBookByTitle(searchValue),
                        "Результаты поиска по названию: " + searchValue);
            } else {
                resultHtml = BookView.renderBookTable(library.findBooksByAuthor(searchValue),
                        "Результаты поиска по автору: " + searchValue);
            }

            String html = HtmlTemplates.getHeader() + resultHtml + "<br><a href='/'>На главную</a>" +
                    HtmlTemplates.getFooter();
            sendResponse(exchange, html);
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