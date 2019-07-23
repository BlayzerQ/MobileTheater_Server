<%@ page contentType="text/html; charset=UTF-8" language="java" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

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
                <link href="<c:url value=" /pages/css/font-awesome.min.css " />" rel="stylesheet">

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
                <h1 class="px-3 pb-3 text-center tx-correction">Site.ru - Цифровая площадка для разработчиков и администраторов игровых серверов</h1>
                <hr>
                    <div class="jumbotron" style="margin-top: 20px;">

                        <!-- Current page main code -->
                        <b>Message: ${message}</b>
                        <br>
                        <a href="/api/getUserByID/{id}"><b>/api/getUserByID/{id}</b></a>
                        <br>
                        <a href="/api/getUserByName/{name}"><b>/api/getUserByName/{name}</b></a>
                        <br>
                        <br>

                        <b>XML to JSON конвертер:</b>
                        <br>
                        <a href="/convert"><b>/convert</b></a>
                        <br>
                        <br>

                    <!-- Modals -->
                    <div id="modalsContent"></div>

                    </div>

                    <!-- Modals -->
                    <div id="footerContent"></div>

                </div>
            </body>

            </html>