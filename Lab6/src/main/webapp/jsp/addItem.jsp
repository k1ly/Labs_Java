<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <title>Item list</title>
</head>
<body>
<div class="page-container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <div class="content-wrap" style="width: 90%">
        <div style="height: 100%; display: flex; align-items: center;">
            <div class="add-item-content">
                <h1 style="text-align: center">Добавление предмета в ломбард</h1>
                <div>
                    <form action="${pageContext.request.contextPath}/lombard" method="post">
                        <input type="hidden" name="command" value="add_item">
                        <table>
                            <tr>
                                <td>
                                    <label for="add-item-name">Название</label>
                                </td>
                                <td>
                                    <input id="add-item-name" type="text" name="name"
                                           pattern="^(\p{L})+([. '-](\p{L})+)*$" autocomplete="off" required/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label for="add-item-price">Цена</label>
                                </td>
                                <td>
                                    <input id="add-item-price" type="text" name="price"
                                           pattern="^\d{1,6}$" value="0"
                                           autocomplete="off" required/>
                                </td>
                            </tr>
                        </table>
                        <input id="add-item-submit" type="submit" value="Добавить">
                    </form>
                </div>
            </div>
        </div>
    </div>
    <script src="${pageContext.request.contextPath}/js/script.js"></script>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div>
</body>
</html>
