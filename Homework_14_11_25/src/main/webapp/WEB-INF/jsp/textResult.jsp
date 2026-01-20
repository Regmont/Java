<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Анализ текста (Английский)</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            max-width: 1000px;
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
        .text-box {
            margin: 20px 0;
            padding: 20px;
            background-color: #f9f9f9;
            border-left: 4px solid #2196F3;
            word-wrap: break-word;
        }
        .stats-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
            gap: 20px;
            margin: 20px 0;
        }
        .stat-item {
            padding: 15px;
            border-radius: 5px;
            background-color: #f0f8ff;
        }
        .vowels { border-left: 4px solid #4CAF50; }
        .consonants { border-left: 4px solid #FF9800; }
        .punctuation { border-left: 4px solid #9C27B0; }
        .other { border-left: 4px solid #607D8B; }
        .count {
            font-size: 1.5em;
            font-weight: bold;
            color: #2196F3;
        }
        .char-list {
            margin-top: 10px;
            font-family: 'Courier New', monospace;
            font-size: 16px;
            background: white;
            padding: 10px;
            border-radius: 3px;
            max-height: 150px;
            overflow-y: auto;
        }
        .warning {
            background: #fff3cd;
            border: 1px solid #ffeaa7;
            color: #856404;
            padding: 15px;
            border-radius: 5px;
            margin: 20px 0;
        }
        .btn {
            display: inline-block;
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Анализ текста</h1>
        <div class="warning">
            <strong>Внимание:</strong> Анализируются только английские буквы A-Z и a-z.
            Русские и другие неанглийские символы учитываются отдельно.
        </div>

        <div class="text-box">
            <h3>Исходный текст:</h3>
            <p>${text}</p>
        </div>

        <div class="stats-grid">
            <div class="stat-item vowels">
                <h3>Английские гласные</h3>
                <p class="count">Количество: ${stats.vowelCount}</p>
                <div class="char-list">
                    <c:forEach var="vowel" items="${stats.vowels}">
                        <span>${vowel}</span>
                    </c:forEach>
                </div>
            </div>

            <div class="stat-item consonants">
                <h3>Английские согласные</h3>
                <p class="count">Количество: ${stats.consonantCount}</p>
                <div class="char-list">
                    <c:forEach var="consonant" items="${stats.consonants}">
                        <span>${consonant}</span>
                    </c:forEach>
                </div>
            </div>

            <div class="stat-item punctuation">
                <h3>Знаки препинания</h3>
                <p class="count">Количество: ${stats.punctuationCount}</p>
                <div class="char-list">
                    <c:forEach var="punct" items="${stats.punctuation}">
                        <span>${punct}</span>
                    </c:forEach>
                </div>
            </div>

            <c:if test="${otherCount > 0}">
                <div class="stat-item other">
                    <h3>Другие символы (не английские)</h3>
                    <p class="count">Количество: ${otherCount}</p>
                    <p>Включая русские буквы и другие символы</p>
                    <div class="char-list">
                        <c:forEach var="char" items="${otherChars}">
                            <span>${char}</span>
                        </c:forEach>
                    </div>
                </div>
            </c:if>
        </div>

        <div class="nav">
            <a href="javascript:history.back()" class="btn">Анализировать другой текст</a>
            <a href="/" class="btn">На главную</a>
        </div>
    </div>
</body>
</html>