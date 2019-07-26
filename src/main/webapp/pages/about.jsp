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
    <h1 class="px-3 pb-3 text-center tx-correction">О сайте</h1>
    <hr>
    <div class="jumbotron" style="margin-top: 20px;">

        <!-- Current page main code -->
        <div class="content">
            <div class="separator"></div>
            <p>Мы предоставляем открытую платформу для разработчиков модулей и игровых модификаций.</p>
            <p>На данный момент наша платформа состоит из двух больших модулей:</p>
            <p>- Маркет модификаций и модулей, где разработчики могут размещать собственные разработки, а администраторы
                игровых серверов могут осуществлять покупку.</p>
            <p>- Фриланс площадка, где администраторы могут создавать задания на выполнение, а разработчики могут брать
                их и выполнять.</p>
            <br>
            <p>Мы удерживаем небольшой процент за проведение операций, а вся остальная сумма отправляется разработчиками
                по указанным в профиле реквизитам.</p>
            <div class="separator"></div>

            <h2 class="mb-3">Контактная информация</h2>
            <p>По вопросам сайта и оплаты обращайтесь по адресу <a href="mailto:info@site.com">info@site.com</a>.</p>
            <p>По остальным вопросам бращайтесь:</p>
            <ul>
                <li>VK: vk.com/site.com</li>
                <li>Telegram: t.me/site.com</li>
            </ul>

            <h2 class="mb-3">Пополнение счета</h2>
            <p>Пополнить свой счет вы можете в соответствующем разделе в профиле</p>
        </div>

        <!-- Modals -->
        <div id="modalsContent"></div>

    </div>

    <!-- Modals -->
    <div id="footerContent"></div>

</div>

</body>
</html>