<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Profiles</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>
<nav class="mainmenu">
    <div class="container">
        <div class="dropdown">
            <button type="button" class="navbar-toggle" data-toggle="dropdown"><span class="icon-bar"></span> <span
                    class="icon-bar"></span> <span class="icon-bar"></span></button>
            <!-- <a data-toggle="dropdown" href="#">Dropdown trigger</a> -->
            <ul class="dropdown-menu">
                <li><a href="${pageContext.request.contextPath}/WEB-INF/index.jsp">Home<c:out value="${home}"/></a>
                </li>
                <li>
                    <a href="/Controller?command=get_update_page&entity=profile">Добавить клиента</a>
                </li>
                <li>
                    <a href="/Controller?command=get_tariffs"><i class="fa fa-list-alt fa-fw"></i>Открыть тарифы </a>
                </li>
                <a href="${pageContext.request.contextPath}/Login">
                    <c:choose>
                        <c:when test="${sessionScope.user.role=='client' || sessionScope.user.role=='admin'}">
                            <c:out value="${loginCabinetButton}"/>
                        </c:when>
                        <c:otherwise>
                            <c:out value="${loginButton}"/>
                        </c:otherwise>
                    </c:choose>
                </a>
                </li>
                <c:if test="${sessionScope.user.role=='client' || sessionScope.user.role=='admin'}">
                    <li>
                        <a href="${pageContext.request.contextPath}/Controller?command=logout">
                            Logout <c:out value="${logoutButton}"/>
                        </a>
                    </li>
                </c:if>
            </ul>
        </div>
        <div class="dropdown navbar-right">
            <button type="button" class="navbar-toggle" data-toggle="dropdown"><c:out value="${langLang}"/></button>
            <ul class="dropdown-menu">
                <li><a href="/Controller?command=set_locale&local=en"><c:out value="${langEn}"/></a></li>
                <li><a href="/Controller?command=set_locale&local=ru"><c:out value="${langRu}"/></a></li>
            </ul>
        </div>
    </div>
</nav>

<br/>
<div class="container" align="center">
    <p style="color: green">
        <c:out value="${param.messageSuccess}"/>
    </p>
    <p style="color: crimson">
        <c:out value="${param.messageError}"/>
    </p>
</div>
<br/>

<!-- Main (Home) section -->
<section class="section" id="head">
    <div class="container">

        <div class="row">
            <div class="col-md-0 col-lg-0 col-md-offset-1 col-lg-offset-1 text-center">

                <!-- Site Title, your name, HELLO msg, etc. -->
                <h2 class="title">Учётные записи клиентов</h2>
                <h3 class="subtitle">Здесь доступны просмотр, редактирование, добавление и удаление <br/>
                    учётных записей клиента</h3>

                <div class="container" align="center">
                    <table class="table" align="center" style="width:70%">
                        <tr style="color:#1b1e21">
                            <th>First name</th>
                            <th>Second name</th>
                            <th>Passport</th>
                            <th>Balance</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Action</th>
                        </tr>
                        <c:forEach items="${users}" var="profile">
                            <c:if test="${profile.role == 'client'}">
                                <tr>
                                    <td style="color:#1b1e21">${profile.firstName}</td>
                                    <td style="color:#1b1e21">${profile.secondName}</td>
                                    <td style="color:#1b1e21">${profile.passport}</td>
                                    <td style="color:#1b1e21">${profile.balance}</td>
                                    <td style="color:#1b1e21">${profile.login}</td>
                                    <td style="color:#1b1e21">${profile.password}</td>
                                    <td style="color:#1b1e21">${profile.role}</td>
                                    <td>
                                        <a href="/Controller?command=get_update_page&id=${profile.profileId}&entity=profile"
                                           data-target="#profileModal" class="edit" title="Edit"
                                           data-toggle="tooltip"><i
                                                class="material-icons">&#xE254;</i></a>
                                        <a class="delete" title="Delete" data-toggle="tooltip"><i
                                                class="material-icons">
                                            &#xE872;</i></a>
                                    </td>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </table>
                    <ul class="pagination" align="center">
                        <c:forEach begin="1" end="${pagesNumber}" var="page">
                            <li><a href="Controller?command=get_profiles&currentPage=${page}">${page}</a></li>
                        </c:forEach>
                    </ul>
                </div>
            </div> <!-- /col -->
        </div> <!-- /row -->
    </div>
</section>

<%@include file="elementpage/jsLoading.jspf"%>
</body>
</html>
