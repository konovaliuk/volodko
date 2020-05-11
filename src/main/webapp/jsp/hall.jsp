<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<jsp:include page="/fragment/headTag.jsp"/>
<body>
<jsp:include page="/fragment/bodyHeader.jsp"/>
<h1>${lang['app.hall']}</h1>
<table>
    <tr>
        <td/>
        <c:forEach begin="1" end="${seats}" var="seat">
            <th>${lang['ticket.seat']} ${seat}</th>
        </c:forEach>
    </tr>
    <c:forEach begin="1" end="${rows}" var="line">
        <tr>
            <th>${lang['ticket.line']} ${line}</th>
            <c:forEach begin="1" end="${seats}" var="seat">
                <td>
                    <c:set value="${tickets.get(line).get(seat)}" var="ticket"/>
                    <c:if test="${ticket != null}">
                        ${ticket.price} ${lang['ticket.currency']}
                        <c:choose>
                            <c:when test="${ not ticket.sold}">
                                <c:if test="${(loggedUser != null) && (loggedUser.hasRole('ROLE_USER'))}">
                                    <br><br>
                                    <form method="post" action="${pageContext.request.contextPath}/">
                                        <input type="hidden" name="command" value="buyTicket">
                                        <input type="hidden" name="ticketId" value="${ticket.id}">
                                        <input id="add" type="submit" value=${lang['ticket.buy']}>
                                    </form>
                                </c:if>
                            </c:when>
                            <c:otherwise>
                                <br><br>
                                <div id="sold">
                                        ${lang['ticket.sold']}
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                </td>
            </c:forEach>
        </tr>
    </c:forEach>
</table>
<c:if test="${loggedUser != null && loggedUser.hasRole('ROLE_ADMIN')}">
    <h1>${lang['hall.attendance']}: ${attendance} &#37;</h1>
</c:if>
</body>
</html>
