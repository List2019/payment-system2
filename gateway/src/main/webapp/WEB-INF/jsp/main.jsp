<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta charset="utf-8"/>
    <title>Главная страница</title>
    <style>
        <%@include file="/WEB-INF/css/main.css"%>
    </style>
</head>
<body>
<div class="button">
    <div class="logout">
        <ul>
            <li><a href="/logout">выйти</a></li>
        </ul>
    </div>
</div>
<div class="main">
    <form method="post" action="/main">
        <ul>
            <li><a href="/transfer">Перевод</a></li>
            <li><a href="/refill">Пополнение счёта</a></li>
            <li><a href="/accountBlocking">Блокировка счёта</a></li>
            <li><a href="/createCard">Создание банковской карты</a></li>

            <c:if test="${currentUserRole == 'admin'}">
                <div class="admin">
                    <li><a href="/admin">Администрирование</a></li>
                </div>
            </c:if>

        </ul>
    </form>
</div>
</body>
</html>