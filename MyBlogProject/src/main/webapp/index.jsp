<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList, models.Message" %>
<html>
<head>
    <title>Мини-блог</title>
</head>
<body>
    <%
        String user = (String) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }
    %>

    <h1>Привет, <%= user %>!</h1>

    <form action="add-message" method="post">
        Сообщение: <input type="text" name="message" size="50">
        <input type="submit" value="Отправить">
    </form>

    <hr>

    <h2>Сообщения:</h2>
    <%
        ArrayList<Message> messages =
            (ArrayList<Message>) application.getAttribute("messages");
        if (messages != null && !messages.isEmpty()) {
            for (Message msg : messages) {
    %>
        <div style="border: 1px solid #ccc; margin: 5px; padding: 10px;">
            <strong><%= msg.getAuthor() %></strong>
            [<%= msg.getTime() %>]:<br>
            <%= msg.getText() %>
        </div>
    <%
            }
        } else {
    %>
        <p>Сообщений пока нет</p>
    <%
        }
    %>

    <br>
    <a href="logout">Выйти</a>
</body>
</html>