<%--
  Created by IntelliJ IDEA.
  User: zfb
  Date: 2021/4/28
  Time: 14:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%if (request.getAttribute("msg") == null) {
    request.setAttribute("msg", "Please entre your user/admin ID");
}%>
<html>
<head>
    <title>Login</title>
</head>
<body>
<div style="text-align: center">
    <form action = "loginServlet" method = "post">
        <br><br><br><br><br>
        ID:       <input type = "text" name = "id"> <br>
        Password: <input type = "password" name = "password"> <br>
        <span style="font-size: 12px;color:red"><%=request.getAttribute("msg")%></span><br>
        <button type="submit">login</button>
    </form>
</div>
</body>
</html>
