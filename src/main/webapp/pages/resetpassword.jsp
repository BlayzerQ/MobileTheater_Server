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
			<h1 class="px-3 pb-4 text-center">Восстановление пароля</h1>
			
			<div class="d-flex justify-content-center">
				<div class="col-4 border rounded px-3 pt-3">
					<form method="post" autocomplete="off">
						<div class="form-group">
							<label for="email" class="control-label">Ваш E-mail</label>
							<input type="email" id="email" name="email" class="form-control" placeholder="Введите ваш E-mail" required="required">
						</div>
						<div class="form-group">
							<div class="submit-button">
								<input type="submit" name="submit" value="Восстановить пароль" class="btn btn-block btn-success">
							</div>
						</div>
					</form>
					<div class="separator"></div>
					<p>На ваш e-mail придёт письмо с новым паролем</p>
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