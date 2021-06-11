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
    <title>remove</title>
    <link rel="stylesheet" type="text/css" href="static/css/userpage_css/bootstrap.min.css">
    <link rel="stylesheet" href="static/css/remove.css">
</head>
<body><br><br><br><br><br><br><br><br><br>
<div align="center" class="content">
    <div>
        <dt><h2>Are you sure to delete user <%=personUName%>? </h2></dt>
        <dd><em><p style="color: rgb(190, 56, 112);font-size: 18px;">You may not undo this!</p></em></dd>
    </div>

    <form action="adminDeleteServlet" method="post">
        <div class="form-group">
            <input type="hidden" name="screenname" value="<%=personUName%>">
            <label for="admin_password" style="color: rgb(85, 41, 56);font-size: 20px;">Admin password</label>
            <input type="password" name="password" id="admin_password" placeholder=" Input admin password">
        </div>
        <button class="btn btn-danger" type="submit" value="remove" class="btn btn-default" style="color: rgb(73, 41, 85)">
            Confirm
        </button>
        <a class="btn btn-default" href="adminMainPageServlet">cancel</a>
    </form>
</div>
</body>
</html>