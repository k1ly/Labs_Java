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
        <div>
            <h1 style="text-align: center">Список предметов ломбарда</h1>
            <c:if test="${requestScope.itemList!=null}">
                <table style="margin: 15px; width: calc(100% - 30px)">
                    <tr style="padding:10px; border-bottom: 1px #0c0b0b solid; background-color: #6e6e6e; color: white">
                        <th>Ид</th>
                        <th>Название</th>
                        <th>Цена</th>
                        <th>Владелец</th>
                        <th></th>
                    </tr>
                    <c:forEach var="item" items="${requestScope.itemList}">
                        <tr class="item"
                            style="padding:10px; border-bottom: 1px #545454 solid; background-color: white">
                            <td>${item.id}</td>
                            <td>${item.name}</td>
                            <td>${item.price}</td>
                            <td>${item.owner.name}</td>
                            <td>
                                <form action="${pageContext.request.contextPath}/lombard" method="post">
                                    <input type="hidden" name="command" value="delete_item">
                                    <input type="hidden" name="item_id" value="${item.id}">
                                    <button class="delete-item" style="float: right; margin-right: 10px">Удалить</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
        </div>
    </div>
    <script src="${pageContext.request.contextPath}/js/script.js"></script>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div>
</body>
</html>
