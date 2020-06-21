<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
 <head>
 <meta charset="utf-8" />
 <title>Перевод на другой счёт</title>
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
	 	<div class = "form">
		 <form method="post" action="/transfer">
		   <input name="value" type="text" required placeholder="Сумма">
		   <input name="numberCard" type="text" required placeholder="Номер карты">
		    <form action="#">
    		 <button type="submit">Отправить</button>
		    </form>
		 </form>
		</div>
		<div id="message">
	    ${message}
		</div>
	 </body>
</html>