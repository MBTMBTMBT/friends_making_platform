<%@ page import="database.tables.UserPerson" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<!DOCTYPE html>
<html lang="en">

<%
    String adminName = null;
    int adminID = -1;

    HttpSession httpSession = request.getSession();
    try {
        adminName = (String) request.getAttribute("admin_username");
        adminID = (int) request.getAttribute("admin_id");
    } catch (Exception ignore) {
        adminName = null;
        adminID = -1;
    }

    if (adminName == null) {
        adminName = (String) httpSession.getAttribute("admin_username");
        adminID = (int) httpSession.getAttribute("admin_id");
    }

    String personUName = request.getParameter("screenname");

    request.setAttribute("admin_username", adminName);
    request.setAttribute("admin_id", adminID);
    httpSession.setAttribute("admin_username", adminName);
    httpSession.setAttribute("admin_id", adminID);
%>

<head>
    <meta charset="UTF-8">
    <title>modify</title>
    <link rel="stylesheet" type="text/css" href="static/css/userpage_css/bootstrap.min.css">
    <link rel="stylesheet" href="static/css/modify.css">
</head>
<body>
<br><br><br><br><br><br><br>
<h1 style="font-size: 50px; text-align: center;color: rgb(85, 74, 90);text-shadow: rgb(156, 153, 153) 2.5px 1.5px 1.5px;">
    Modify User</h1>
<br>
<form action="adminModifyServlet" method="post">
    <div align="center">
        <input type="hidden" name="screenname" value="<%=personUName%>">
        <label for="password" style="font-size: 25px;color: rgb(85, 74, 90);">password</label>
        <input type="password" name="password" id="password" placeholder="Change your password">
        <br><br>
        <label style="font-size: 25px;color: rgb(85, 74, 90);"> gender</label>

        <input type="radio" name="sex" value="male"> male
        <input type="radio" name="sex" value="female">Female
        <br><br>
        <button class="btn btn-danger" type="submit" value="add" class="btn btn-default" style="color: rgb(73, 41, 85)">
            Confirm
        </button>
        <a class="btn btn-default" href="adminMainPageServlet">cancel</a>
    </div>

</form>


</body>
</html>