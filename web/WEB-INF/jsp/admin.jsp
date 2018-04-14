<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <title>IPS</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/jquery.dataTables.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="/css/adminPageStyle.css">
    <link rel="stylesheet" href="/css/table.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round|Open+Sans">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

    <script>
        $(window).on('load', function () {
            $('#${updatedEntityName}UpdateModalButton').click();
        });
    </script>
</head>

<body>
<%--
<fmt:setLocale value="en"/>
<fmt:setBundle basename="message" var="loc"/>
<fmt:message bundle="${loc}" key="button.admin.viewTariffs" var="viewTariffsB"/>
<fmt:message bundle="${loc}" key="button.admin.addTariff" var="addTariffB"/>
<fmt:message bundle="${loc}" key="button.admin.addUser" var="addUserB"/>
<fmt:message bundle="${loc}" key="button.admin.viewUsers" var="viewUsers"/>
--%>

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
<div class="container" align="center">
    <p style="color: green">
        <c:out value="${messageSuccess}"/>
    </p>
    <p style="color: crimson">
        <c:out value="${messageError}"/>
    </p>
</div>
<br/>


<div class="container">
    <div class="row">
        <div class="col-md-3">
            <ul class="nav nav-pills nav-stacked">
                <%-- <li class="active"><a href="#"><i class="fa fa-home fa-fw"></i>Home</a></li>
                 <li><a href="http://www.jquery2dotnet.com"><i class="fa fa-list-alt fa-fw"></i><c:out value="${viewTariffsB}"/> </a></li>
                 <li><a href="http://www.jquery2dotnet.com"><i class="fa fa-file-o fa-fw"></i><c:out value="${addTariffB}"/></a></li>
                 <li><a href="http://www.jquery2dotnet.com"><i class="fa fa-bar-chart-o fa-fw"></i><c:out value="${viewUsers}"/></a></li>
                 <li><a href="http://www.jquery2dotnet.com"><i class="fa fa-table fa-fw"></i><c:out value="${addUserB}"/></a></li>
             --%>
                <li><a href="/Controller?command=get_tariffs"><i class="fa fa-list-alt fa-fw"></i>Открыть тарифы </a>
                </li>
                <li>
                    <a data-toggle="modal" data-target="#tariffModal" id="tariffModalButton" href=""> Добавить
                        тариф </a>
                    <div class="container" align="center">
                        <div class="modal fade" id="tariffModal" tabindex="-1" role="dialog"
                             aria-labelledby="addTariffModal"
                             aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="updateModal">Добавить тариф</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <form method="post"
                                          action="${pageContext.request.contextPath}/Controller?command=add_tariff">
                                        <input type="text" style="display:none" name="lang" class="form-control"
                                               value="en">
                                        <input type="number" name="id" style="display: none">
                                        <div class="modal-body">
                                            <div class="form-group">
                                                <label for="recipient-name-update" class="col-form-label">Name:</label>
                                                <input type="text" name="name" class="form-control"
                                                       id="recipient-name-update"
                                                >
                                            </div>
                                            <div class="form-group">
                                                <label for="message-text-update"
                                                       class="col-form-label">Description:</label>
                                                <textarea class="form-control" name="description"
                                                          id="message-text-update"></textarea>
                                            </div>
                                            <div class="form-group">
                                                <label for="transf-speed-update" class="col-form-label">Transfer
                                                    speed:</label>
                                                <input type="number" min="25" class="form-control" name="transferSpeed"
                                                       id="transf-speed-update">
                                                <label for="recieving-speed-update" class="col-form-label">Recieving
                                                    speed:</label>
                                                <input type="number" min="25" name="recievingSpeed" class="form-control"
                                                       id="recieving-speed-update">
                                                <label for="price-update" class="col-form-label">Price:</label>
                                                <input type="number" min="0" class="form-control" name="price"
                                                       id="price-update">
                                            </div>

                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-toggle="modal"
                                                    data-dismiss="modal">
                                                Закрыть
                                            </button>
                                            <button type="submit" class="btn btn-primary">Добавить</button>
                                        </div>
                                    </form>

                                </div>
                            </div>
                        </div>
                    </div>
                </li>
                <li><a href="/Controller?command=get_profiles"><i class="material-icons">&#xE8A6;</i>Список клиентов</a>
                </li>
                <li>
                    <a href="" data-toggle="modal" id="profileModalButton" data-target="#profileModal"><i class=""></i>Добавить
                        клиента
                    </a>
                </li>
                <div class="container" align="center">
                    <div class="modal fade" id="profileModal" tabindex="-1" role="dialog"
                         aria-labelledby="exampleModalLabel"
                         aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="profileModalLabel">Добавить клиента</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <form method="post"
                                      action="${pageContext.request.contextPath}/Controller?command=add_profile">
                                    <input type="text" style="display:none" name="lang" class="form-control" value="en">
                                    <div class="modal-body">
                                        <div class="form-group">
                                            <label for="first-name" class="col-form-label">First name:</label>
                                            <input type="text" class="form-control" name="firstName" id="first-name"
                                                   value="${updatableProfile.firstName}">

                                            <label for="second-name" class="col-form-label">Second name:</label>
                                            <input type="text" name="secondName" class="form-control" id="second-name"
                                                   value="${updatableProfile.secondName}"/>

                                            <label for="passport" class="form-control">Passport:</label>
                                            <input type="text" class="form-control" name="passport" id="passport"
                                                   value="${updatableProfile.passport}"/>

                                            <label for="tariffId" class="col-form-label">Tariff id:</label>
                                            <input type="text" class="form-control" name="tariffId" id="tariffId"
                                                   value="${updatableProfile.tariffId}"/>

                                            <label for="balance" class="col-form-label">Balance:</label>
                                            <input type="text" class="form-control" name="balance" id="balance"
                                                   value="${updatableProfile.balance}"/>

                                            <label for="user-name" class="col-form-label">User name:</label>
                                            <input type="text" name="name" class="form-control" id="user-name">

                                            <label for="password" class="col-form-label">Password:</label>
                                            <input type="password" class="form-control" name="password" id="password"/>

                                            <label class="col-form-label">Role:</label>
                                            <select class="form-control" name="role" required="required"
                                                    formId="addUser">
                                                <option>user</option>
                                                <option>admin</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Закрыть
                                        </button>
                                        <button type="submit" class="btn btn-primary">Добавить</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                </li>
            </ul>
        </div>
        <div class="col-md-3">
            <c:import url="elementpage/showProfiles.jsp"/>
        </div>
    </div>
</div>
<c:import url="elementpage/showTariffs.jsp"/>
<c:import url="elementpage/updateTariff.jsp"/>
<footer class="container-fluid text-center">
    <p>ByFlow</p>
</footer>
</body>
</html>
