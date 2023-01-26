<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" type="text/css"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <title>Войти</title>
</head>
<body>
<div class="auth-page">
    <div class="auth-container">
        <c:if test="${sessionScope.errorMessage!=null}">
            <p class="errorMessage">${sessionScope.errorMessage}</p>
            ${sessionScope.remove("errorMessage")}
        </c:if>
        <form action="${pageContext.request.contextPath}/auth" method="post">
            <input type="hidden" name="command" value="sign_in">
            <div style="padding: 16px;">
                <label for="login">Логин<span style="color: red">*</span></label>
                <input id="login" type="text" name="login"
                       placeholder="Введите логин"
                       pattern="^[A-Za-z]\w{5,20}$" required/>
                <label for="password">Пароль</label>
                <input id="password" type="password" name="password"
                       placeholder="Введите пароль"
                       pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$" required/>
                <input id="auth-submit" type="submit" value="Войти"/>
            </div>
        </form>
        <div class="auth-footer">
            <a class="cancel-auth" href="${pageContext.request.contextPath}">
                Отмена
            </a>
            <div class="auth-actions">
                <div>
                    <a href="${pageContext.request.contextPath}/jsp/register.jsp">
                        Зарегистрироваться
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/script.js"></script>
</body>
</html>