<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Список магазинов</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        table { border-collapse: collapse; width: 100%; margin-top: 20px; }
        th, td { border: 1px solid #ddd; padding: 12px; text-align: left; }
        th { background-color: #f2f2f2; }
        tr:hover { background-color: #f5f5f5; }
        a { color: #0066cc; text-decoration: none; }
        a:hover { text-decoration: underline; }
        .add-btn { background: #4CAF50; color: white; padding: 10px 15px;
                   border: none; border-radius: 4px; cursor: pointer; margin-bottom: 20px; }
        .add-btn:hover { background: #45a049; }
    </style>
</head>
<body>
    <h1>Магазины</h1>

    <a href="add" class="add-btn">Добавить магазин</a>

    <table>
        <tr>
            <th>Название</th>
            <th>Адрес</th>
            <th>Телефон</th>
            <th>Категория</th>
            <th>Действия</th>
        </tr>
        <c:forEach items="${shops}" var="shop">
            <tr>
                <td>${shop.name}</td>
                <td>${shop.address}</td>
                <td>${shop.phone}</td>
                <td>${shop.category}</td>
                <td>
                    <a href="shops/${shop.id}">Подробнее</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>