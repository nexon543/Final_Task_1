<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 26.03.2018
  Time: 22:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>ByFlow provider</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/flag-icon-css/0.8.2/css/flag-icon.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.2/css/bootstrap-select.min.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
        /* Remove the navbar's default margin-bottom and rounded borders */
        .navbar {
            margin-bottom: 0;
            border-radius: 0;
        }

        /* Add a gray background color and some padding to the footer */
        footer {
            background-color: #f2f2f2;
            padding: 25px;
        }

        .carousel-inner img {
            width: 100%; /* Set width to 100% */
            margin: auto;
            min-height: 200px;
        }
        body{
            background-color: #a2aec7;
        }

        /* Hide the carousel text when the screen is less than 600 pixels wide */
        @media (max-width: 600px) {
            .carousel-caption {
                display: none;
            }
        }
    </style>
</head>
dd
<body>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp">ByFlow</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Home</a></li>
                <li><a href="${pageContext.request.contextPath}/Controller?command=get_tariffs">Tariffs</a></li>

            </ul>

            <ul class="nav navbar-nav navbar-right">

                <li>
                    <c:choose>
                        <c:when test="${user != null}">
                            <a href="${pageContext.request.contextPath}/jsp/${user.role}.jsp"><span class="glyphicon glyphicon-log-in"> Go to cabinet </span></a>
                        </c:when>
                        <c:otherwise>
                    <a href="${pageContext.request.contextPath}/jsp/login.jsp"><span class="glyphicon glyphicon-log-in"> Login</span></a>
                        </c:otherwise>
                    </c:choose>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Language <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="#">English</a></li>
                        <li class="divider"></li>
                        <li><a href="#">Russian</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
<br/>
<br/>
<br/>
<div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">
        <div class="item active">
            <img src="/img/city.jpg" alt="Image" style="height: 60%">
            <div class="carousel-caption">
                <h3>Provider of your city</h3>
                <p></p>
            </div>
        </div>

        <div class="item">
            <img src="/img/combo.jpg" alt="Image" style="height: 60%">
            <div class="carousel-caption">
                <h3>New cool tariff</h3>
                <p></p>
            </div>
        </div>
    </div>

    <!-- Left and right controls -->
    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
    </a>
</div>
<br/>
<br/>
<br/>

<div class="container text-center">
    <h3>What We Do</h3><br>
    <div class="row">
        <div class="col-sm-4">
            <img src="img/wifi_manual.jpg" class="img-responsive" style="width:100%" alt="Image">
            <p>Wi-fi configuration</p>
        </div>
        <div class="col-sm-4">
            <img src="img/internet_service.jpg" class="img-responsive" style="width:100%" alt="Image">
            <p>Internet service</p>
        </div>
        <div class="col-sm-4">
            <img src="img/ddos_byfly.jpg" class="img-responsive" style="width:100%" alt="Image">
            <p>Protect from DDoS attacks</p>
        </div>
    </div>
</div>
<br>

<footer class="container-fluid text-center">
    <p>ByFlow</p>
</footer>

<script>
    $(function(){
        $('.selectpicker').selectpicker();
    });
</script>
</body>
</html>
