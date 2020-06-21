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
<c:set value="${userList}" var="userPageList"/>
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
        <th>idUser</th>
        <th>name</th>
        <th>lastName</th>
        <th>numberCard</th>
        <th>password</th>
        <th>login</th>
        <th>role</th>
        <th>email</th>
    </tr>
    <c:forEach var="userIterator" items="${userPageList.pageList}">
        <tr>
            <td><a>${userIterator.getIdUsers()}</a></td>
            <td><a>${userIterator.getName()}</a></td>
            <td><a>${userIterator.getLastName()}</a></td>
            <td><a>${userIterator.getNumberCard()}</a></td>
            <td><a>${userIterator.getPassword()}</a></td>
            <td><a>${userIterator.getLogin()}</a></td>
            <td><a>${userIterator.getRole()}</a></td>
            <td><a>${userIterator.getEmail()}</a></td>
            <br>
        </tr>
    </c:forEach>

</table>

<c:forEach begin="1" end="${userPageList.pageCount}" step="1" varStatus="tagStatus">
    <c:choose>
        <c:when test="${(userPageList.page + 1) == tagStatus.index}">
            <span>${tagStatus.index}</span>
        </c:when>
        <c:otherwise>
            <c:url value="/admin/allUsers/${tagStatus.index}" var="url"/>
            <a href='<c:out value="${url}" />'>${tagStatus.index}</a>
        </c:otherwise>
    </c:choose>
</c:forEach>

</body>
</html>