<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta charset="utf-8"/>
    <title>Администрирование</title>
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
<div class="main ">
    <ul>
        <li><a href="admin/allUsers/1">Все пользователи</a></li>
        <li><a href="admin/log">Последние транзакции</a></li>
        <li><a href="/unblocking">Снятие блокировки</a></li>
        <li><a href="/allCard">Просмотр всех карт</a></li>
        <li><a href="/search">Поиск по номеру карты</a></li>
    </ul>
</div>
</body>
</html>