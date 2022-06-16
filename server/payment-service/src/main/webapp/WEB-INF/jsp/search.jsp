<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Поиск по номеру карты</title>
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
<div class="search">
    <form method="post" action="/search">
        <input name="value" type="number" required autofocus placeholder="Номер карты">
        <form id="button" action="#">
            <button type="submit">Найти</button>
        </form>
    </form>
</div>

<c:if test="${user != null}">
<table>
    <tr>
        <th>idUser</th>
        <th>name</th>
        <th>lastName</th>
        <th>numberCard</th>
        <th>password</th>
        <th>login</th>
        <th>role</th>
        <th>email</th>
    </tr>

        <tr>
            <td><a>${user.getIdUsers()}</a></td>
            <td><a>${user.getName()}</a></td>
            <td><a>${user.getLastName()}</a></td>
            <td><a>${user.getNumberCard()}</a></td>
            <td><a>${user.getPassword()}</a></td>
            <td><a>${user.getLogin()}</a></td>
            <td><a>${user.getRole()}</a></td>
            <td><a>${user.getEmail()}</a></td>
            <br></td>
        </tr>
</table>
</c:if>

<div id="message">
    ${message}
</div>

</body>
</html>