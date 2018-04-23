<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<html>

<head>
    <%@include file="elementpage/headContent.jspf" %>
    <fmt:message bundle="${loc}" key="message.title.admin" var="title"/>
    <fmt:message bundle="${loc}" key="message.page.subtitle.admin" var="subTitle"/>
    <fmt:message bundle="${loc}" key="message.page.title.admin" var="pageTitle"/>
    <title>${title}</title>
</head>

<body class="theme-invert">
<%@include file="elementpage/menu/admin.jspf" %>
<!-- Main (Home) section -->
<section class="section" id="head">
    <%@include file="elementpage/message.jspf" %>
    <div class="container">
        <div class="row">
            <div class="col-md-0 col-lg-0 col-md-offset-1 col-lg-offset-1 text-center">
                <%@include file="elementpage/pageTitle.jspf" %>
                <!-- Short introductory (optional) -->
                <h4 class="tagline">
                </h4>
            </div>
        </div>
    </div>
</section>

<%@include file="elementpage/jsLoading.jspf" %>
</body>
</html>