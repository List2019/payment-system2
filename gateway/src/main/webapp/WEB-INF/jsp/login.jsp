<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
 <head>
 <meta charset="utf-8" />
 <title>Авторизация</title>
 <style>
     <%@include file="/WEB-INF/css/main.css"%>
 </style>
 </head>
	 <body>
	 	<div class = "form">
		 <form class="form1" method="post" action="/login">
	    	 <input name="login" type="text" placeholder="логин" required autofocus/>
     		 <input name ="password" type="password" required placeholder="пароль"/>
     		 <form action="">
    		 <button type="submit">Войти</button>
			 </form>
			 <p>
     		 <form name="form2" action="/registration">
       		 <button type="submit">Регистрация</button>
			 </form>
		</form>
		</div>
	 </body>
</html>