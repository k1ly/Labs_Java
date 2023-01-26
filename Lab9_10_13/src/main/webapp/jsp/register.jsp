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
    <title>Register</title>
</head>
<body>
<div class="reg-page">
    <div class="reg-container">
        <c:if test="${sessionScope.errorMessage!=null}">
            <p class="errorMessage">${sessionScope.errorMessage}</p>
            ${sessionScope.remove("errorMessage")}
        </c:if>
        <form id="register-form" action="${pageContext.request.contextPath}/auth" method="post" autocomplete="off">
            <input type="hidden" name="command" value="register_user">
            <div style="padding: 16px;">
                <label for="login">Логин</label>
                <input id="login" type="text" name="login"
                       placeholder="Введите логин"
                       pattern="^[A-Za-z]\w{5,20}$" required/>
                <label for="password">Пароль</label>
                <div style="display: inline-block; width: 100%">
                    <input id="password" type="password" name="password"
                           placeholder="Введите пароль"
                           pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$" required/>
                    <button id="show-password" type="button">👁</button>
                </div>
                <div id="password-check">
                    <h5>Пароль должен содержать:</h5>
                    <p id="letter" class="invalid">
                        <b>Строчную</b> букву</p>
                    <p id="capital" class="invalid">
                        <b>Заглавную</b> букву</p>
                    <p id="number" class="invalid">
                        <b>Число</b></p>
                    <p id="length" class="invalid">
                        <b>8 символов</b></p>
                </div>
                <label for="repeat-psw">Повторите пароль</label>
                <div style="display: inline-block; width: 100%">
                    <input id="repeat-psw" type="password" name="repeatPassword"
                           placeholder="Повторите пароль" required/>
                    <span id="repeat-check"></span>
                </div>
                <label for="name">Имя</label>
                <input id="name" type="text" name="name"
                       placeholder="Введите имя"
                       pattern="^(\p{L})+([. '-](\p{L})+)*$" required/>
                <input id="reg-submit" type="submit" value="Регистраиця">
            </div>
        </form>
        <div class="reg-footer">
            <a class="cancel-reg" href="${pageContext.request.contextPath}">Отмена</a>
            <div class="reg-actions">
                Уже есть аккаунт?
                <a href="${pageContext.request.contextPath}/jsp/signIn.jsp">
                    Войти
                </a>
            </div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/script.js"></script>
</body>
</html>