<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <meta charset="utf-8"/>
    <title>Администрирование</title>
    <style>
        <%@include file="/WEB-INF/css/main.css"%>
    </style>
</head>
<body>
<form:form method="POST" modelAttribute="CreditCard" action="/allCard">
<div class="button">
    <ul>
        <div class="logout">
            <li><a href="/logout">выйти</a></li>
        </div>
        <div class="back">
            <li><a href="/admin">Назад</a></li>
        </div>
    </ul>
</div>
<table>
    <tr>
        <th>numberCard</th>
        <th>idUser</th>
        <th>balance</th>
        <th>block</th>
        <th></th>
    </tr>
    <c:forEach var="cardIterator" items="${cards}">
        <tr>
            <td><a>${cardIterator.getNumberCard()}</a></td>
            <td><a>${cardIterator.getIdUsers()}</a></td>
            <td><a>${cardIterator.getBalance()}</a></td>
            <td><a>${cardIterator.isBlock()}</a></td>
            <td><form:checkbox path="numberCard" value="${cardIterator.getNumberCard()}"/>
                <br></td>
        </tr>
    </c:forEach>
</table>

    <div class="form">
        <form  action="">
            <button type="submit">Удалить</button>
        </form>
    </div>

</form:form>
</body>
</html>