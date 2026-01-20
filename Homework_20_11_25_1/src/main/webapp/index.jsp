<%@ page import="com.example.Manufacturer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Создаем объект прямо в JSP
    Manufacturer manufacturer = new Manufacturer();
%>
<!DOCTYPE html>
<html>
<head>
    <title>Производитель ноутбуков</title>
    <style>
        /* МЕНЮ */
        .navbar {
            background: #2c3e50;
            color: white;
            padding: 20px;
            text-align: center;
            font-size: 24px;
            font-weight: bold;
            margin-bottom: 30px;
        }

        /* КОНТЕНТ */
        .container {
            max-width: 1000px;
            margin: 0 auto;
            padding: 30px;
            background: white;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }

        /* ИНФО */
        .info-block {
            display: flex;
            gap: 40px;
            margin-top: 20px;
        }

        .logo img {
            width: 250px;
            border: 2px solid #ddd;
            padding: 10px;
        }

        .details div {
            margin: 15px 0;
            padding: 10px 0;
            border-bottom: 1px solid #eee;
        }

        .label {
            color: #666;
            font-weight: bold;
        }

        .value {
            font-size: 18px;
            margin-top: 5px;
        }
    </style>
</head>
<body>

    <!-- МЕНЮ -->
    <div class="navbar">Производитель ноутбуков</div>

    <!-- КОНТЕНТ -->
    <div class="container">
        <h1>Общая информация</h1>

        <div class="info-block">
            <div class="logo">
                <img src="<%= manufacturer.getLogoPath() %>" alt="Логотип">
            </div>

            <div class="details">
                <div>
                    <div class="label">Название производителя:</div>
                    <div class="value"><%= manufacturer.getName() %></div>
                </div>

                <div>
                    <div class="label">Страна штаб-квартиры:</div>
                    <div class="value"><%= manufacturer.getCountry() %></div>
                </div>

                <div>
                    <div class="label">Количество сотрудников:</div>
                    <div class="value"><%= manufacturer.getEmployeeCount() %> человек</div>
                </div>

                <div>
                    <div class="label">Краткая информация:</div>
                    <div class="value"><%= manufacturer.getDescription() %></div>
                </div>
            </div>
        </div>
    </div>

    <div style="text-align:center; margin-top:40px; color:#666;">
        © 2024 Производитель ноутбуков
    </div>

</body>
</html>