package servlets;

import beans.CalculationResult;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * Сервлет для вычисления максимума из трёх чисел.
 */
@WebServlet(name = "MaxServlet", urlPatterns = {"/max"})
public class MaxServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            double num1 = Double.parseDouble(request.getParameter("num1"));
            double num2 = Double.parseDouble(request.getParameter("num2"));
            double num3 = Double.parseDouble(request.getParameter("num3"));

            double max = Math.max(num1, Math.max(num2, num3));

            CalculationResult result = new CalculationResult(
                    new double[]{num1, num2, num3},
                    max,
                    "Максимальное значение",
                    "max"
            );

            request.setAttribute("calcResult", result);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/maxResult.jsp");
            dispatcher.forward(request, response);

        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Пожалуйста, введите корректные числа");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.html");
            dispatcher.forward(request, response);
        }
    }
}
