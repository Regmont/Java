package servlets;

import beans.CalculationResult;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * Сервлет-калькулятор.
 */
@WebServlet(name = "CalculatorServlet", urlPatterns = {"/calculator"})
public class CalculatorServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            double num1 = Double.parseDouble(request.getParameter("num1"));
            double num2 = Double.parseDouble(request.getParameter("num2"));
            String operation = request.getParameter("operation");

            double result;
            String operationName;
            String operationSymbol;

            switch (operation) {
                case "add":
                    result = num1 + num2;
                    operationName = "Сложение";
                    operationSymbol = "+";
                    break;
                case "subtract":
                    result = num1 - num2;
                    operationName = "Вычитание";
                    operationSymbol = "-";
                    break;
                case "multiply":
                    result = num1 * num2;
                    operationName = "Умножение";
                    operationSymbol = "*";
                    break;
                case "divide":
                    if (num2 == 0) {
                        request.setAttribute("errorMessage", "Деление на ноль невозможно");
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/error.html");
                        dispatcher.forward(request, response);
                        return;
                    }
                    result = num1 / num2;
                    operationName = "Деление";
                    operationSymbol = "/";
                    break;
                case "power":
                    result = Math.pow(num1, num2);
                    operationName = "Возведение в степень";
                    operationSymbol = "^";
                    break;
                case "remainder":
                    result = num1 % num2;
                    operationName = "Остаток от деления";
                    operationSymbol = "%";
                    break;
                default:
                    throw new IllegalArgumentException("Неизвестная операция");
            }

            CalculationResult calcResult = new CalculationResult(
                    new double[]{num1, num2},
                    result,
                    operationName,
                    operationSymbol
            );

            request.setAttribute("calcResult", calcResult);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/calcResult.jsp");
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
