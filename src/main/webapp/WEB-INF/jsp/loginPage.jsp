<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<html>
<head>
    <%@include file="elementpage/headContent.jspf" %>
    <fmt:message bundle="${loc}" key="message.title.login" var="title"/>
    <fmt:message bundle="${loc}" key="message.page.title.login" var="pageTitle"/>
    <fmt:message bundle="${loc}" key="message.form.field.login" var="login"/>
    <fmt:message bundle="${loc}" key="message.form.field.password" var="password"/>
    <fmt:message bundle="${loc}" key="message.form.button.login" var="loginFormButton"/>
    <c:set var="currentPageReq" value="/Login"/>
    <title>${title}</title>
</head>


<!-- use "theme-invert" class on bright backgrounds, also try "text-shadows" class -->
<body class="theme-invert">

<%@include file="elementpage/menu/login.jspf" %>

<section class="section" id="head">
    <%@include file="elementpage/message.jspf" %>
    <div class="container">
        <form name="loginForm" method="post" action="${pageContext.request.contextPath}/Controller"
              accept-charset="UTF-8">
            <div class="row">
                <div class="col-md-10 col-lg-10 col-md-offset-1 col-lg-offset-1 text-center">
                    <div>
                        <div class="login" class="container text-center login">
                            <input type="hidden" name="command" value="login"/>
                            <label for="log">${login}</label>
                        </div>
                    </div>
                </div> <!-- /col -->
            </div> <!-- /row -->

            <div class="row">
                <div class="col-md-10 col-lg-10 col-md-offset-1 col-lg-offset-1 text-center">
                    <input type="text" id="log" name="login" placeholder="Username"
                           required="required"/>
                    <br/>
                </div> <!-- /col -->
            </div> <!-- /row -->

            <div class="row">
                <div class="col-md-10 col-lg-10 col-md-offset-1 col-lg-offset-1 text-center">
                    <br/>
                    <label for="log">${password}</label>
                </div> <!-- /col -->
            </div> <!-- /row -->
            <div class="row">
                <div class="col-md-10 col-lg-10 col-md-offset-1 col-lg-offset-1 text-center">
                    <input type="password" name="pass" placeholder="Password"
                           required="required"/>

                </div> <!-- /col -->
            </div> <!-- /row -->

            <div class="row">
                <div class="col-md-10 col-lg-10 col-md-offset-1 col-lg-offset-1 text-center">
                    <br/>
                    <button type="submit" class="btn btn-default btn-lg">${loginFormButton}</button>
                </div> <!-- /col -->
            </div> <!-- /row -->
        </form>
    </div>
</section>
<%@include file="elementpage/jsLoading.jspf" %>
</body>
</html>
