<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 26.03.2018
  Time: 22:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form name="loginForm" method="post" action="${pageContext.request.contextPath}/Controller">
    <input type="hidden" name="command" value="login"/>
    <br/>Login:<br/>
    <input name="login" type="text" value=""/>
    <br/>Password:<br/>
    <input name="password" type="password" value=""/>
    <br/>
    <p style="color:red;">${errorLoginPassMessage}</p>
    <br/>
    ${wrongAction}
    <br/>
    ${nullPage}
    <br/>
    <input type="submit" value="enter"/>
</form>
<hr/>
</body>
</html>
