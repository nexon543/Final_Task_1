<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<html>

<head>
    <%@include file="elementpage/headContent.jspf" %>
    <fmt:message bundle="${loc}" key="modal.button.accept" var="acceptModal"/>
    <fmt:message bundle="${loc}" key="modal.button.reject" var="closeModal"/>
    <fmt:message bundle="${loc}" key="modal.delete.profile.content" var="contentModal"/>
    <fmt:message bundle="${loc}" key="modal.delete.profile.title" var="titleModal"/>
    <fmt:message bundle="${loc}" key="message.page.title.show.profiles" var="pageTitle"/>
    <fmt:message bundle="${loc}" key="message.page.subtitle.show.profiles" var="subTitle"/>
    <c:set var="currentPageReq" value="get_profiles"/>
    <link rel="stylesheet" href="/css/profile.css">
</head>

<!-- use "theme-invert" class on bright backgrounds, also try "text-shadows" class -->
<body class="theme-invert">
<%@include file="elementpage/menu/showProfiles.jspf" %>
<!-- Main (Home) section -->
<section class="section" id="head">
    <%@include file="elementpage/message.jspf" %>
    <!-- Show profile section -->

    <div class="container">
        <div class="container" align="center">
            <%@include file="elementpage/pageTitle.jspf" %>
            <section>
                <c:forEach items="${users}" varStatus="status" var="profile">
                <c:if test="${profile.role == 'client' or profile.role == 'admin'}">
                <c:if test="${(status.count+1)%2==0}">
                <div class="row">
                    <div class="col-sm-4 col-sm-offset-2 text-center"></c:if>
                        <c:if test="${(status.count+1)%2!=0}">
                        <div class="col-sm-4 text-center"></c:if>
                            <div class="card" style="background-color: #FDF9F4">
                                <br/>
                                <i class="material-icons">account_circle</i>
                                <h1>${profile.firstName} ${profile.secondName}</h1>
                                <p class="titleUserProfile">${profile.passport} </p>
                                <c:forEach items="${tariffsAvailableForUser}" var="t">
                                    <c:if test="${t.tariffId==profile.idTariffs}">
                                        <p>${t.name}</p>
                                    </c:if>
                                </c:forEach>
                                <p><i class="fa fa-bitcoin"></i> ${profile.balance}</p>
                                <p>
                                    <a href="/Controller?command=get_update_page&profile_id=${profile.profileId}&entity=profile"
                                       class="edit" title="Edit"
                                       data-toggle="tooltip"><i
                                            class="material-icons">&#xE254;</i></a>
                                    <a href="#profileDelete"
                                       class="delete" title="Delete" data-target="#profileDelete"
                                       data-toggle="modal"><i
                                            class="material-icons">
                                        &#xE872;</i></a>
                                    <!-- Modal -->
                            </div>
                        </div> <!-- /col -->
                        <c:if test="${(status.count+1)%2!=0}"> </div> </c:if><!-- /row -->
                    </c:if>
                    </c:forEach>
            </section>
            </table>
            <div class="modal fade" id="profileDelete" tabindex="-1" role="dialog"
                 aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">${titleModal}</h5>
                            <button type="button" class="close" data-dismiss="modal"
                                    aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            ${contentModal}
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary"
                                    data-dismiss="modal">${closeModal}
                            </button>
                            <a type="button"
                               href="/Controller?command=delete_profile&id=${profile.profileId}&entity=profile"
                               class="btn btn-primary">${acceptModal}</a>
                        </div>
                    </div>
                </div>
            </div>
            <ul class="pagination" align="center">
                <c:forEach begin="1" end="${pagesNumber}" var="page">
                    <li><a href="Controller?command=get_profiles&currentPage=${page}">${page}</a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</section>
<%@include file="elementpage/jsLoading.jspf" %>
</body>
</html>
