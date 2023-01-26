<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Index</title>
</head>
<body>
<a href="result">Show result</a>
<c:redirect url="result">
    <c:param name="reqParam" value="Redirected"/>
</c:redirect>
</body>
</html>