<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Get time</title>
</head>
<body>
<div>
    <h1>Узнать текущее время</h1>
    <form action="${pageContext.request.contextPath}/time">
        <button type="submit">Узнать время</button>
    </form>
</div>
</body>
</html>
