<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h1>Зареєструватись на сайті</h1>
<div class="row">
    <form class="col s12" method="post">
        <div class="row">
            <div class="input-field col s6">
                <i class="material-icons prefix">account_circle</i>
                <input id="icon_prefix" type="text" name="user-name"
                       class="" >
                <label for="icon_prefix">П.І.Б.</label>
                <span class="helper-text"
                      data-error="Це необхідне поле"
                      data-success="Правильно">Прізвище, ім'я, по-батькові</span>
            </div>
            <div class="input-field col s6">
                <i class="material-icons prefix">mail</i>
                <input  id="icon_email" type="tel"  name="user-email">
                <label for="icon_email">E-mail</label>
                <span class="helper-text"
                      data-error="Це необхідне поле"
                      data-success="Правильно">Адреса електронної пошти</span>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s6">
                <i class="material-icons prefix">lock</i>
                <input  id="icon_password" type="password" name="user-password" >
                <label for="icon_password">Пароль</label>
                <span class="helper-text"
                      data-error="Це необхідне поле"
                      data-success="Припустимо">Придумайте пароль</span>
            </div>
            <div class="input-field col s6">
                <i class="material-icons prefix">key</i>
                <input id="icon_repeat" type="password"  name="user-repeat" inputmode="tel">
                <label for="icon_repeat">Повтор</label>
                <span class="helper-text"
                      data-error="Це необхідне поле"
                      data-success="Правильно">Повторіть пароль</span>
            </div>
        </div>
        <div class="row">
            <div class="file-field input-field col s6">
                <div class="btn purple">
                    <i class="material-icons">photo</i>
                    <input type="file" name="user-avatar">
                </div>
                <div class="file-path-wrapper">
                    <input class="file-path validate" type="text" placeholder="Аватарка">
                </div>
            </div>
            <div class="input-field col s6">
                <button type="button" id="signup-button" class="btn purple right"><i class="material-icons left">task_alt</i>Реєстрація</button>
            </div>
        </div>
    </form>
</div>
<div class="errorDiv">

</div>