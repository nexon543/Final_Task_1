<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 26.03.2018
  Time: 22:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Client page</title>
</head>
<body>

<h1>Welcom, dear ${profile.firstName} ${profile.secondName}</h1>

<br/>
<hr/>
First name: ${profile.firstName}
<br/>
Second name: ${profile.secondName}
<br/>
Passport: ${profile.passport}
<br/>
Register date: ${profile.registerDate}
<br/>
Balance: ${profile.balance}
<br/>
<hr/>
<br/>
<a href="${pageContext.request.contextPath}/Controller?command=logout">Logout</a>


</body>
</html>
