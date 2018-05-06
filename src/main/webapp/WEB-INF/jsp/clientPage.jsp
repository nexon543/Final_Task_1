<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<html>
<head>
    <%@include file="elementpage/headContent.jspf" %>
    <fmt:message bundle="${loc}" key="message.title.client" var="title"/>
    <fmt:message bundle="${loc}" key="message.page.subtitle.client" var="subTitle"/>
    <fmt:message bundle="${loc}" key="message.page.title.client" var="pageTitle"/>
    <fmt:message bundle="${loc}" key="button.client.change_tariff" var="changeTariffButton"/>
    <fmt:message bundle="${loc}" key="button.client.deposit" var="depositButton"/>
    <fmt:message bundle="${loc}" key="modal.button.close" var="closeButton"/>
    <fmt:message bundle="${loc}" key="message.page.content.client.balance" var="balance"/>
    <fmt:message bundle="${loc}" key="message.page.content.client.tariff" var="tariff"/>
    <fmt:message bundle="${loc}" key="message.page.content.client.tspeed" var="tspeed"/>
    <fmt:message bundle="${loc}" key="message.page.content.client.rspeed" var="rspeed"/>
    <fmt:message bundle="${loc}" key="message.page.content.client.passport" var="passport"/>
    <fmt:message bundle="${loc}" key="message.page.content.client.registerdate" var="regDate"/>
    <fmt:message bundle="${loc}" key="modal.deposit.value" var="value"/>
    <c:set var="currentPageReq" value="/Login"/>
    <title>${title}</title>

</head>

<body class="theme-invert">
<%@include file="elementpage/menu/client.jspf" %>

<!-- Main (Home) section -->
<section class="section" id="head">
    <div class="container">
        <%@include file="elementpage/message.jspf" %>
        <div class="row">
            <div class="col-md-0 col-lg-0 col-md-offset-1 col-lg-offset-1 text-center">

                <!-- Site Title, your name, HELLO msg, etc. -->
                <%@include file="elementpage/pageTitle.jspf" %>

                <!-- Short introductory (optional) -->
                <h4 class="tagline">
                    <c:out value="${passport}: ${session_profile.passport}"/> <br>
                    <c:out value="${tariff}: ${session_profile_tariff.name}"/><br>
                    <fmt:setLocale value="${sessionScope.local}" scope="page"/>
                    <c:out value="${regDate}:" />
                    <fmt:formatDate value="${session_profile.registerDate}" dateStyle="full"/><br>
                    <c:out value="${rspeed}:${session_profile_tariff.receivingSpeed}" /> <br>
                    <c:out value="${tspeed}:${session_profile_tariff.transferSpeed}" /> <br>
                    <c:out value="${balance}:${session_profile.balance}" />
                </h4>
            </div> <!-- /col -->
        </div> <!-- /row -->
    </div>
</section>
<!-- Modal -->
<div class="modal fade" id="depositModal" tabindex="-1" role="dialog"
     aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <form name="loginForm" method="post" action="/Controller?command=add_balance">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Deposit money</h5>
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <label for="value" class="col-form-label">${value}:</label>
                    <input required="required" type="number" min="5" max="255" class="form-control"
                           name="balance"
                           id="value">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal" style="background-color: red">
                        ${closeButton}
                    </button>
                    <button type="submit" class="btn btn-primary" style="background-color: limegreen">${depositButton}</button>
                </div>
            </div>
        </form>
    </div>
</div>

<%@include file="elementpage/jsLoading.jspf" %>
</body>
</html>
