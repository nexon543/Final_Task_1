<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>

<html>
<head>
    <%@include file="elementpage/headContent.jspf" %>
    <fmt:message bundle="${loc}" key="message.title.update.tariff" var="title"/>
    <fmt:message bundle="${loc}" key="message.page.title.update.tariff" var="pageTitle"/>
    <fmt:message bundle="${loc}" key="message.page.subtitle.update.tariff" var="subTitle"/>
    <c:set var="currentPageReq" value="/UpdateTariff"/>
</head>

<body class="theme-invert">
<%@include file="elementpage/menu/updateTariff.jspf" %>
<section class="section" id="head">
    <div class="container">
        <div class="row">
            <div class="col-md-0 col-lg-0 col-md-offset-1 col-lg-offset-1 text-center">
                <%@include file="elementpage/pageTitle.jspf" %>
                <%@include file="elementpage/form/updateTariff.jspf" %>
            </div> <!-- /col -->
        </div> <!-- /row -->
    </div>
</section>


<%@include file="elementpage/jsLoading.jspf" %>
</body>
</html>
