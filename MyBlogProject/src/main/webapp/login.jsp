<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Вход</title>
</head>
<body>
    <h2>Вход</h2>
    <form action="login" method="post">
        Логин: <input type="text" name="login"><br>
        Пароль: <input type="password" name="pass"><br>
        <input type="submit" value="Войти">
    </form>

    <% if(request.getParameter("error") != null) { %>
        <p style="color:red">Неверный логин или пароль!</p>
    <% } %>
</body>
</html>