<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- Modal Login Window -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
<div class="modal-dialog modal-sm" role="document">
	<div class="modal-content">
	<div class="modal-header text-center">
		<h4 class="modal-title w-100 font-weight-bold modal-correction">Вход</h4>
		<button type="button" class="close" data-dismiss="modal" aria-label="Close">
		<span aria-hidden="true">&times;</span>
		</button>
	</div><br>
	<div class="container" style="width: 300px;">
		<c:url value="/j_spring_security_check" var="loginUrl" />
                    <form action="${loginUrl}" method="post">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <input type="text" class="form-control md-form mb-2" name="j_username" placeholder="Логин" required autofocus value="test">
                        <input type="password" class="form-control md-form mb-4" name="j_password" placeholder="Пароль" required value="1234">
                        <button class="btn btn-primary btn-block" type="submit">Войти</button>
						<br>
                    </form>
    </div>
        <div class="modal-footer flex-column justify-content-center">
			<a href="/resetpassword" class="btn btn-block btn-link">Я забыл пароль</a>
            <button type="button" class="btn btn-primary button-correction" data-dismiss="modal" onclick="window.location.href='/registration'">Регистрация</button>
        </div>
	</div>
</div>
</div>