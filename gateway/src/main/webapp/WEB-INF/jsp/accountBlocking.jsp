<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
 <head>
 <meta charset="utf-8" />
 <title>Блокировка счёта</title>
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
	 	<div class="form">
		 <form method="post" action="/accountBlocking">
		   <textarea cols="40" rows="5" autofocus maxlength="1000" placeholder="Напишите причину сюда" required></textarea></p>
			<form action="">
    		 <button>Заблокировать</button>
			</form>
		 </form>
		</div>
		<div id="message">
	    ${message}
		</div>
	</body>
</html>