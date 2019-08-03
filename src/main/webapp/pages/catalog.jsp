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
    <h1 class="px-3 pb-3 text-center tx-correction">Каталог веб-модулей и игровых модификаций</h1>
    <hr>
    <div class="jumbotron">

        <!-- Current page main code -->
        <nav class="navbar filters shadow-sm">
            <form class="row" id="filters-panel" autocomplete="off" action="/catalog" method="get">
                <div class="input-group col-5">
                    <input type="text" id="search" name="search" value="" class="form-control"
                           placeholder="Поиск по названию мода">
                    <div class="input-group-append search-buttons">
                        <input type="submit" value="Поиск" class="btn btn-info">
                        <a href="/catalog?fp_clear=true" class="btn btn-info">Сброс</a>
                    </div>
                </div>
                <div class="col-3">
                    <div class="dropdown bootstrap-select">
                        <select id="fp_version" name="fp_version"
                                class="selectpicker btn dropdown-toggle btn-light bs-placeholder" tabindex="-98">
                            <option value="">Все версии MC</option>
                            <option value="1.10.2">MC 1.10.2</option>
                            <option value="1.12.2">MC 1.12.2</option>
                            <option value="1.7.10">MC 1.7.10</option>
                        </select>
                        <!--  <button type="button" class="btn dropdown-toggle btn-light bs-placeholder" data-toggle="dropdown" role="button" data-id="fp_version" title="Все версии MC">
                            <div class="filter-option">
                                <div class="filter-option-inner">
                                    <div class="filter-option-inner-inner">Все версии MC</div>
                                </div>
                            </div>
                        </button> -->
                        <div class="dropdown-menu " role="combobox">
                            <div class="inner show" role="listbox" aria-expanded="false" tabindex="-1">
                                <ul class="dropdown-menu inner show"></ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-auto filters-checkboxes">
                    <div class="custom-control custom-checkbox custom-control-inline">
                        <input type="checkbox" name="fp_cost_options[]" value="free" class="custom-control-input"
                               id="fp_cost_options_0">
                        <label class="custom-control-label" for="fp_cost_options_0">Бесплатные</label>
                    </div>
                    <div class="custom-control custom-checkbox custom-control-inline">
                        <input type="checkbox" name="fp_cost_options[]" value="paid" class="custom-control-input"
                               id="fp_cost_options_1">
                        <label class="custom-control-label" for="fp_cost_options_1">Платные</label>
                    </div>
                </div>
                <input type="hidden" id="apply_filters" name="apply_filters" value="true">
            </form>
        </nav>
        <br>


        <div class="card-group mb-3 text-center products-list">
            <c:forEach var="item" items='${products}'>
                <div class="card mb-3 shadow-sm product-card">
                    <div class="card-header product-card__title">
                        <a href="/catalog/item/${item.id}">${item.name}</a>
                    </div>
                    <div class="card-body">
                        <div class="product-card__price-row">
                            <div class="product-card__price-row-label">Стоимость</div>
                            <div class="product-card__price-row-value">
                                    ${item.price} ₽
                            </div>
                        </div>
                        <div class="product-card__version">
                            <span class="text-muted">Версия продукта:</span> ${item.productVersion}
                        </div>
                        <br>
                        <a href="/catalog/item/${item.id}" class="btn btn-block btn-outline-primary">Подробнее</a>
                    </div>
                    <div class="card-footer">
                        <small class="text-muted">Тип продукта: ${item.type}</small><br>
                        <small class="text-muted">Последнее обновление: null</small>
                    </div>
                </div>
            </c:forEach>
        </div>

        <!-- Modals -->
        <div id="modalsContent"></div>

    </div>

    <!-- Modals -->
    <div id="footerContent"></div>

</div>

</body>
</html>