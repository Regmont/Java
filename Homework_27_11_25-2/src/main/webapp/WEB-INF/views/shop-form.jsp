<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Добавить магазин</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; max-width: 600px; }
        .form-container { background: #f9f9f9; padding: 20px; border-radius: 5px;
                         border: 1px solid #ddd; }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; font-weight: bold; }
        input[type="text"], input[type="email"], textarea, select {
            width: 100%; padding: 8px; border: 1px solid #ddd; border-radius: 4px;
            box-sizing: border-box;
        }
        textarea { height: 100px; resize: vertical; }
        .btn { background: #4CAF50; color: white; padding: 10px 15px;
              border: none; border-radius: 4px; cursor: pointer; }
        .btn:hover { background: #45a049; }
        .cancel-link { margin-left: 10px; color: #666; }
    </style>
</head>
<body>
    <h1>Добавить новый магазин</h1>

    <div class="form-container">
        <form action="add" method="post">
            <div class="form-group">
                <label for="name">Название магазина:</label>
                <input type="text" id="name" name="name" required>
            </div>

            <div class="form-group">
                <label for="address">Адрес:</label>
                <input type="text" id="address" name="address" required>
            </div>

            <div class="form-group">
                <label for="phone">Телефон:</label>
                <input type="text" id="phone" name="phone" required>
            </div>

            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email">
            </div>

            <div class="form-group">
                <label for="website">Ссылка на сайт:</label>
                <input type="text" id="website" name="website">
            </div>

            <div class="form-group">
                <label for="category">Категория магазина:</label>
                <select id="category" name="category" required>
                    <option value="продовольственный">Продовольственный</option>
                    <option value="хозяйственный">Хозяйственный</option>
                    <option value="спортивный">Спортивный</option>
                    <option value="одежда">Одежда</option>
                    <option value="электроника">Электроника</option>
                    <option value="другое">Другое</option>
                </select>
            </div>

            <div class="form-group">
                <label for="description">Описание магазина:</label>
                <textarea id="description" name="description"></textarea>
            </div>

            <button type="submit" class="btn">Добавить магазин</button>
            <a href="/" class="cancel-link">Отмена</a>
        </form>
    </div>
</body>
</html>