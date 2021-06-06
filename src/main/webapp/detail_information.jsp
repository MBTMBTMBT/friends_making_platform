<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>detail_information</title>
    <link rel="stylesheet" type="text/css" href="main/webapp/static/css/userpage_css/bootstrap.min.css">
    <link rel="stylesheet" href="main/webapp/static/css/userpage_css/style.css">
</head>
<style>
    .table
    {
        width:500px;
    }

    /* 容器 <div> - 需要定位下拉内容 */
    .dropdown {
        position: relative;
        display: inline-block;
    }

    /* 下拉内容 (默认隐藏) */
    .dropdown-content {
        display: none;
        position: absolute;
        background-color: #f9f9f9;
        min-width: 160px;
        box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
    }

    /* 下拉菜单的链接 */
    .dropdown-content a {
        color: black;
        padding: 12px 16px;
        text-decoration: none;
        display: block;
    }

    /* 鼠标移上去后修改下拉菜单链接颜色 */
    .dropdown-content a:hover {background-color: #ac79e6}

    /* 在鼠标移上去后显示下拉菜单 */
    .dropdown:hover .dropdown-content {
        display: block;
    }

</style>

<%
    String username = (String) request.getAttribute("user_username");
    int userID = (int) request.getAttribute("user_id");
    String message0 = (String) request.getAttribute("msg0");
    String message1 = (String) request.getAttribute("msg1");
    String message2 = (String) request.getAttribute("msg2");
    String message3 = (String) request.getAttribute("msg3");
    String message4 = (String) request.getAttribute("msg4");
    String message5 = (String) request.getAttribute("msg5");
    String message6 = (String) request.getAttribute("msg6");
    String message7 = (String) request.getAttribute("msg7");
    String message8 = (String) request.getAttribute("msg8");
    String message9 = (String) request.getAttribute("msg9");
    String message10 = (String) request.getAttribute("msg10");
    String message11 = (String) request.getAttribute("msg11");
    request.setAttribute("user_username", username);
    request.setAttribute("user_id", userID);
    HttpSession httpSession = request.getSession();
    httpSession.setAttribute("user_username", username);
    httpSession.setAttribute("user_id", userID);
%>

<body>

<h1 style="text-align: center;color: rgb(85, 74, 90);text-shadow: rgb(167,10,10) 2.5px 1.5px 1.5px;">Detail Information</h1>

<br>
<div align="center">
    <a class="btn btn-primary" href="Recommendations.html">Return</a>
    <button class="btn btn-warning" >Follow</button>
    <button class="btn btn-danger" >Strat Relationship</button>


</div>
<br>
<div align="center" >
    <img border="0" src="main/webapp/static/images/headicon.png" width="250" height="228">
    <br><br>
    <table  class="table table-hover table-bordered " >
        <tr class="active">
            <td align="center" width="100px">Username</td>
            <td align="center"><%=request.getAttribute("user_username")%></td>
        </tr>
        <tr class="success">
            <td align="center" width="100px">Gender</td>
            <td align="center"><%=message4%></td>
        </tr>
        <tr class="warning">
            <td align="center" width="100px">Birthday</td>
            <td align="center" ><%=message3%></td>
        </tr>
        <tr class="danger">
            <td align="center" width="100px">Slogan</td>
            <td align="center" ><%=message5%></td>
        </tr>
        <tr class="info">
            <td align="center" width="100px">Email</td>
            <td align="center" ><%=message1%></td>
        </tr>
        <tr class="active">
            <td align="center" width="100px">Wechat</td>
            <td align="center" ><%=message2%></td>
        </tr>
        <tr class="warning">
            <td align="center" width="100px">Work</td>
            <td align="center" ><%=message6%></td>
        </tr>
        <tr class="danger">
            <td align="center" width="100px">Locations</td>
            <td align="center"><%=message9%></td>
        </tr>
        <tr class="info">
            <td align="center" width="100px">Food</td>
            <td align="center" ><%=message8%></td>
        </tr>
        <tr class="active">
            <td align="center" width="100px">Films</td>
            <td align="center" ><%=message10%></td>
        </tr>
        <tr class="success">
            <td align="center" width="100px">Sports</td>
            <td align="center" ><%=message7%></td>
        </tr>
        <tr class="warning">
            <td align="center" width="100px">Books</td>
            <td align="center" ><%=message11%></td>
        </tr>
    </table>

</div>
</body>
</html>