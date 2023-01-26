<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <title>Lab 6</title>
</head>
<body>
<h1>Lab 6</h1>
<ul style="list-style: none; font-size: x-large;">
    <li style="margin: 10px;">
        <a href="${pageContext.request.contextPath}/jsp/getTime.jsp">Время</a>
    </li>
    <li style="margin: 10px;">
        <div class="lombard">
            <a href="${pageContext.request.contextPath}/jsp/welcome.jsp">Ломбард</a>
        </div>
    </li>
    <li style="margin: 10px;">
        <form style="width: 40%; background-color: #dfbbfa" action="${pageContext.request.contextPath}/servlet1"
              method="get">
            <div>
                <label for="param-get">Введите текст</label>
                <input id="param-get" type="text" name="text" autocomplete="off">
            </div>
            <div>
                <input id="is-redirect-get" type="checkbox" name="isRedirect">
                <label for="is-redirect-get">Использовать переадресацию</label>
            </div>
            <input style="margin: 10px;" type="submit" value="Сервлет 1 (GET)">
        </form>
    </li>
    <li style="margin: 10px;">
        <form style="width: 40%; background-color: #b6defa" action="${pageContext.request.contextPath}/servlet1"
              method="post">
            <div>
                <label for="param-post">Введите текст</label>
                <input id="param-post" type="text" name="text" autocomplete="off">
            </div>
            <div>
                <input id="is-redirect-post" type="checkbox" name="isRedirect">
                <label for="is-redirect-post">Использовать переадресацию</label>
            </div>
            <input style="margin: 10px;" type="submit" value="Сервлет 1 (POST)">
        </form>
    </li>
</ul>
</body>
</html>