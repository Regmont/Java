<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.pizza.Storage" %>
<%@ page import="com.pizza.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
    request.setCharacterEncoding("UTF-8");

    if ("POST".equals(request.getMethod())) {
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String pizza = request.getParameter("pizza");
        String priceStr = request.getParameter("price");

        if (name == null || phone == null || email == null ||
            address == null || pizza == null || priceStr == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        try {
            double price = Double.parseDouble(priceStr);
            Order order = new Order(name, phone, email, address, pizza, price);
            Storage.saveOrder(order);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    List<Order> orders = Storage.getAllOrders();
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Подтверждение заказа</title>
    <style>
        body { font-family: Arial; margin: 40px; }
        .success { color: #00b894; font-size: 24px; }
        .order { border: 1px solid #ddd; padding: 15px; margin: 10px 0; }
        a { color: #0984e3; text-decoration: none; }
        a:hover { text-decoration: underline; }
    </style>
</head>
<body>
    <% if ("POST".equals(request.getMethod())) { %>
        <div class="success">Заказ успешно оформлен!</div>
        <p>Спасибо за заказ! Пицца будет доставлена в течение часа.</p>
        <hr>
    <% } %>

    <h2>Все заказы (<%= orders.size() %>)</h2>

    <% if (orders.isEmpty()) { %>
        <p>Заказов пока нет.</p>
    <% } else {
        for (Order order : orders) { %>
            <div class="order">
                <strong># <%= order.getCustomerName() %></strong><br>
                Пицца: <%= order.getPizzaName() %><br>
                Адрес: <%= order.getAddress() %><br>
                Телефон: <%= order.getPhone() %><br>
                Дата: <%= sdf.format(order.getOrderDate()) %><br>
                Сумма: <%= String.format("%.0f", order.getTotalPrice()) %>₽
            </div>
        <% }
    } %>

    <br>
    <a href="index.jsp">← Вернуться к выбору пиццы</a>
</body>
</html>