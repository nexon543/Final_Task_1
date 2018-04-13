<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>

<head>
    <title>ByFlow provider</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/flag-icon-css/0.8.2/css/flag-icon.min.css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.2/css/bootstrap-select.min.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="/css/mainPageStyle.css">

</head>
<body>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="message" var="loc"/>
<fmt:message bundle="${loc}" key="button.login" var="loginButton"/>
<fmt:message bundle="${loc}" key="button.login.cabinet" var="loginCabinetButton"/>
<fmt:message bundle="${loc}" key="button.lang.en" var="langEn"/>
<fmt:message bundle="${loc}" key="button.lang.ru" var="langRu"/>
<fmt:message bundle="${loc}" key="button.lang.language" var="langLang"/>
<fmt:message bundle="${loc}" key="button.header.home" var="home"/>
<fmt:message bundle="${loc}" key="button.header.tariffs" var="tariffs"/>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp">ByFlow</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li class="active"><a href="${pageContext.request.contextPath}/index.jsp"><c:out value="${home}"/></a>
                </li>
                <li><a href="${pageContext.request.contextPath}/Controller?command=get_tariffs"><c:out
                        value="${tariffs}"/></a></li>
            </ul>

            <ul class="nav navbar-nav navbar-right">

                <li>
                    <a href="${pageContext.request.contextPath}/Login"><span
                            class="glyphicon glyphicon-log-in">
                        <c:choose>
                            <c:when test="${sessionScope.user.role=='client'}">
                                <c:out value="${loginCabinetButton}"/>
                            </c:when>
                            <c:when test="${sessionScope.user.role=='admin'}">
                                <c:out value="${loginCabinetButton}"/>
                            </c:when>
                            <c:otherwise>
                                <c:out value="${loginButton}"/>
                            </c:otherwise>
                        </c:choose>
                            </span></a>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                       aria-expanded="false"><c:out value="${langLang}"/>
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="/Controller?command=set_locale&local=en"><c:out value="${langEn}"/></a></li>
                        <li class="divider"></li>
                        <li><a href="/Controller?command=set_locale&local=ru"><c:out value="${langRu}"/></a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
<br/>

<br>

<footer class="container-fluid text-center">
    <p>ByFlow</p>
</footer>

<script>
    $(function () {
        $('.selectpicker').selectpicker();
    });
</script>
</body>
</html>
