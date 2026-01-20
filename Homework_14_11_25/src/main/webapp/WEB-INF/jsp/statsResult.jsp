<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Статистика чисел</title>
    <style>
        body {
            font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, sans-serif;
            max-width: 1000px;
            margin: 0 auto;
            padding: 20px;
            background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
            min-height: 100vh;
        }
        .container {
            background: white;
            padding: 40px;
            border-radius: 20px;
            box-shadow: 0 20px 60px rgba(0, 0, 0, 0.1);
        }
        .header {
            text-align: center;
            margin-bottom: 40px;
        }
        .header h1 {
            margin: 0;
            color: #2d3748;
            font-size: 32px;
        }
        .header p {
            color: #718096;
            font-size: 18px;
            margin-top: 10px;
        }
        .input-numbers {
            display: flex;
            justify-content: center;
            gap: 30px;
            margin: 40px 0;
        }
        .number-card {
            width: 120px;
            height: 120px;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            border-radius: 15px;
            background: #f8fafc;
            border: 3px solid #e2e8f0;
            transition: all 0.3s ease;
        }
        .number-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1);
        }
        .number-label {
            font-size: 14px;
            color: #718096;
            margin-bottom: 5px;
        }
        .number-value {
            font-size: 32px;
            font-weight: bold;
            color: #4a5568;
        }
        .stats-grid {
            display: grid;
            grid-template-columns: repeat(3, 1fr);
            gap: 30px;
            margin: 50px 0;
        }
        .stat-card {
            padding: 30px;
            border-radius: 15px;
            text-align: center;
            transition: all 0.3s ease;
        }
        .stat-card:hover {
            transform: scale(1.05);
        }
        .stat-card.max {
            background: linear-gradient(135deg, #4299e1 0%, #3182ce 100%);
            color: white;
        }
        .stat-card.min {
            background: linear-gradient(135deg, #ed8936 0%, #dd6b20 100%);
            color: white;
        }
        .stat-card.avg {
            background: linear-gradient(135deg, #48bb78 0%, #38a169 100%);
            color: white;
        }
        .stat-title {
            font-size: 18px;
            margin-bottom: 10px;
            opacity: 0.9;
        }
        .stat-value {
            font-size: 48px;
            font-weight: bold;
            margin: 15px 0;
        }
        .stat-formula {
            font-size: 14px;
            opacity: 0.8;
            font-family: 'Courier New', monospace;
            margin-top: 10px;
        }
        .selected-operation {
            text-align: center;
            padding: 20px;
            background: #f0fff4;
            border-radius: 10px;
            margin: 30px 0;
            border-left: 5px solid #48bb78;
        }
        .selected-operation h3 {
            margin: 0;
            color: #2f855a;
        }
        .selected-operation p {
            margin: 10px 0 0 0;
            color: #718096;
        }
        .operation-result {
            background: linear-gradient(135deg, #9f7aea 0%, #805ad5 100%);
            color: white;
            padding: 40px;
            border-radius: 15px;
            text-align: center;
            margin: 40px 0;
        }
        .operation-result h2 {
            margin: 0 0 20px 0;
            font-size: 24px;
        }
        .final-result {
            font-size: 60px;
            font-weight: bold;
            margin: 0;
        }
        .navigation {
            display: flex;
            justify-content: center;
            gap: 20px;
            margin-top: 50px;
            flex-wrap: wrap;
        }
        .btn {
            padding: 15px 40px;
            border: none;
            border-radius: 10px;
            font-size: 16px;
            font-weight: 600;
            cursor: pointer;
            transition: all 0.3s ease;
            text-decoration: none;
            display: inline-block;
        }
        .btn-primary {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
        }
        .btn-primary:hover {
            transform: translateY(-3px);
            box-shadow: 0 10px 25px rgba(102, 126, 234, 0.4);
        }
        .btn-secondary {
            background: #e2e8f0;
            color: #4a5568;
        }
        .btn-secondary:hover {
            background: #cbd5e0;
        }
        .calculation-details {
            background: #f7fafc;
            padding: 30px;
            border-radius: 10px;
            margin: 40px 0;
        }
        .calculation-details h3 {
            color: #4a5568;
            margin-top: 0;
        }
        .details-grid {
            display: grid;
            grid-template-columns: repeat(2, 1fr);
            gap: 20px;
        }
        .detail-item {
            background: white;
            padding: 20px;
            border-radius: 8px;
            border-left: 4px solid #4299e1;
        }
        .detail-title {
            font-weight: 600;
            color: #4a5568;
            margin-bottom: 10px;
        }
        .detail-value {
            font-size: 18px;
            color: #2d3748;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>Статистика трёх чисел</h1>
            <p>Полный анализ введённых значений</p>
        </div>

        <div class="input-numbers">
            <c:forEach var="num" items="${calcResult.numbers}" varStatus="loop">
                <div class="number-card">
                    <div class="number-label">Число ${loop.index + 1}</div>
                    <div class="number-value">${num}</div>
                </div>
            </c:forEach>
        </div>

        <div class="selected-operation">
            <h3>Выбранная операция: ${calcResult.operation}</h3>
            <p>Символ операции: ${calcResult.operationSymbol}</p>
        </div>

        <div class="stats-grid">
            <div class="stat-card max">
                <div class="stat-title">Максимум</div>
                <div class="stat-value">
                    <c:set var="max" value="${Math.max(calcResult.numbers[0], Math.max(calcResult.numbers[1], calcResult.numbers[2]))}" />
                    ${max}
                </div>
                <div class="stat-formula">max(a,b,c)</div>
            </div>

            <div class="stat-card min">
                <div class="stat-title">Минимум</div>
                <div class="stat-value">
                    <c:set var="min" value="${Math.min(calcResult.numbers[0], Math.min(calcResult.numbers[1], calcResult.numbers[2]))}" />
                    ${min}
                </div>
                <div class="stat-formula">min(a,b,c)</div>
            </div>

            <div class="stat-card avg">
                <div class="stat-title">Среднее</div>
                <div class="stat-value">
                    <fmt:formatNumber value="${(calcResult.numbers[0] + calcResult.numbers[1] + calcResult.numbers[2]) / 3}"
                                     pattern="#.##" />
                </div>
                <div class="stat-formula">(a+b+c)/3</div>
            </div>
        </div>

        <div class="operation-result">
            <h2>Результат выбранной операции (${calcResult.operation}):</h2>
            <div class="final-result">${calcResult.result}</div>
        </div>

        <div class="calculation-details">
            <h3>Детали вычислений:</h3>
            <div class="details-grid">
                <div class="detail-item">
                    <div class="detail-title">Числа:</div>
                    <div class="detail-value">${calcResult.numbers[0]}, ${calcResult.numbers[1]}, ${calcResult.numbers[2]}</div>
                </div>
                <div class="detail-item">
                    <div class="detail-title">Сумма чисел:</div>
                    <div class="detail-value">${calcResult.numbers[0] + calcResult.numbers[1] + calcResult.numbers[2]}</div>
                </div>
                <div class="detail-item">
                    <div class="detail-title">Произведение:</div>
                    <div class="detail-value">${calcResult.numbers[0] * calcResult.numbers[1] * calcResult.numbers[2]}</div>
                </div>
                <div class="detail-item">
                    <div class="detail-title">Диапазон:</div>
                    <div class="detail-value">
                        От ${Math.min(calcResult.numbers[0], Math.min(calcResult.numbers[1], calcResult.numbers[2]))}
                        до ${Math.max(calcResult.numbers[0], Math.max(calcResult.numbers[1], calcResult.numbers[2]))}
                    </div>
                </div>
            </div>
        </div>

        <div class="navigation">
            <a href="/forms/stats-form.html" class="btn btn-primary">Новая операция</a>
            <a href="/forms/max-form.html" class="btn btn-secondary">Только максимум</a>
            <a href="/forms/calc-form.html" class="btn btn-secondary">Калькулятор</a>
            <a href="/" class="btn btn-secondary">На главную</a>
        </div>
    </div>
</body>
</html>