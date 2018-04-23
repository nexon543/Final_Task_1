<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<html>

<head>
    <%@include file="elementpage/headContent.jspf" %>
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
            <section>
                <c:forEach items="${users}" varStatus="status" var="profile">
                <c:if test="${profile.role == 'client' or profile.role == 'admin'}">
                <c:if test="${(status.count+1)%2==0}">
                <div class="row">
                    <div class="col-sm-4 col-sm-offset-2 text-center"></c:if>
                        <c:if test="${(status.count+1)%2!=0}">
                        <div class="col-sm-4 text-center"></c:if>
                            <div class="card">
                                <i class="material-icons">account_circle</i>
                                <h1>${profile.firstName} ${profile.secondName}</h1>
                                <p class="titleUserProfile">${profile.passport} </p>
                                <p><i class="fa fa-bitcoin"></i> ${profile.balance}</p>
                                <p>
                                    <a href="/Controller?command=get_update_page&profile_id=${profile.profileId}&entity=profile"
                                       data-target="#profileModal" class="edit" title="Edit"
                                       data-toggle="tooltip"><i
                                            class="material-icons">&#xE254;</i></a>
                                    <a href="/Controller?command=delete_profile&id=${profile.profileId}&entity=profile"
                                       class="delete" title="Delete" data-toggle="tooltip"><i
                                            class="material-icons">
                                        &#xE872;</i></a>
                                </p>
                            </div> <!-- /col -->
                            <c:if test="${(status.count+1)%2!=0}"> </div> </c:if><!-- /row -->
                    </div>

            </c:if>
            </c:forEach>
            </section>
            </table>
            <ul class="pagination" align="center">
                <c:forEach begin="1" end="${pagesNumber}" var="page">
                    <li><a href="Controller?command=get_profiles&currentPage=${page}">${page}</a></li>
                </c:forEach>
            </ul>
        </div>

    </div>
</section>

<%@include file="elementpage/jsLoading.jspf" %>
</body>
</html>
