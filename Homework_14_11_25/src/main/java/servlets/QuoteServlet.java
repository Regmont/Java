package servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * Сервлет для отображения цитаты Линуса Торвальдса.
 */
@WebServlet(name = "QuoteServlet", urlPatterns = {"/quote"})
public class QuoteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("title", "Цитата Линуса Торвальдса");
        request.setAttribute("quote",
                "Bad programmers worry about the code. " +
                        "Good programmers worry about data structures and their relationships");

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/quote.jsp");
        dispatcher.forward(request, response);
    }
}
