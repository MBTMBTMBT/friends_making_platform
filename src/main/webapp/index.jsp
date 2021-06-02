<%@ page import="database.supports.SetDefault" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:forward page="login.jsp"/>
<%
    SetDefault.setDefaultMentor();
    SetDefault.setDefaultLabels();
%>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
</body>
</html>