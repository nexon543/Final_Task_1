<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<html>

<head>
    <%@include file="../elementpage/headContent.jspf" %>
    <fmt:message bundle="${loc}" key="message.title.client" var="title"/>
    <fmt:message bundle="${loc}" key="message.page.subtitle.client" var="subTitle"/>
    <fmt:message bundle="${loc}" key="message.page.title.client" var="pageTitle"/>
</head>

<!-- use "theme-invert" class on bright backgrounds, also try "text-shadows" class -->
<body class="theme-invert">
<%@include file="../elementpage/menu/error.jspf" %>

<!-- Main (Home) section -->
<section class="section" id="head">
    <div class="container">
        <div class="row">
            <div class="col-md-0 col-lg-0 col-md-offset-1 col-lg-offset-1 text-center">
                <%@include file="../elementpage/pageTitle.jspf" %>
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
