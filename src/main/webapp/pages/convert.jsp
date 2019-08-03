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
    <link href="/webjars/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="/webjars/fontawesome/css/font-awesome.min.css" rel="stylesheet"/>
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
        <center>
            <h1>XML to JSON конвертер</h1>
            <br>
            <form enctype="multipart/form-data">
                <p><input type="file">
            </form>
            <input type="submit" value="Отправить" id="convertbutton"></p>

            <br/>
            <script type="text/javascript">
                $(function () {
                    $('#convertbutton').click(function (e) {
                        $.post({
                            url: 'convertPost',
                            success: function (res) {
                                $('#resultContainer code').text(JSON.stringify(res));
                                $('#resultContainer').show();
                            }
                        })
                    });
                });
            </script>
            <div id="resultContainer" style="display: none;">
                <hr>
                <h4>Результат:</h4>
                <code></code>
            </div>
        </center>

        <!-- Modals -->
        <div id="modalsContent"></div>

    </div>

    <!-- Modals -->
    <div id="footerContent"></div>

</div>

</body>
</html>