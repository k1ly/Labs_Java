<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/tld/lke.tld" prefix="lke" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
${param.reqParam}
<div style="border: 1px solid black; margin: 10px">
    <c:catch var="exception">
        <%--        ${'number' > 0}--%>
        <c:out value="exception case"/>
    </c:catch>
    <c:if test="${not empty exception}">
        <script>
            alert('Ошибка: ${exception.message}');
        </script>
    </c:if>
    <br>
    <c:if test="${not empty requestScope.number}">
        <c:choose>
            <c:when test="${requestScope.number < 0}">
                <c:out value="${requestScope.number} меньше нуля"/>
            </c:when>
            <c:when test="${requestScope.number > 0}">
                <c:out value="${requestScope.number} больше нуля"/>
            </c:when>
            <c:otherwise>
                <c:out value="${requestScope.number} равно нулю"/>
            </c:otherwise>
        </c:choose>
    </c:if>
    <br>
    <c:url value="test.jsp" var="myURL">
        <c:param name="x" value="1"/>
    </c:url>
</div>
<div style="border: 1px solid black; margin: 10px">
    <fmt:setBundle basename="messages"/>
    <fmt:setLocale value="en_US"/>
    <fmt:message key="label.result.hello"/>
    <br>
    <fmt:setTimeZone value="GMT-8"/>
    <fmt:formatDate type="both" value="${requestScope.date}"/>
    <br>
    <fmt:formatNumber type="currency" value="${requestScope.price}"/>
</div>
<div style="margin: 10px">
    <sql:setDataSource var="dataSrc" driver="com.mysql.cj.jdbc.Driver" url="jdbc:mysql://localhost:3306/lab_servlet_db"
                       user="root" password="MySQL3306"/>
        <sql:query dataSource="${dataSrc}" var="items">
            SELECT * FROM items;
        </sql:query>
        <table style="width: 30%; border: 1px solid black">
            <tr style="background-color: #969696; color: white">
                <th>Название</th>
                <th>Цена</th>
            </tr>
            <c:forEach var="item" items="${items.rows}">
                <tr>
                    <td style="border: 1px solid black"><c:out value="${item.name}"/></td>
                    <td style="border: 1px solid black"><c:out value="${item.price}"/></td>
                </tr>
            </c:forEach>
        </table>
</div>
<div style="margin: 10px">
    <c:import url="http://localhost:8080/lab7/xml/bookshop.xml" var="xml_url"/>
    <x:parse var="doc" doc="${xml_url}"/>
    <x:set var="bookshop" select="$doc/bookshop"/>
    <x:if select="$bookshop//shelf">
        <table style="width: 50%; border: 1px solid gray">
            <tr style="background-color: #343434; color: white">
                <th>Кол-во</th>
                <th>Продукт</th>
            </tr>
            <x:forEach var="shelf" select="$bookshop/shelf">
                <tr>
                    <x:set var="amount" select="$shelf/attribute::amount"/>
                    <td style="border: 1px solid gray"><x:out select="$amount"/></td>
                    <x:choose>
                        <x:when select="$shelf//book">
                            <x:set var="bookName" select="$shelf/book/name/text()"/>
                            <td style="border: 1px solid gray"><x:out select="$bookName"/></td>
                        </x:when>
                        <x:when select="$shelf//comics">
                            <x:set var="comicsName" select="$shelf/comics/name/text()"/>
                            <td style="border: 1px solid gray"><x:out select="$comicsName"/></td>
                        </x:when>
                        <x:when select="$shelf//postcard">
                            <x:set var="postcardTheme" select="$shelf/postcard/theme/text()"/>
                            <td style="border: 1px solid gray"><x:out select="$postcardTheme"/></td>
                        </x:when>
                    </x:choose>
                </tr>
            </x:forEach>
        </table>
    </x:if>
</div>
<div style="border: 1px solid black; margin: 10px">
    ${fn:length("length of string")}
    <br>
    ${fn:toUpperCase("some small text")}
    <br>
    ${fn:toLowerCase("SOME BIG TEXT")}
    <br>
    <c:set var="array" value="${fn:split('array of strings', ' ')}"/>
    <c:forEach var="string" items="${array}">
        <c:out value="${string}"/>
        <br>
    </c:forEach>
    <c:remove var="array"/>
    ${fn:substring("example", 1, 2)}
    <br>
    ${fn:replace("input", "in", "out")}
    <br>
    ${fn:indexOf("char", "a")}
    <br>
    ${fn:contains("word", "o")}
</div>
<div style="border: 1px solid black; margin: 10px">
    <lke:submit/>
</div>
<div style="margin: 10px">
    <lke:printTable list="${requestScope.list}"/>
</div>
</body>
</html>
