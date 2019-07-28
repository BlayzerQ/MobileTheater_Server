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
    <h1 class="px-3 pb-3 text-center tx-correction">Задания на разработку</h1>
    <hr>
    <div class="jumbotron">

        <!-- Current page main code -->
        <nav class="navbar filters shadow-sm">
            <form class="row" id="filters-panel" autocomplete="off" action="/tasks" method="get">
                <div class="input-group col-5">
                    <input type="text" id="search" name="search" value="" class="form-control"
                           placeholder="Поиск по названию мода">
                    <div class="input-group-append search-buttons">
                        <input type="submit" value="Поиск" class="btn btn-info">
                        <a href="/tasks?fp_clear=true" class="btn btn-info">Сброс</a>
                    </div>
                </div>
                <div class="col-auto filters-checkboxes checkbox-correction">
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

        <ul class="content-list content-list_freelancers" id="freelancers_list">
            <c:forEach var="item" items='${tasks}'>
                <li class="content-list__item">
                    <article class="user user_short">
                        <figure class="user__avatar user__avatar_r50">
                            <a href="/account/${item.creator}"><img class="avatario"
                                                                    src="https://freelansim.ru/assets/default/users/avatar_r50-a6ce93fe35b158fd29ba0e8681c918c22117160e9586a56eee4ffbc20df9bda1.png"></a>
                            <span class="user-profile__status_verified user-profile__status_verified_mini"
                                  title="Верифицирован">
											Аккаунт верифицирован
										</span>
                            <div class="user_rating">
                                <a class="positive" href="/account/${item.creator}/opinions">+0</a> /
                                <a class="negative" href="/account/${item.creator}/opinions">-0</a>
                            </div>
                        </figure>
                        <div class="user__info">
                            <header class="user-data">
                                <div class="user-data__title">
                                    <a href="/account/${item.creator}">${item.creator}</a>
                                    <div class="user-data__spec">
                                            ${item.title}
                                    </div>
                                </div>
                                <div class="user__price" id="freelancer_status_505425">
                                    <span class="negotiated_price">${item.price} ₽</span>
                                </div>

                            </header>
                            <p class="user__description">
                                    ${item.description}
                            </p>
                            <div class="user__tags">
                                <ul class="tags tags_short">
                                    <li class="tags__item"><a class="tags__item_link" href="/tasks?q=minecraft">minecraft</a>
                                    </li>
                                    <li class="tags__item"><a class="tags__item_link" href="/tasks?q=web">web</a></li>
                                </ul>
                            </div>
                        </div>
                    </article>
                </li>
            </c:forEach>
        </ul>

        <!-- Modals -->
        <div id="modalsContent"></div>

    </div>

    <!-- Modals -->
    <div id="footerContent"></div>

</div>

</body>
</html>