<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 26.03.2018
  Time: 23:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Request from ${pageContext.errorData.requestURI} is faild
<br/>
Servlet name or type: ${pageContext.errorData.servletName}
<br/>
Status code: ${pageContext.errorData.statusCode}
<br/>
<a href="${pageContext.request.contextPath}/index.jsp">Go to main page</a>
<h3>Sorry, error occured</h3>
<a href="${pageContext.request.contextPath}/index.jsp">Go to main page</a>
</body>
</html>
