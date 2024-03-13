<%@ page import="step.learning.dal.dto.PromotionItem" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String context = request.getContextPath();
    // Вилучаємо дані, передані сервлетом (контроллером)
    PromotionItem[] cartItems = (PromotionItem[]) request.getAttribute("promotion");
%>
<h1>Акційні пропозиції</h1>
<%-- Відображаємо дані --%>
<% for(PromotionItem item : cartItems) { %>
<div class="col s12 m7">
    <div class="card horizontal">
        <div class="card-image flex1">
            <img src="<%=context%>/img/promotion.png" alt="promotion" />
        </div>
        <div class="card-image flex1">
            <img src="<%=context%>/img/no-image.png" alt="img" />
        </div>
        <div class="card-stacked flex3">
            <div class="card-content">
                <p><%= item.getProductId() %></p>
                <p><%= item.getCount() %></p>
            </div>
            <div class="card-action">
                <a href="#">Додати до кошику</a>
            </div>
        </div>
    </div>
</div>
<% } %>