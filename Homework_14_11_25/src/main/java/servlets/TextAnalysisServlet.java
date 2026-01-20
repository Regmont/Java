package servlets;

import beans.TextStats;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.*;

/**
 * Сервлет для анализа текста (только английские буквы).
 */
@WebServlet(name = "TextAnalysisServlet", urlPatterns = {"/text-analysis"})
public class TextAnalysisServlet extends HttpServlet {

    private final Set<Character> vowelsSet;
    private final Set<Character> consonantsSet;
    private final Set<Character> punctuationSet;

    public TextAnalysisServlet() {
        vowelsSet = new HashSet<>(Arrays.asList(
                'a', 'e', 'i', 'o', 'u',
                'A', 'E', 'I', 'O', 'U'
        ));

        consonantsSet = new HashSet<>();
        for (char c = 'a'; c <= 'z'; c++) {
            if (!vowelsSet.contains(c)) {
                consonantsSet.add(c);
            }
        }
        for (char c = 'A'; c <= 'Z'; c++) {
            if (!vowelsSet.contains(c)) {
                consonantsSet.add(c);
            }
        }

        punctuationSet = new HashSet<>(Arrays.asList(
                '.', ',', '!', '?', ';', ':', '-', '(', ')',
                '"', '\'', '[', ']', '{', '}', '<', '>',
                '/', '\\', '@', '#', '$', '%', '^', '&', '*',
                '_', '+', '=', '|', '~', '`'
        ));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String text = request.getParameter("text");

        if (text == null || text.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Пожалуйста, введите текст");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.html");
            dispatcher.forward(request, response);
            return;
        }

        List<Character> vowels = new ArrayList<>();
        List<Character> consonants = new ArrayList<>();
        List<Character> punctuation = new ArrayList<>();
        List<Character> other = new ArrayList<>();

        for (char c : text.toCharArray()) {
            if (vowelsSet.contains(c) || vowelsSet.contains(Character.toUpperCase(c))) {
                vowels.add(c);
            } else if (consonantsSet.contains(c) || consonantsSet.contains(Character.toUpperCase(c))) {
                consonants.add(c);
            } else if (punctuationSet.contains(c)) {
                punctuation.add(c);
            } else if (Character.isLetter(c)) {
                other.add(c);
            }
        }

        TextStats stats = new TextStats(
                vowels.size(), vowels,
                consonants.size(), consonants,
                punctuation.size(), punctuation
        );

        request.setAttribute("text", text);
        request.setAttribute("stats", stats);
        request.setAttribute("otherChars", other);
        request.setAttribute("otherCount", other.size());

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/textResult.jsp");
        dispatcher.forward(request, response);
    }
}
