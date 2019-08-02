<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- Modal Login Window -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header text-center">
                <h4 class="modal-title w-100 font-weight-bold modal-correction">Вход</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <br>
            <div class="container" style="width: 300px;">
                <input type="text" class="form-control md-form mb-2" id="username"
                       placeholder="Логин" required
                       autofocus value="test">
                <input type="password" class="form-control md-form mb-4" id="password"
                       placeholder="Пароль"
                       required value="1234">
                <td><input type="checkbox" id="remember-me"></td>
                <td>Remember me</td>
                <p id="loginErrorMessage"></p>
                <button class="btn btn-primary btn-block" type="button" onclick="handleLoginProcess()">Войти</button>
                <script>
                    function handleLoginProcess() {
                        let xhttp = new XMLHttpRequest();
                        xhttp.onreadystatechange = function () {
                            if (this.readyState === 4) {
                                if (this.status === 403) {
                                    document.getElementById("loginErrorMessage").textContent = this.responseText;
                                    document.getElementById("loginErrorMessage").style.color = "red";
                                } else if (this.status === 200) {
                                    window.location.reload()
                                }
                            }
                        };
                        let username = document.getElementById("username").value;
                        let password = document.getElementById("password").value;
                        let rememberMeCheckbox = document.getElementById("remember-me");
                        xhttp.open("POST", "processLogin", true);
                        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                        let requestData = "_csrf=${_csrf.token}&username=" + username + "&password=" + password;
                        if (rememberMeCheckbox.checked) {
                            requestData += "&remember-me=" + rememberMeCheckbox.value;
                        }
                        xhttp.send(requestData);
                    }
                </script>
                <br>
            </div>
            <div class="modal-footer flex-column justify-content-center">
                <a href="/resetpassword" class="btn btn-block btn-link">Я забыл пароль</a>
                <button type="button" class="btn btn-primary button-correction" data-dismiss="modal"
                        onclick="window.location.href='/registration'">Регистрация
                </button>
            </div>
        </div>
    </div>
</div>