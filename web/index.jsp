<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<html>

<head>
    <%@include file="WEB-INF/jsp/elementpage/headContent.jspf" %>
    <fmt:message bundle="${loc}" key="message.title.home" var="title"/>
    <fmt:message bundle="${loc}" key="message.page.subtitle.home" var="subTitle"/>
    <fmt:message bundle="${loc}" key="message.page.title.home" var="pageTitle"/>
    <fmt:message bundle="${loc}" key="message.page.content.home" var="pageContent"/>
    <c:set var="currentPageReq" value="/Home"/>
    <title>${title}</title>
</head>

<!-- use "theme-invert" class on bright backgrounds, also try "text-shadows" class -->
<body class="theme-invert">

<%@include file="WEB-INF/jsp/elementpage/menu/index.jspf" %>

<!-- Main (Home) section -->
<section class="section" id="head">

    <div class="container">
        <%@include file="WEB-INF/jsp/elementpage/message.jspf" %>
        <div class="row">
            <div class="col-md-0 col-lg-0 col-md-offset-1 col-lg-offset-1 text-center">
                <!-- Site Title, your name, HELLO msg, etc. -->
                <%@include file="WEB-INF/jsp/elementpage/pageTitle.jspf"%>

                <!-- Short introductory (optional) -->
                <h4 class="tagline">

                    <c:out value="${pageContent}"/>
                </h4>
                <p><%@include file="WEB-INF/jsp/elementpage/menu/menuelement/loginButton.jsp"%></p>


            </div> <!-- /col -->
        </div> <!-- /row -->
    </div>
</section>
<%@include file="WEB-INF/jsp/elementpage/jsLoading.jspf" %>
</body>
</html>
