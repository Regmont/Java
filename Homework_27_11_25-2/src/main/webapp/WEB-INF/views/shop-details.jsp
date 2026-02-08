<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>${shop.name}</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; max-width: 800px; }
        .shop-details { background: #f9f9f9; padding: 20px; border-radius: 5px;
                       border: 1px solid #ddd; margin-top: 20px; }
        .field { margin-bottom: 15px; }
        .label { font-weight: bold; color: #555; }
        .value { margin-top: 5px; }
        .back-link { display: inline-block; margin-top: 20px; padding: 8px 16px;
                    background: #007bff; color: white; border-radius: 4px; }
        .back-link:hover { background: #0056b3; text-decoration: none; }
    </style>
</head>
<body>
    <h1>${shop.name}</h1>

    <div class="shop-details">
        <div class="field">
            <div class="label">Название магазина:</div>
            <div class="value">${shop.name}</div>
        </div>

        <div class="field">
            <div class="label">Адрес:</div>
            <div class="value">${shop.address}</div>
        </div>

        <div class="field">
            <div class="label">Телефон:</div>
            <div class="value">${shop.phone}</div>
        </div>

        <div class="field">
            <div class="label">Email:</div>
            <div class="value">${shop.email}</div>
        </div>

        <div class="field">
            <div class="label">Сайт:</div>
            <div class="value">
                <a href="${shop.website}" target="_blank">${shop.website}</a>
            </div>
        </div>

        <div class="field">
            <div class="label">Категория:</div>
            <div class="value">${shop.category}</div>
        </div>

        <div class="field">
            <div class="label">Описание:</div>
            <div class="value">${shop.description}</div>
        </div>
    </div>

    <a href="/" class="back-link">Вернуться к списку</a>
</body>
</html>