<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<html>

<head>
    <%@include file="elementpage/headContent.jspf" %>
    <fmt:message bundle="${loc}" key="message.title.show.tariff" var="title"/>
    <fmt:message bundle="${loc}" key="message.page.subtitle.tariffs" var="subTitle"/>
    <fmt:message bundle="${loc}" key="message.page.title.show.tariff" var="pageTitle"/>
</head>

<!-- use "theme-invert" class on bright backgrounds, also try "text-shadows" class -->
<body class="theme-invert">
<%@include file="elementpage/menu/showTariffs.jspf" %>
<!-- Main (Home) section -->
<section class="section" id="head">
    <%@include file="elementpage/message.jspf" %>
    <div class="container">
        <div class="row">
            <div class="col-md-0 col-lg-0 col-md-offset-1 col-lg-offset-1 text-center">
                <%@include file="elementpage/pageTitle.jspf" %>
                <!-- Short introductory (optional) -->
                <div class="container" align="center">
                    <table border="1" id="example" class="display" align="center" style="width:70%; height:40%">
                        <tr>
                            <th>Name</th>
                            <th>Description</th>
                            <th>Recieving speed</th>
                            <th>Transfer speed</th>
                            <th>Price</th>
                            <c:if test="${sessionScope.user.role=='client' || sessionScope.user.role=='admin'}">
                                <th>Action</th>
                            </c:if>
                        </tr>

                        <c:forEach items="${tariff_list}" var="currTariff">
                            <tr>
                                <td>${currTariff.name}</td>
                                <td>${currTariff.description}</td>
                                <td>${currTariff.receivingSpeed}</td>
                                <td>${currTariff.transferSpeed}</td>
                                <td>${currTariff.price}</td>
                                <c:if test="${sessionScope.session_profile.role=='client'}">
                                    <td>
                                        <a href="/Controller?command=change_tariff&change_tariff=${currTariff.tariffId}"
                                           class="change"
                                           title="Change"
                                           data-toggle="tooltip"><i class="material-icons">&#xE8C9;</i></a>
                                    </td>
                                </c:if>
                                <c:if test="${sessionScope.session_profile.role=='admin'}">
                                    <td>
                                        <a href="/Controller?command=get_update_page&id_tariffs=${currTariff.tariffId}&entity=tariff"
                                           data-target="#profileModal" class="edit" title="Edit"
                                           data-toggle="tooltip"><i
                                                class="material-icons">&#xE254;</i></a>
                                        <a href="/Controller?command=delete_tariff&id=${currTariff.tariffId}&entity=tariff"
                                           class="delete" title="Delete" data-toggle="tooltip"><i
                                                class="material-icons">
                                            &#xE872;</i></a>
                                    </td>
                                </c:if>
                            </tr>
                        </c:forEach>
                    </table>
                    <ul class="pagination" align="center">
                        <c:forEach begin="1" end="${pagesNumber}" var="page">
                            <li><a href="Controller?command=get_tariffs&currentPage=${page}">${page}</a></li>
                        </c:forEach>
                    </ul>

                </div>
            </div> <!-- /col -->
        </div> <!-- /row -->
    </div>
</section>

<%@include file="elementpage/jsLoading.jspf" %>
</body>
</html>
