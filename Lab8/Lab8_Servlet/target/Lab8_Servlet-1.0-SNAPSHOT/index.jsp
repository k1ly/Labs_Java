<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <title>Lab 8</title>
</head>
<body>
<div style="display: flex; justify-content: center; align-items: center; width: 100vw; height: 100vh">
    <form style="padding: 10px; box-shadow: 0 0 10px rgba(0,0,0,0.5); border-radius: 5px"
          action="${pageContext.request.contextPath}/transfer" method="post">
        <div>
            <label>
                Введите сумму для перевода<br/>
                <input style="margin: 10px;" type="text" name="value" pattern="\d+" required/>
            </label>
        </div>
        <div style="display: flex; justify-content: center"><input type="submit" value="Подтвердить"></div>
    </form>
</div>
</body>
</html>