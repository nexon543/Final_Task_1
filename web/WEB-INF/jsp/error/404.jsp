<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<html>

<head>
    <title>Magister ISP</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Sergey Pozhilov (GetTemplate.com)">

    <title>Magister ISP</title>

    <link rel="shortcut icon" href="/img/gt_favicon.png">

    <!-- Bootstrap itself -->
    <link href="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet" type="text/css">

    <!-- Custom styles -->
    <link rel="stylesheet" href="/css/magister.css">

    <!-- Fonts -->
    <link href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css" rel="stylesheet"
          type="text/css">
    <link href='http://fonts.googleapis.com/css?family=Wire+One' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="message" var="loc"/>
    <fmt:message bundle="${loc}" key="button.login" var="loginButton"/>
    <fmt:message bundle="${loc}" key="button.tariff" var="tariffButton"/>
    <fmt:message bundle="${loc}" key="button.login.cabinet" var="loginCabinetButton"/>
    <fmt:message bundle="${loc}" key="button.lang.en" var="langEn"/>
    <fmt:message bundle="${loc}" key="button.lang.ru" var="langRu"/>
    <fmt:message bundle="${loc}" key="button.lang.language" var="langLang"/>
    <fmt:message bundle="${loc}" key="button.tariffs.show" var="tariffs"/>

</head>

<!-- use "theme-invert" class on bright backgrounds, also try "text-shadows" class -->
<body class="theme-invert">
<nav class="mainmenu">
    <div class="container">
        <div class="dropdown">
            <button type="button" class="navbar-toggle" data-toggle="dropdown"><span class="icon-bar"></span> <span
                    class="icon-bar"></span> <span class="icon-bar"></span></button>
            <!-- <a data-toggle="dropdown" href="#">Dropdown trigger</a> -->
            <ul class="dropdown-menu">
                <li><a href="${pageContext.request.contextPath}/Home"><i class="material-icons"></i><c:out value="${homeButton}"/></a>
                </li>
                <li>
                    <a href="/Controller?command=get_tariffs"><c:out value="${tariffButton}"/> </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/Login"><c:choose>
                        <c:when test="${sessionScope.user.role=='client' || sessionScope.user.role=='admin'}">
                            <c:out value="${loginCabinetButton}"/>
                        </c:when>
                        <c:otherwise>
                            <c:out value="${loginButton}"/>
                        </c:otherwise>
                    </c:choose></a>
                </li>
                <c:if test="${sessionScope.user.role=='client' || sessionScope.user.role=='admin'}">
                    <li>
                        <a href="${pageContext.request.contextPath}/Controller?command=logout">
                            <c:out value="${logoutButton}"/>
                        </a>
                    </li>
                </c:if>
            </ul>
        </div>

        <div class="dropdown navbar-right">
            <button type="button" class="navbar-toggle" data-toggle="dropdown"><i class="material-icons">translate</i></button>
            <ul class="dropdown-menu">
                <li><a href="/Controller?command=set_locale&local=en"><img src="../../img/lang/eng.png"/></a></li>
                <li><a href="/Controller?command=set_locale&local=ru"><img src="../../img/lang/ru.png"/></a></li>
            </ul>
        </div>
    </div>
</nav>

<!-- Main (Home) section -->
<section class="section" id="head">
    <div class="container">
        <div class="row">
            <div class="col-md-0 col-lg-0 col-md-offset-1 col-lg-offset-1 text-center">
                <!-- Site Title, your name, HELLO msg, etc. -->
                <h2 class="title">Error 404</h2>
                <h3 class="subtitle">Internet Service Provider</h3>

                <!-- Short introductory (optional) -->
                <h4 class="tagline">
                    Info about the error<br/>
                    Request from ${pageContext.errorData.requestURI} is faild
                    <br/>
                    Status code: ${pageContext.errorData.statusCode}
                </h4>

                <a href="${pageContext.request.contextPath}/Home" class="btn btn-default btn-lg">
                    Main Page
                </a>
            </div> <!-- /col -->
        </div> <!-- /row -->
    </div>
</section>


<!-- Load js libs only when the page is loaded. -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
<script src="/js/modernizr.custom.72241.js"></script>
<!-- Custom template scripts -->
<script src="/js/magister.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.2/js/bootstrap-select.min.js"></script>
<script>$(function () {
    $('.selectpicker').selectpicker();
});</script>
</body>
</html>
