package servlets;

import beans.CalculationResult;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * Сервлет для вычисления максимума, минимума и среднего арифметического трёх чисел.
 */
@WebServlet(name = "StatsServlet", urlPatterns = {"/stats"})
public class StatsServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            double num1 = Double.parseDouble(request.getParameter("num1"));
            double num2 = Double.parseDouble(request.getParameter("num2"));
            double num3 = Double.parseDouble(request.getParameter("num3"));
            String operation = request.getParameter("operation");

            double result;
            String operationName;
            String operationSymbol;

            switch (operation) {
                case "max":
                    result = Math.max(num1, Math.max(num2, num3));
                    operationName = "Максимальное значение";
                    operationSymbol = "max";
                    break;
                case "min":
                    result = Math.min(num1, Math.min(num2, num3));
                    operationName = "Минимальное значение";
                    operationSymbol = "min";
                    break;
                case "avg":
                    result = (num1 + num2 + num3) / 3;
                    operationName = "Среднее арифметическое";
                    operationSymbol = "avg";
                    break;
                default:
                    throw new IllegalArgumentException("Неизвестная операция");
            }

            CalculationResult calcResult = new CalculationResult(
                    new double[]{num1, num2, num3},
                    result,
                    operationName,
                    operationSymbol
            );

            request.setAttribute("calcResult", calcResult);

            // Определяем, какую JSP использовать
            String jspPage;
            if ("max".equals(operation)) {
                jspPage = "/WEB-INF/jsp/maxResult.jsp";
            } else {
                jspPage = "/WEB-INF/jsp/statsResult.jsp";
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher(jspPage);
            dispatcher.forward(request, response);

        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Пожалуйста, введите корректные числа");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.html");
            dispatcher.forward(request, response);
        } catch (IllegalArgumentException e) {
            request.setAttribute("errorMessage", e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.html");
            dispatcher.forward(request, response);
        }
    }
}
