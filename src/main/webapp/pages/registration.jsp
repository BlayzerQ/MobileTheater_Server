    <%@ page contentType="text/html; charset=UTF-8" language="java" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

        <!DOCTYPE html>
        <html lang="en">

        <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Site.com</title>

        <!-- Bootstrap core CSS -->
        <link href="<c:url value=" /pages/css/bootstrap.css " />" rel="stylesheet">
        <link href="<c:url value=" /pages/css/bootstrap.min.css " />" rel="stylesheet">
        <link href="<c:url value=" /pages/css/styles.css " />" rel="stylesheet">

        <!-- Bootstrap core JS -->
        <script src="/pages/js/jquery.min.js"></script>
        <script src="/pages/js/bootstrap.min.js"></script>

        <!-- Templates loading JS -->
        <script>
            $(function(){
                $("#navmenuContent").load("/pages/templates/nav_menu.jsp");
                $("#modalsContent").load("/pages/templates/modals.jsp");
                $("#footerContent").load("/pages/templates/footer.jsp");
            });
        </script>
        </head>

        <body>

        <!-- Nav Menu -->
        <div id="navmenuContent"></div>

        <div class="container nav-correction">
        <div class="jumbotron" style="margin-top: 20px;">

        <!-- Current page main code -->
		<div class="content">

			<h1 class="px-3 pb-4 text-center">Регистрация</h1>
			<div class="d-flex justify-content-center">
				<div class="col-4 border rounded px-3 pt-3">
					<form:form method="POST" action="${pageContext.request.contextPath}/registration" modelAttribute="user">
						<div class="form-group">
							<form:label path="login">Логин</form:label>
							<form:input path="login" class="form-control" placeholder="Введите Логин" required="required"/>
                            <small><form:errors path="login" class="errors"/></small>
						</div>
						<div class="form-group">
							<form:label path="email" >E-mail</form:label>
							<form:input path="email" class="form-control" placeholder="Введите E-mail" required="required"/>
                            <small><form:errors path="email" class="errors"/></small>
						</div>
						<div class="form-group">
							<form:label path="password">Пароль</form:label>
							<form:input path="password" class="form-control" placeholder="Введите Пароль" required="required"/>
                            <small><form:errors path="password" class="errors"/></small>
						</div>
						<div class="form-group">
                            <label for="confirm" class="control-label">Повтор пароля</label>
                            <input type="password" id="confirm" class="form-control" placeholder="Введите Пароль" required="required" oninput="check(this)">
                            <script>
                                function check(input) {
                                    if (input.value !== document.getElementById('password').value) {
                                        input.setCustomValidity('Пароли не совпадают!');
                                    } else {
                                        input.setCustomValidity('');
                                    }
                                }
                            </script>
						</div>
						<div class="separator"></div>
						<div class="form-group">
							<div class="submit-button">
								<input class="btn btn-lg btn-primary btn-block" type="submit" value="Зарегистрироваться"/>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>

        <!-- Modals -->
        <div id="modalsContent"></div>

        </div>

        <!-- Modals -->
        <div id="footerContent"></div>

        </div>

        </body>
        </html>