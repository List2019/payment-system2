<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Создание банковской карты</title>
    <style>
        <%@include file="/WEB-INF/css/main.css"%>
    </style>
</head>
<body>

<div class="button">
    <ul>
        <div class="logout">
            <li><a href="/logout">выйти</a></li>
        </div>
        <div class="back">
            <li><a href="/main">Назад</a></li>
        </div>
    </ul>
</div>

<c:if test="${errors != null}">

    <div id="error" class="errorMessage">
        Не удается зарегистрировать карту
        <br>
        <br>
        <c:forEach var="error" items="${errors}">
            ${error.defaultMessage}<br>
        </c:forEach>
    </div>

</c:if>
<div class="form">
    <form method="post" action="/createCard">
        <input name="numberCard" type="number" placeholder="Номер карты" required autofocus/>
        <input name="balance" type="number" placeholder="Первоначальный баланс" required/>
        <form action="">
            <button type="submit">Создать карту</button>
        </form>
    </form>
</div>
<div id="message">
    ${message}
</div>
</body>
</html>