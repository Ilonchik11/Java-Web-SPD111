
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h1>Домашня сторінка</h1>
<p>
    Контроль хеш-сервісу: <%=request.getAttribute("hash")%> <br/>
    Control of db: <%=request.getAttribute("db")%>
</p>