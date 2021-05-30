<%--
  Created by IntelliJ IDEA.
  User: 13769
  Date: 21/5/29
  Time: 18:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>File Upload to Database Demo</title>
</head>
<body>
<center>
    <h1>File Upload to Database Demo</h1>
    <form method="post" action="uploadServlet" enctype="multipart/form-data">
        <c:if test="%{not empty img_path}">
            <tr>
                <%String img_path = (String) request.getAttribute("img_path");%>
                <img src="<%=img_path%>" alt="head icon" width="40" height="40">
            </tr>
        </c:if>
        <table border="0">
            <tr>
                <td>Portrait Photo: </td>
                <td><input type="file" name="photo" size="50"/></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="Save">
                </td>
            </tr>
        </table>
    </form>
</center>
</body>
</html>
