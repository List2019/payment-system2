<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
 <head>
 <meta charset="utf-8" />
 <title>Последние операции</title>
 <style>
     <%@include file="/WEB-INF/css/main.css"%>
 </style>
<%--  <script src="/WEB-INF/script/jquery-3.3.1.min.js"></script>
  <script type="text/javascript" src="http://code.jquery.com/jquery-1.4.2.min.js"></script>--%>
 </head>
	 <body>
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
		<br><br>
	 	<table>
	 	<tr>
			<th>operation</th>
		</tr>
	 	<c:forEach var="logIterator" items="${logs}">
			<tr>
					<td><a>${logIterator.toString()}</a></td>	
			</tr>
		</c:forEach>
		</table>
		<div id="message">
	    ${message}
		</div>
	 </body>
</html>