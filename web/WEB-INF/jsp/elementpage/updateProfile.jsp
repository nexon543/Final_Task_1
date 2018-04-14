<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${state=='update_profile'}">
    <a href="" data-toggle="modal" id="profileUpdateModalButton" data-target="#profileUpdateModal"><i
            class=""></i>Добавить
        клиента
    </a>
    <div class="container" align="center">
        <div class="modal fade" id="profileUpdateModal" tabindex="-1" role="dialog"
             aria-labelledby="exampleModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="profileUpdateModalLabel">Изменить данные клиента</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <form method="post"
                          action="${pageContext.request.contextPath}/Controller?command=update_profile">
                        <input type="text" style="display:none" name="lang" class="form-control" value="en">
                        <div class="modal-body">
                            <div class="form-group">
                                <label for="update-first-name" class="col-form-label">First name:</label>
                                <input type="text" class="form-control" name="firstName" id="update-first-name"
                                       value="${updatableProfile.firstName}">

                                <label for="update-second-name" class="col-form-label">Second name:</label>
                                <input type="text" name="secondName" class="form-control"
                                       id="update-second-name"
                                       value="${updatableProfile.secondName}"/>

                                <label for="update-passport" class="form-control">Passport:</label>
                                <input type="text" class="form-control" name="passport" id="update-passport"
                                       value="${updatableProfile.passport}"/>

                                <label for="update-tariffId" style="display: none" class="col-form-label">Tariff
                                    id:</label>
                                <input type="text" class="form-control" name="tariffId" id="update-tariffId"
                                       value="${updatableProfile.tariffId}"/>

                                <label for="update-balance" class="col-form-label">Balance:</label>
                                <input type="text" class="form-control" name="balance" id="update-balance"
                                       value="${updatableProfile.balance}"/>

                                <label for="update-user-name" class="col-form-label">User name:</label>
                                <input type="text" name="name" class="form-control" id="update-user-name"
                                       value="${updatableProfile.login}">

                                <label for="update-password" class="col-form-label">Password:</label>
                                <input type="password" class="form-control" name="password" id="update-password"
                                       value="${updatableProfile.password}">

                                <label class="col-form-label">Role:</label>
                                <select class="form-control" name="role" required="required"
                                        formId="addUser" value="${updatableProfile.role}">
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
</c:if>