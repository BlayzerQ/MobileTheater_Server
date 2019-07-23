<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!-- Nav menu -->
<nav class="navbar height-correction fixed-top d-flex flex-row align-items-center p-3 px-4 bg-white border-bottom shadow-sm">
    <div class="mr-auto site-name nt-correction">
        <h1>Site.com</h1>
    </div>
    <div class="navbar-menu mr-3 nt-correction">
        <nav class="nav">
            <li class="nav-item">
                <a href="/" class="nav-link" target="_self">Главная</a>
            </li>
            <li class="nav-item">
                <a href="/catalog" class="nav-link" target="_self">Магазин</a>
            </li>
            <li class="nav-item">
                <a href="/tasks" class="nav-link" target="_self">Фриланс</a>
            </li>
			<li class="nav-item">
                <a href="/about" class="nav-link" target="_self">О сайте</a>
            </li>
            <li class="nav-item">
                <a href="/news" class="nav-link" target="_self">Новости</a>
            </li>
            <li class="nav-item">
                <a href="/forum" class="nav-link" target="_blank">Форум</a>
            </li>
        </nav>
    </div>

    <div class="btn-group nt-correction">
        <sec:authorize access="!isAuthenticated()">
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">Авторизация</button>
            <br>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <a class="btn btn-outline-primary" href="/account" title="Личный кабинет">
                <i class="fa fa-user-circle" aria-hidden="true"></i>
                <sec:authentication property="principal.username" /> </a>
            <button type="button" class="btn btn-outline-primary dropdown-toggle dropdown-toggle-split" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <span class="sr-only">Toggle Dropdown</span>
            </button>
            <div class="dropdown-menu dropdown-menu-right">
                <a class="dropdown-item" href="/logout">Выйти</a>
            </div>
        </sec:authorize>
    </div>
</nav>
    <script>
    var page = window.location.pathname;
    if(page != '/account') {
        var link = document.querySelector( '[ href="'+ page +'" ]' );
        link.style.color = '#dc3545';
        link.style.borderBottom = '2px solid #dc3545';
    }
    </script>