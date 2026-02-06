<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.pizza.Storage" %>
<%@ page import="com.pizza.Pizza" %>
<%
    request.setCharacterEncoding("UTF-8");
    String pizzaName = request.getParameter("pizza");

    if (pizzaName == null || pizzaName.isEmpty()) {
        response.sendRedirect("index.jsp");
        return;
    }

    Pizza pizza = Storage.getPizzaByName(pizzaName);

    if (pizza == null) {
        response.sendRedirect("index.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Заказ: <%= pizza.getName() %></title>
    <style>
        body { font-family: Arial; margin: 40px; max-width: 500px; }
        input, textarea { width: 100%; padding: 8px; margin: 5px 0 15px 0; }
        button { background: #00b894; color: white; padding: 10px 20px; border: none; cursor: pointer; }
    </style>
</head>
<body>
    <h1>Заказ пиццы: <%= pizza.getName() %></h1>
    <p>Цена: <strong><%= pizza.getPrice() %>₽</strong></p>

    <form action="confirmation.jsp" method="post">
        <input type="hidden" name="pizza" value="<%= pizza.getName() %>">
        <input type="hidden" name="price" value="<%= pizza.getPrice() %>">

        <label>Имя:</label>
        <input type="text" name="name" required>

        <label>Телефон:</label>
        <input type="tel" name="phone" required>

        <label>Email:</label>
        <input type="email" name="email" required>

        <label>Адрес доставки:</label>
        <textarea name="address" rows="3" required></textarea>

        <button type="submit">Заказать</button>
        <a href="index.jsp" style="margin-left: 20px;">Отмена</a>
    </form>
</body>
</html>