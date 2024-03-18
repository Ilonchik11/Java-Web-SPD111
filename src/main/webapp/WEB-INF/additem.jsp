<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h1>Додати новий товар</h1>
<div class="row">
    <form id="add-product-form" class="col s12" method="post">
        <div class="row">
            <div class="input-field col s6">
                <i class="material-icons prefix">sell</i>
                <input id="icon_prefix" type="text" name="product-name"
                       class="" >
                <label for="icon_prefix">Product name</label>
                <span class="helper-text"
                      data-error="Це необхідне поле"
                      data-success="Правильно">Назва продукту</span>
            </div>
            <div class="input-field col s6">
                <i class="material-icons prefix">payments</i>
                <input  id="icon_price" type="text" name="product-price">
                <label for="icon_price">Price</label>
                <span class="helper-text"
                      data-error="Це необхідне поле"
                      data-success="Правильно">Ціна</span>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <i class="material-icons prefix">description</i>
                <textarea id="textarea1" class="materialize-textarea" name="product-description"></textarea>
                <label for="textarea1">Description</label>
                <span class="helper-text"
                      data-error="Це необхідне поле"
                      data-success="Припустимо">Додайте опис</span>
            </div>
        </div>
        <div class="row">
            <div class="file-field input-field col s6">
                <div class="btn purple">
                    <i class="material-icons">photo</i>
                    <input type="file" name="product-photo">
                </div>
                <div class="file-path-wrapper">
                    <input class="file-path validate" type="text" placeholder="Зображення товару">
                </div>
            </div>
            <div class="input-field col s6">
                <button type="button" id="addProduct-button" class="btn purple right">
                    <i class="material-icons left">task_alt</i>Додати товар</button>
            </div>
        </div>
    </form>
</div>