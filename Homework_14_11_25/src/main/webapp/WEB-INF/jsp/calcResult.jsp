<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Результат вычисления</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
            background-color: #f5f5f5;
        }
        .container {
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        .result-box {
            margin: 20px 0;
            padding: 20px;
            background-color: #e8f5e9;
            border-radius: 5px;
        }
        .numbers {
            color: #666;
            font-size: 1.1em;
        }
        .result {
            color: #2196F3;
            font-weight: bold;
            font-size: 1.5em;
            margin-top: 10px;
        }
        .operation {
            color: #4CAF50;
            font-size: 1.2em;
        }
        .btn {
            display: inline-block;
            padding: 10px 20px;
            margin: 5px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }
        .btn:hover {
            background-color: #45a049;
        }
        .btn-secondary {
            background-color: #607D8B;
        }
        .btn-secondary:hover {
            background-color: #546E7A;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Результат вычисления</h1>

        <div class="result-box">
            <h2 class="operation">${calcResult.operation}</h2>

            <p class="numbers">
                Числа:
                <c:forEach var="num" items="${calcResult.numbers}" varStatus="loop">
                    <strong>${num}</strong>
                    <c:if test="${!loop.last}"> ${calcResult.operationSymbol} </c:if>
                </c:forEach>
            </p>

            <p class="result">Результат: ${calcResult.result}</p>
        </div>

        <div class="nav">
            <a href="javascript:history.back()" class="btn btn-secondary">Вернуться</a>
            <a href="/" class="btn">На главную</a>
        </div>
    </div>
</body>
</html>