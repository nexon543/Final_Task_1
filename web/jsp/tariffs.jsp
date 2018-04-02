<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 26.03.2018
  Time: 22:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Tariffs</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
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

        /* Hide the carousel text when the screen is less than 600 pixels wide */
        @media (max-width: 600px) {
            .carousel-caption {
                display: none;
            }
        }
    </style>
</head>

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
                            <a href="${pageContext.request.contextPath}/jsp/${user.role}.jsp"><span
                                    class="glyphicon glyphicon-log-in"> Go to cabinet </span></a>
                        </c:when>
                        <c:otherwise>
                            <a href="${pageContext.request.contextPath}/jsp/login.jsp"><span
                                    class="glyphicon glyphicon-log-in"> Login</span></a>
                        </c:otherwise>
                    </c:choose>
                </li>
            </ul>
        </div>
    </div>
</nav>
<br/>
<br/>
<div class="container" align="center" style="width:70%">
    <table border="1" id="example" class="display" align="center" style="width:70%">
        <tr>
            <th>Name</th>
            <th>Description</th>
            <th>Recieving speed</th>
            <th>Transfer speed</th>
            <th>Price</th>

        </tr>

        <c:forEach items="${tariffs}" var="tariff">
            <tr>
                <td>${tariff.name}</td>
                <td>${tariff.description}</td>
                <td>${tariff.recievingSpeed}</td>
                <td>${tariff.transferSpeed}</td>
                <td>${tariff.price}</td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="5" border="0">

            </td>
        </tr>
    </table>
    <ul class="pagination" align="center">
        <c:forEach begin="1" end="${pagesNumber}" var="page">
            <li><a href="Controller?command=get_tariffs&currentPage=${page}">${page}</a></li>
        </c:forEach>
    </ul>
</div>
<c:if test="${user.role == 'admin'}">
    <div class="container" align="center">
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal"
                data-whatever="@mdo">Add tariff
        </button>

        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">New tariff</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <form method="post" action="${pageContext.request.contextPath}/Controller?command=add_tariff">
                        <input type="text" style="display:none" name="lang" class="form-control" value="en">
                        <div class="modal-body">
                            <div class="form-group">
                                <label for="recipient-name" class="col-form-label">Name:</label>
                                <input type="text" name="name" class="form-control" id="recipient-name">
                            </div>
                            <div class="form-group">
                                <label for="message-text" class="col-form-label">Description:</label>
                                <textarea class="form-control" name="description" id="message-text"></textarea>
                            </div>
                            <div class="form-group">
                                <label for="transf-speed" class="col-form-label">Transfer speed:</label>
                                <input type="text" class="form-control" name="transferSpeed" id="transf-speed">
                                <label for="recieving-speed" class="col-form-label">Recieving speed:</label>
                                <input type="text" name="recievingSpeed" class="form-control" id="recieving-speed">
                                <label for="price" class="col-form-label">Price:</label>
                                <input type="text" class="form-control" name="price" id="price">
                            </div>

                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-primary">Add Tariff</button>
                        </div>
                    </form>

                </div>
            </div>
        </div>
    </div>
</c:if>

<br/>
<br/>

<footer class="container-fluid text-center">
    <p>ByFlow</p>
</footer>

</body>
</html>
