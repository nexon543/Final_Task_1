<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<html>

<head>
    <%@include file="elementpage/headContent.jspf" %>
    <fmt:message bundle="${loc}" key="message.title.update.profile" var="title"/>
    <fmt:message bundle="${loc}" key="message.page.title.update.profile" var="pageTitle"/>
    <fmt:message bundle="${loc}" key="message.page.subtitle.update.profile" var="subTitle"/>
    <c:set var="currentPageReq" value="/UpdateTariff"/>
    <title>${title}</title>
</head>

<!-- use "theme-invert" class on bright backgrounds, also try "text-shadows" class -->
<body class="theme-invert">
<%@include file="elementpage/menu/updateProfile.jspf" %>

<!-- Main (Home) section -->
<section class="section" id="head">
    <%@include file="elementpage/message.jspf" %>
    <div class="container">
        <div class="row">
            <div class="col-md-0 col-lg-0 col-md-offset-1 col-lg-offset-1 text-center">
                <%@include file="elementpage/pageTitle.jspf" %>
                <%@include file="elementpage/form/updateProfile.jspf" %>
            </div> <!-- /col -->
        </div> <!-- /row -->
    </div>
</section>

<%@include file="elementpage/jsLoading.jspf" %>
</body>

</html>
