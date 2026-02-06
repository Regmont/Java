<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.pizza.Storage" %>
<%@ page import="com.pizza.Pizza" %>
<%@ page import="java.util.List" %>
<%
    request.setCharacterEncoding("UTF-8");
    List<Pizza> pizzas = Storage.getAllPizzas();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Заказ пиццы</title>
    <style>
        body { font-family: Arial; margin: 40px; }
        h1 { color: #d63031; }
        .pizza { border: 1px solid #ddd; padding: 20px; margin: 15px 0; width: 300px; }
        button { background: #0984e3; color: white; padding: 10px 20px; border: none; cursor: pointer; }
        button:hover { background: #074b83; }
    </style>
</head>
<body>
    <h1>Выберите пиццу:</h1>

    <% for (Pizza pizza : pizzas) { %>
        <div class="pizza">
            <h3><%= pizza.getName() %> - <%= pizza.getPrice() %>₽</h3>
            <form action="order.jsp" method="get" accept-charset="UTF-8">
                <input type="hidden" name="pizza" value="<%= pizza.getName() %>">
                <button type="submit">Заказать</button>
            </form>
        </div>
    <% } %>

    <hr>
    <p>Всего заказов сделано: <%= Storage.getOrdersCount() %></p>
    <a href="confirmation.jsp?view=all">Посмотреть все заказы</a>
</body>
</html>