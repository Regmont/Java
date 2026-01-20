<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Результат - Максимум</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
        }
        .container {
            background: white;
            padding: 40px;
            border-radius: 15px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.2);
        }
        .header {
            text-align: center;
            margin-bottom: 30px;
            color: #333;
        }
        .header h1 {
            margin: 0;
            font-size: 28px;
            color: #4a5568;
        }
        .header h2 {
            margin: 10px 0 0 0;
            font-size: 18px;
            color: #718096;
            font-weight: normal;
        }
        .numbers-display {
            display: flex;
            justify-content: center;
            gap: 20px;
            margin: 30px 0;
            flex-wrap: wrap;
        }
        .number-box {
            width: 100px;
            height: 100px;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 24px;
            font-weight: bold;
            border-radius: 10px;
            background: #f7fafc;
            border: 2px solid #e2e8f0;
            transition: all 0.3s ease;
        }
        .max-number {
            background: linear-gradient(135deg, #4299e1 0%, #3182ce 100%);
            color: white;
            border: none;
            transform: scale(1.1);
            box-shadow: 0 5px 15px rgba(66, 153, 225, 0.4);
        }
        .result-section {
            text-align: center;
            margin: 40px 0;
            padding: 30px;
            background: linear-gradient(135deg, #f6d365 0%, #fda085 100%);
            border-radius: 10px;
            color: white;
        }
        .result-label {
            font-size: 18px;
            margin-bottom: 10px;
            opacity: 0.9;
        }
        .result-value {
            font-size: 42px;
            font-weight: bold;
            margin: 0;
        }
        .navigation {
            display: flex;
            justify-content: center;
            gap: 15px;
            margin-top: 30px;
        }
        .btn {
            padding: 12px 30px;
            border: none;
            border-radius: 8px;
            font-size: 16px;
            font-weight: 600;
            cursor: pointer;
            transition: all 0.3s ease;
            text-decoration: none;
            display: inline-block;
        }
        .btn-primary {
            background: linear-gradient(135deg, #48bb78 0%, #38a169 100%);
            color: white;
        }
        .btn-primary:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(72, 187, 120, 0.4);
        }
        .btn-secondary {
            background: #e2e8f0;
            color: #4a5568;
        }
        .btn-secondary:hover {
            background: #cbd5e0;
        }
        .calculation-details {
            background: #edf2f7;
            padding: 20px;
            border-radius: 8px;
            margin: 20px 0;
        }
        .calculation-details p {
            margin: 10px 0;
            font-size: 16px;
        }
        .formula {
            font-family: 'Courier New', monospace;
            background: white;
            padding: 15px;
            border-radius: 5px;
            margin: 10px 0;
            font-size: 18px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>Результат вычисления максимума</h1>
            <h2>Из трёх чисел</h2>
        </div>

        <div class="numbers-display">
            <c:forEach var="num" items="${calcResult.numbers}" varStatus="loop">
                <div class="number-box ${num == calcResult.result ? 'max-number' : ''}">
                    ${num}
                </div>
            </c:forEach>
        </div>

        <div class="result-section">
            <div class="result-label">Максимальное значение:</div>
            <div class="result-value">${calcResult.result}</div>
        </div>

        <div class="calculation-details">
            <h3>Детали вычисления:</h3>
            <p><strong>Формула:</strong> max(a, b, c) = наибольшее из трёх чисел</p>
            <p><strong>Применено:</strong> Math.max(${calcResult.numbers[0]}, Math.max(${calcResult.numbers[1]}, ${calcResult.numbers[2]}))</p>
            <div class="formula">
                max = Math.max(${calcResult.numbers[0]}, Math.max(${calcResult.numbers[1]}, ${calcResult.numbers[2]}))
            </div>
            <p><strong>Результат:</strong> ${calcResult.numbers[0]}, ${calcResult.numbers[1]}, ${calcResult.numbers[2]} → ${calcResult.result}</p>
        </div>

        <div class="navigation">
            <a href="/forms/max-form.html" class="btn btn-primary">Новое вычисление</a>
            <a href="/forms/stats-form.html" class="btn btn-secondary">Другие операции</a>
            <a href="/" class="btn btn-secondary">На главную</a>
        </div>
    </div>
</body>
</html>