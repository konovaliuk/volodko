<%@page contentType="text/html" pageEncoding="UTF-8" %>
<form method="post" action="${pageContext.request.contextPath}/">
    <input type="hidden" name="command" value="changeLocale">
    <input type="hidden" name="from" value="${pageContext.request.requestURI}">
    <input type="hidden" name="language" value="en">
    <input type="button" border="0" value="English"/>
</form>

<form method="post" action="${pageContext.request.contextPath}/">
    <input type="hidden" name="command" value="changeLocale">
    <input type="hidden" name="from" value="${pageContext.request.requestURI}">
    <input type="hidden" name="language" value="ru">
    <input type="button" border="0" value="Русский"/>
</form>

<form method="post" action="${pageContext.request.contextPath}/">
    <input type="hidden" name="command" value="changeLocale">
    <input type="hidden" name="from" value="${pageContext.request.requestURI}">
    <input type="hidden" name="language" value="uk">
    <input type="button" border="0" value="Українська"/>
</form>