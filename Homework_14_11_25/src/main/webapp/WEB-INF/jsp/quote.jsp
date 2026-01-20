<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${title}</title>
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
        .quote {
            font-style: italic;
            font-size: 1.2em;
            color: #333;
            line-height: 1.6;
            padding: 20px;
            border-left: 4px solid #4CAF50;
            background-color: #f9f9f9;
        }
        .author {
            margin-top: 20px;
            color: #666;
            text-align: right;
        }
        .nav {
            margin-top: 30px;
        }
        .btn {
            display: inline-block;
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }
        .btn:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>${title}</h1>

        <div class="quote">
            ${quote}
        </div>

        <div class="author">
            — Линус Торвальдс
        </div>

        <div class="nav">
            <a href="/" class="btn">На главную</a>
        </div>
    </div>
</body>
</html>