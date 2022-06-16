<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
 <head>
 <meta charset="utf-8" />
 <title>Регистрация</title>
  <style>
     <%@include file="/WEB-INF/css/main.css"%>
 </style>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js">
</script>
<script type="text/javascript">
<%@include file="/WEB-INF/script/registration.js"%>
</script>
 </head>
	 <body>
    <c:if test="${errors != null}">

          <div id="error" class="errorMessage">
          Не удается зарегистрироваться
          <br>
          <br>
          <c:forEach var="error" items="${errors}">
            ${error.defaultMessage}<br>
          </c:forEach>
          </div>

    </c:if>
	 	<div class = "form">
		<form method="post" action="/registration">
		 	 <input name="name" type="text" placeholder="Имя"required autofocus/>
		 	 <input name="lastName" type="text" placeholder="Фамилия"required/>
     		 <input name="password" type="password" placeholder="Пароль"required/>
     		 <input name="numberCard" type="number" placeholder="Номер карты"required/>
     		 <input id="login" name="login" type="text" placeholder="Логин"required/>
     		 <input name="email" type="text" placeholder="Электронная почта"required/>
       		 <form action="">
    		 <button type="submit">Регистрация</button>
			 </form>
		</form>
        </div>
	 </body>
</html>