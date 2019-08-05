<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
    <link href="/webjars/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="/pages/css/styles.css" rel="stylesheet"/>

    <!-- Bootstrap core JS -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>

    <!-- Templates loading JS -->
    <script>
        $(function () {
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
            <h1 class="px-3 pb-4 text-center">Создание нового пароля</h1>

            <div class="d-flex justify-content-center">
                <div class="col-4 border rounded px-3 pt-3">
                    <form:form method="POST" action="${pageContext.request.contextPath}/change-password"
                               modelAttribute="resetPasswordForm">
                        <input type="hidden" name="token" value="${token}"/>
                        <div class="form-group">
                            <form:label path="newPassword">Пароль</form:label>
                            <form:input path="newPassword" type="password" class="form-control"
                                        placeholder="Введите новый пароль"
                                        required="required"/>
                            <small><form:errors path="newPassword" class="errors"/></small>
                        </div>
                        <div class="form-group">
                            <label for="confirm" class="control-label">Повтор пароля</label>
                            <input type="password" id="confirm" class="form-control"
                                   placeholder="Введите Пароль еще раз"
                                   required="required" oninput="check(this)">
                            <script>
                                function check(input) {
                                    if (input.value !== document.getElementById('newPassword').value) {
                                        input.setCustomValidity('Пароли не совпадают!');
                                    } else {
                                        input.setCustomValidity('');
                                    }
                                }
                            </script>
                        </div>
                        <div class="form-group">
                            <div class="submit-button">
                                <input type="submit" name="submit" value="Изменить пароль"
                                       class="btn btn-block btn-success">
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