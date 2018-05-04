<fmt:message bundle="${loc}" key="button.login" var="loginButton"/>
<fmt:message bundle="${loc}" key="button.login.cabinet" var="loginCabinetButton"/>
<a class="btn btn-default btn-lg" href="${pageContext.request.contextPath}/Login">
    <c:choose>
        <c:when test="${sessionScope.user.role=='client' || sessionScope.user.role=='admin'}">
            <c:out value="${loginCabinetButton}"/>
        </c:when>
        <c:otherwise>
            <c:out value="${loginButton}"/>
        </c:otherwise>
    </c:choose>
</a>