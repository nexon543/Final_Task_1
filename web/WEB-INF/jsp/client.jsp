<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ByFlow provider</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="/css/adminPageStyle.css">
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
                <li class="active"><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
                <li><a href="${pageContext.request.contextPath}/Controller?command=get_tariffs">Tariffs</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="${pageContext.request.contextPath}/Controller?command=logout"><span
                        class="glyphicon glyphicon-log-in">Logout</span></a></li>
            </ul>
        </div>
    </div>
</nav>
<br/>

<div class="container">
    <div class="row">
        <div class="col-md-5  toppad  pull-right col-md-offset-3 ">
        </div>
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad">
            <div class="panel panel-info">
                <div class="panel-heading">
                    <h3 class="panel-title">${profile.firstName} ${profile.secondName}</h3>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-md-3 col-lg-3 " align="center"><img alt="User Pic"
                                                                            src="http://babyinfoforyou.com/wp-content/uploads/2014/10/avatar-300x300.png"
                                                                            class="img-circle img-responsive"></div>

                        <div class=" col-md-9 col-lg-9 ">
                            <table class="table table-user-information">
                                <tbody>
                                <tr>
                                    <td>Passport:</td>
                                    <td>${profile.passport}</td>
                                </tr>
                                <tr>
                                    <td>Register date:</td>
                                    <td>${profile.registerDate}</td>
                                </tr>

                                <tr>
                                    <td>Tariff:</td>
                                    <td>${tariff.name}</td>
                                </tr>

                                <tr>
                                    <td>Recieving speed:</td>
                                    <td>${tariff.recievingSpeed}</td>
                                </tr>

                                <tr>
                                    <td>Transfer speed:</td>
                                    <td>${tariff.recievingSpeed}</td>
                                </tr>

                                <tr>
                                    <td>Balance:</td>
                                    <td>${profile.balance}</td>
                                </tr>


                                </tbody>
                            </table>

                            <button type="button" class="btn btn-primary" data-toggle="modal"
                                    data-target="#depositModal">
                                Deposit money
                            </button>
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
                                                <label for="value" class="col-form-label">Value:</label>
                                                <input type="number" min="0" class="form-control" name="balance"
                                                       id="value">
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">
                                                    Close
                                                </button>
                                                <button type="submit" class="btn btn-primary">Deposit</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <a href="/Controller?command=get_tariffs" type="button" class="btn btn-primary">
                                Change tariff
                            </a>
                        </div>
                    </div>
                </div>

                <div class="panel-footer">

                </div>
            </div>
        </div>
    </div>
</div>

<footer class="container-fluid text-center">
    <p>ByFlow</p>
</footer>
</body>
</html>
