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
    <link href="/webjars/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="/webjars/fontawesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"/>
    <link href="/pages/css/styles.css" rel="stylesheet"/>

    <!-- Bootstrap core JS -->
    <script src="/webjars/jquery/3.4.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>

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
        <h1 class="px-3 pb-4 text-center">Продукт "${product.name} (${product.mcVersion})"</h1>

        <hr>

        <div class="row product-data">
            <div class="col-4 product-data__column">
                <h2>Общие данные</h2>
                <div class="product-data__item">
                    <div class="product-data__property">Название мода:</div>
                    <div class="product-data__value">${product.name}</div>
                </div>
                <div class="product-data__item">
                    <div class="product-data__property">Версия Minecraft:</div>
                    <div class="product-data__value">${product.mcVersion}</div>
                </div>
                <div class="product-data__item">
                    <div class="product-data__property">Версия мода:</div>
                    <div class="product-data__value">${product.productVersion}</div>
                </div>
                <div class="product-data__item">
                    <div class="product-data__property">Стоимость:</div>
                    <div class="product-data__value">
                        ${product.price} ₽
                    </div>
                </div>
            </div>
            <div class="col-4 product-data__column text-break">
                <h2>Описание</h2>
                <ol>
                    <li>${product.description}</li>
                </ol>
            </div>
            <div class="col-4 product-data__column">
                <h2>Изменения</h2>
                <div class="product-update">
                    <div class="row product-update__data">
                        <div class="col product-update__date">01.01.1991</div>
                        <div class="col-auto product-update__price">
                            0 ₽
                        </div>
                    </div>
                    <div class="product-update__description">${product.changelog}</div>
                </div>
            </div>
        </div>

        <sec:authorize access="!isAuthenticated()">
            <div class="row justify-content-center mt-4 product-buy-buttons">
                <button type="button" class="col-auto btn btn-primary mx-1" data-toggle="modal"
                        data-target="#exampleModal">Войдите, чтобы купить
                </button>
            </div>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <div class="row justify-content-center mt-4 product-buy-buttons">
                <a href="/payment/${product.id}" class="col-auto btn btn-primary mx-1" target="_self"><i
                        class="fa fa-download" aria-hidden="true"></i> Купить за ${product.price} ₽</a>
            </div>
        </sec:authorize>

        <!-- Modals -->
        <div id="modalsContent"></div>

    </div>

    <!-- Modals -->
    <div id="footerContent"></div>

</div>

</body>
</html>