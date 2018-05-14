<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<html>

<head>
    <%@include file="elementpage/headContent.jspf" %>
    <fmt:message bundle="${loc}" key="message.title.show.tariff" var="title"/>
    <fmt:message bundle="${loc}" key="message.page.title.tariffs" var="pageTitle"/>
    <fmt:message bundle="${loc}" key="message.page.subtitle.tariffs" var="subTitle"/>
    <fmt:message bundle="${loc}" key="modal.button.accept" var="acceptModal"/>
    <fmt:message bundle="${loc}" key="modal.button.reject" var="rejectModal"/>
    <fmt:message bundle="${loc}" key="modal.delete.tariff.content" var="contentModal"/>
    <fmt:message bundle="${loc}" key="modal.delete.tariff.title" var="titleModal"/>
    <fmt:message bundle="${loc}" key="modal.delete.tariff.title" var="titleModal"/>
    <fmt:message bundle="${loc}" key="message.tariff.money" var="messMoney"/>
    <fmt:message bundle="${loc}" key="button.client.deposit" var="depositButton"/>
    <fmt:message bundle="${loc}" key="modal.button.close" var="closeButton"/>
    <fmt:message bundle="${loc}" key="modal.deposit.value" var="value"/>
    <c:set var="currentPageReq" value="get_tariffs"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/profile.css">
</head>

<!-- use "theme-invert" class on bright backgrounds, also try "text-shadows" class -->
<body class="theme-invert">
<%@include file="elementpage/menu/showTariffs.jspf" %>
<!-- Main (Home) section -->
<section class="section" id="head">
    <%@include file="elementpage/message.jspf" %>

    <%--<div class="container">
        <div class="row">
            <div class="col-md-0 col-lg-0 col-md-offset-1 col-lg-offset-1 text-center">--%>

    <div class="container">
        <div class="container" align="center">
            <%@include file="elementpage/pageTitle.jspf" %>
            <section>
                <c:forEach items="${tariff_list}" varStatus="status" var="currTariff">
                <c:if test="${(status.count+1)%2==0}">
                <div class="row">
                    <div class="col-sm-4 col-sm-offset-2 text-center"></c:if>
                        <c:if test="${(status.count+1)%2!=0}">
                        <div class="col-sm-4 text-center" style="height:10% "></c:if>
                            <div class="card" style="background-color: #FDF9F4; height:10% ">
                                <h1>${currTariff.name}</h1>
                                <p class="titleUserProfile">${currTariff.description} </p>
                                <p>${currTariff.transferSpeed}/${currTariff.receivingSpeed}</p>
                                <p><i class="fa fa-bitcoin"></i>${currTariff.price}</p>

                                <c:if test="${sessionScope.session_profile.role=='client'}">
                                    <c:choose>
                                        <c:when test="${(sessionScope.session_profile.balance<currTariff.price)}">
                                            <p><a href="#addBalanceModal" data-toggle="modal"
                                                  data-target="#addBalanceModal">${messMoney}</a>
                                            </p>
                                            <p>${sessionScope.session_profile.balance}</p>

                                            <!-- Modal -->
                                            <div class="modal fade" id="addBalanceModal"
                                                 tabindex="-1" role="dialog"
                                                 aria-labelledby="exampleModalLabel"
                                                 aria-hidden="true">
                                                <div class="modal-dialog" role="document">
                                                    <form name="loginForm" method="post"
                                                          action="${pageContext.request.contextPath}/Controller?command=add_balance">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <h5 class="modal-title"
                                                                    id="addBalanceModalTitle">
                                                                    Deposit money</h5>
                                                                <button type="button" class="close"
                                                                        data-dismiss="modal"
                                                                        aria-label="Close">
                                                                    <span aria-hidden="true">&times;</span>
                                                                </button>
                                                            </div>
                                                            <div class="modal-body">
                                                                <label for="value"
                                                                       class="col-form-label">${value}:</label>
                                                                <input required="required"
                                                                       type="number" min="5"
                                                                       max="255"
                                                                       class="form-control"
                                                                       name="balance"
                                                                       id="value">
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="button"
                                                                        class="btn btn-secondary"
                                                                        data-dismiss="modal">
                                                                        ${closeButton}
                                                                </button>
                                                                <button type="submit"
                                                                        class="btn btn-primary">${depositButton}</button>
                                                            </div>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="${pageContext.request.contextPath}/Controller?command=change_tariff&change_tariff=${currTariff.tariffId}"
                                               class="change" title="Change" data-toggle="tooltip">
                                                <i class="material-icons">&#xE8C9;</i></a>
                                        </c:otherwise>
                                    </c:choose>
                                </c:if>
                                <c:if test="${sessionScope.session_profile.role=='admin'}">
                                    <a href="${pageContext.request.contextPath}/Controller?command=get_update_page&id_tariffs=${currTariff.tariffId}&entity=tariff"
                                       data-target="#profileModal" class="edit" title="Edit"
                                       data-toggle="tooltip"><i
                                            class="material-icons">&#xE254;</i></a>
                                    <a href="#TariffDelete" class="delete" title="Delete"
                                       data-target="#tariffDelete" data-toggle="modal"><i
                                            class="material-icons">
                                        &#xE872;</i></a>
                                    <!-- Modal -->
                                    <div class="modal fade" id="tariffDelete" tabindex="-1"
                                         role="dialog"
                                         aria-labelledby="exampleModalLabel" aria-hidden="true">
                                        <div class="modal-dialog" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title"
                                                        id="exampleModalLabel">${titleModal}</h5>
                                                    <button type="button" class="close"
                                                            data-dismiss="modal"
                                                            aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <div class="modal-body">
                                                        ${contentModal}
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary"
                                                            data-dismiss="modal">${rejectModal}
                                                    </button>
                                                    <a type="button"
                                                       style="background-color: red"
                                                       href="${pageContext.request.contextPath}/Controller?command=delete_tariff&id=${currTariff.tariffId}&entity=tariff"
                                                       class="btn btn-primary">${acceptModal}</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:if>
                                <!-- Modal -->
                            </div>
                        </div> <!-- /col -->
                        <c:if test="${(status.count+1)%2!=0}"> </div> </c:if><!-- /row -->
                    </c:forEach>

            </section>
            <c:if test="${pagesNumber!=1}">
                <ul class="pagination" align="center">
                    <c:forEach begin="1" end="${pagesNumber}" var="page">
                        <li><a href="Controller?command=get_tariffs&currentPage=${page}">${page}</a>
                        </li>
                    </c:forEach>
                </ul>
            </c:if>
        </div>
    </div>
    <%@include file="elementpage/jsLoading.jspf" %>

</body>
</html>
