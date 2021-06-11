<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="database.tables.UserPerson" %>
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

    List<UserPerson> userPersonList;
    try {
        userPersonList = (List<UserPerson>) request.getAttribute("user_person_list");
    } catch (Exception exception) {
        userPersonList = null;
    }

    if (userPersonList == null) userPersonList = (List<UserPerson>) httpSession.getAttribute("user_person_list");

    Iterator<UserPerson> listIterator = userPersonList.iterator();

    int pageNum = 1;
    try {
        pageNum = Integer.parseInt(request.getParameter("pageNum"));
    } catch (NumberFormatException ignore) {
        try {
            pageNum = (int) request.getAttribute("pageNum");
        } catch (NullPointerException ignoreignore) {
        }
    }
    int maxPage = userPersonList.size() / 10 + 1;
    if (userPersonList.size() % 10 == 0) maxPage -= 1;
    if (maxPage < 1) maxPage = 1;
    if (pageNum > maxPage) pageNum = maxPage;
    if (pageNum < 1) pageNum = 1;
    request.setAttribute("pageNum", pageNum);

    for (int i = 0; i < (pageNum - 1) * 10; i++) {
        System.out.println(i);
        listIterator.next();
    }

    request.setAttribute("admin_username", adminName);
    request.setAttribute("admin_id", adminID);
    request.setAttribute("user_person_list", userPersonList);
    httpSession.setAttribute("admin_username", adminName);
    httpSession.setAttribute("admin_id", adminID);
    httpSession.setAttribute("user_person_list", userPersonList);

    UserPerson userPerson;
    UserPerson empty = new UserPerson();
    empty.setSystemID(-1);
    empty.setUserID(-1);
    empty.setForename("");
    empty.setSurname("");
    empty.setScreenName("");
    empty.setGender("");
%>

<head>
    <meta charset="UTF-8">
    <title>Admin</title>
    <link rel="stylesheet" type="text/css" href="static/css/userpage_css/bootstrap.min.css">
    <link rel="stylesheet" href="static/css/userpage_css/style.css">

    <style>
        .table {
            width: 1200px;
        }
    </style>
</head>

<body>
<h1 style="text-align: center;color: rgb(85, 74, 90);text-shadow: rgb(156, 153, 153) 2.5px 1.5px 1.5px;">Admin
    system</h1>
<br>
<div align="center">
    <form class="form-inline" action="adminMainPageServlet" method="post" id="search_user_by_mentor">
        <input type="text" name="find_screenname" id="find_screenname" placeholder="Input screenname">
        <button type="submit" class="btn btn-warning" style="color: rgb(73, 41, 85)">Search</button>
        <button type="reset" class="btn btn-default" style="color: rgb(73, 41, 85)">reset</button>
        <a class="btn btn-default" href="adminMainPageServlet">cancel</a>
        <a class="btn btn-info" href="login.jsp">logout</a>
    </form>
</div>
<br>
<table class="table table-hover table-bordered" align="center">
    <tr>
        <th class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> system ID</th>
        <th class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> user ID</th>
        <th class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> username</th>
        <th class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> forename</th>
        <th class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> surname</th>
        <th class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> gender</th>
        <th class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> operation</th>
    </tr>

    <tr>
        <%
            userPerson = listIterator.hasNext() ? listIterator.next() : empty;
        %>
        <td class="text-center"
            style="font-size: 17px;color: rgb(85, 74, 90);"><%=userPerson.getSystemID() == -1 ? "" : String.valueOf(userPerson.getSystemID())%>
        </td>
        <td class="text-center"
            style="font-size: 17px;color: rgb(85, 74, 90);"><%=userPerson.getUserID() == -1 ? "" : String.valueOf(userPerson.getUserID())%>
        </td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"><%=userPerson.getScreenName()%>
        </td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"><%=userPerson.getForename()%>
        </td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"><%=userPerson.getSurname()%>
        </td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"><%=userPerson.getGender()%>
        </td>
        <td class="text-center">
            <form action="<%=(!userPerson.getGender().equals(""))? "modify.jsp": "#"%>" method="post">
                <input type="hidden" name="screenname" value=<%=userPerson.getScreenName()%>>
                <button type=submit class="btn btn-default">modify</button>
            </form>
        </td>
        <td class="text-center">
            <form action="<%=(!userPerson.getGender().equals(""))? "remove.jsp": "#"%>" method="post">
                <input type="hidden" name="screenname" value=<%=userPerson.getScreenName()%>>
                <button type=submit class="btn btn-default">remove</button>
            </form>
        </td>
    </tr>

    <tr>
        <%
            userPerson = listIterator.hasNext() ? listIterator.next() : empty;
        %>
        <td class="text-center"
            style="font-size: 17px;color: rgb(85, 74, 90);"><%=userPerson.getSystemID() == -1 ? "" : String.valueOf(userPerson.getSystemID())%>
        </td>
        <td class="text-center"
            style="font-size: 17px;color: rgb(85, 74, 90);"><%=userPerson.getUserID() == -1 ? "" : String.valueOf(userPerson.getUserID())%>
        </td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"><%=userPerson.getScreenName()%>
        </td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"><%=userPerson.getForename()%>
        </td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"><%=userPerson.getSurname()%>
        </td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"><%=userPerson.getGender()%>
        </td>
        <td class="text-center">
            <form action="<%=(!userPerson.getGender().equals(""))? "modify.jsp": "#"%>" method="post">
                <input type="hidden" name="screenname" value=<%=userPerson.getScreenName()%>>
                <button type=submit class="btn btn-default">modify</button>
            </form>
        </td>
        <td class="text-center">
            <form action="<%=(!userPerson.getGender().equals(""))? "remove.jsp": "#"%>" method="post">
                <input type="hidden" name="screenname" value=<%=userPerson.getScreenName()%>>
                <button type=submit class="btn btn-default">remove</button>
            </form>
        </td>
    </tr>

    <tr>
        <%
            userPerson = listIterator.hasNext() ? listIterator.next() : empty;
        %>
        <td class="text-center"
            style="font-size: 17px;color: rgb(85, 74, 90);"><%=userPerson.getSystemID() == -1 ? "" : String.valueOf(userPerson.getSystemID())%>
        </td>
        <td class="text-center"
            style="font-size: 17px;color: rgb(85, 74, 90);"><%=userPerson.getUserID() == -1 ? "" : String.valueOf(userPerson.getUserID())%>
        </td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"><%=userPerson.getScreenName()%>
        </td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"><%=userPerson.getForename()%>
        </td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"><%=userPerson.getSurname()%>
        </td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"><%=userPerson.getGender()%>
        </td>
        <td class="text-center">
            <form action="<%=(!userPerson.getGender().equals(""))? "modify.jsp": "#"%>" method="post">
                <input type="hidden" name="screenname" value=<%=userPerson.getScreenName()%>>
                <button type=submit class="btn btn-default">modify</button>
            </form>
        </td>
        <td class="text-center">
            <form action="<%=(!userPerson.getGender().equals(""))? "remove.jsp": "#"%>" method="post">
                <input type="hidden" name="screenname" value=<%=userPerson.getScreenName()%>>
                <button type=submit class="btn btn-default">remove</button>
            </form>
        </td>
    </tr>

    <tr>
        <%
            userPerson = listIterator.hasNext() ? listIterator.next() : empty;
        %>
        <td class="text-center"
            style="font-size: 17px;color: rgb(85, 74, 90);"><%=userPerson.getSystemID() == -1 ? "" : String.valueOf(userPerson.getSystemID())%>
        </td>
        <td class="text-center"
            style="font-size: 17px;color: rgb(85, 74, 90);"><%=userPerson.getUserID() == -1 ? "" : String.valueOf(userPerson.getUserID())%>
        </td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"><%=userPerson.getScreenName()%>
        </td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"><%=userPerson.getForename()%>
        </td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"><%=userPerson.getSurname()%>
        </td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"><%=userPerson.getGender()%>
        </td>
        <td class="text-center">
            <form action="<%=(!userPerson.getGender().equals(""))? "modify.jsp": "#"%>" method="post">
                <input type="hidden" name="screenname" value=<%=userPerson.getScreenName()%>>
                <button type=submit class="btn btn-default">modify</button>
            </form>
        </td>
        <td class="text-center">
            <form action="<%=(!userPerson.getGender().equals(""))? "remove.jsp": "#"%>" method="post">
                <input type="hidden" name="screenname" value=<%=userPerson.getScreenName()%>>
                <button type=submit class="btn btn-default">remove</button>
            </form>
        </td>
    </tr>

    <tr>
        <%
            userPerson = listIterator.hasNext() ? listIterator.next() : empty;
        %>
        <td class="text-center"
            style="font-size: 17px;color: rgb(85, 74, 90);"><%=userPerson.getSystemID() == -1 ? "" : String.valueOf(userPerson.getSystemID())%>
        </td>
        <td class="text-center"
            style="font-size: 17px;color: rgb(85, 74, 90);"><%=userPerson.getUserID() == -1 ? "" : String.valueOf(userPerson.getUserID())%>
        </td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"><%=userPerson.getScreenName()%>
        </td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"><%=userPerson.getForename()%>
        </td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"><%=userPerson.getSurname()%>
        </td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"><%=userPerson.getGender()%>
        </td>
        <td class="text-center">
            <form action="<%=(!userPerson.getGender().equals(""))? "modify.jsp": "#"%>" method="post">
                <input type="hidden" name="screenname" value=<%=userPerson.getScreenName()%>>
                <button type=submit class="btn btn-default">modify</button>
            </form>
        </td>
        <td class="text-center">
            <form action="<%=(!userPerson.getGender().equals(""))? "remove.jsp": "#"%>" method="post">
                <input type="hidden" name="screenname" value=<%=userPerson.getScreenName()%>>
                <button type=submit class="btn btn-default">remove</button>
            </form>
        </td>
    </tr>

    <tr>
        <%
            userPerson = listIterator.hasNext() ? listIterator.next() : empty;
        %>
        <td class="text-center"
            style="font-size: 17px;color: rgb(85, 74, 90);"><%=userPerson.getSystemID() == -1 ? "" : String.valueOf(userPerson.getSystemID())%>
        </td>
        <td class="text-center"
            style="font-size: 17px;color: rgb(85, 74, 90);"><%=userPerson.getUserID() == -1 ? "" : String.valueOf(userPerson.getUserID())%>
        </td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"><%=userPerson.getScreenName()%>
        </td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"><%=userPerson.getForename()%>
        </td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"><%=userPerson.getSurname()%>
        </td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"><%=userPerson.getGender()%>
        </td>
        <td class="text-center">
            <form action="<%=(!userPerson.getGender().equals(""))? "modify.jsp": "#"%>" method="post">
                <input type="hidden" name="screenname" value=<%=userPerson.getScreenName()%>>
                <button type=submit class="btn btn-default">modify</button>
            </form>
        </td>
        <td class="text-center">
            <form action="<%=(!userPerson.getGender().equals(""))? "remove.jsp": "#"%>" method="post">
                <input type="hidden" name="screenname" value=<%=userPerson.getScreenName()%>>
                <button type=submit class="btn btn-default">remove</button>
            </form>
        </td>
    </tr>

    <tr>
        <%
            userPerson = listIterator.hasNext() ? listIterator.next() : empty;
        %>
        <td class="text-center"
            style="font-size: 17px;color: rgb(85, 74, 90);"><%=userPerson.getSystemID() == -1 ? "" : String.valueOf(userPerson.getSystemID())%>
        </td>
        <td class="text-center"
            style="font-size: 17px;color: rgb(85, 74, 90);"><%=userPerson.getUserID() == -1 ? "" : String.valueOf(userPerson.getUserID())%>
        </td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"><%=userPerson.getScreenName()%>
        </td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"><%=userPerson.getForename()%>
        </td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"><%=userPerson.getSurname()%>
        </td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"><%=userPerson.getGender()%>
        </td>
        <td class="text-center">
            <form action="<%=(!userPerson.getGender().equals(""))? "modify.jsp": "#"%>" method="post">
                <input type="hidden" name="screenname" value=<%=userPerson.getScreenName()%>>
                <button type=submit class="btn btn-default">modify</button>
            </form>
        </td>
        <td class="text-center">
            <form action="<%=(!userPerson.getGender().equals(""))? "remove.jsp": "#"%>" method="post">
                <input type="hidden" name="screenname" value=<%=userPerson.getScreenName()%>>
                <button type=submit class="btn btn-default">remove</button>
            </form>
        </td>
    </tr>

    <tr>
        <%
            userPerson = listIterator.hasNext() ? listIterator.next() : empty;
        %>
        <td class="text-center"
            style="font-size: 17px;color: rgb(85, 74, 90);"><%=userPerson.getSystemID() == -1 ? "" : String.valueOf(userPerson.getSystemID())%>
        </td>
        <td class="text-center"
            style="font-size: 17px;color: rgb(85, 74, 90);"><%=userPerson.getUserID() == -1 ? "" : String.valueOf(userPerson.getUserID())%>
        </td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"><%=userPerson.getScreenName()%>
        </td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"><%=userPerson.getForename()%>
        </td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"><%=userPerson.getSurname()%>
        </td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"><%=userPerson.getGender()%>
        </td>
        <td class="text-center">
            <form action="<%=(!userPerson.getGender().equals(""))? "modify.jsp": "#"%>" method="post">
                <input type="hidden" name="screenname" value=<%=userPerson.getScreenName()%>>
                <button type=submit class="btn btn-default">modify</button>
            </form>
        </td>
        <td class="text-center">
            <form action="<%=(!userPerson.getGender().equals(""))? "remove.jsp": "#"%>" method="post">
                <input type="hidden" name="screenname" value=<%=userPerson.getScreenName()%>>
                <button type=submit class="btn btn-default">remove</button>
            </form>
        </td>
    </tr>

    <tr>
        <%
            userPerson = listIterator.hasNext() ? listIterator.next() : empty;
        %>
        <td class="text-center"
            style="font-size: 17px;color: rgb(85, 74, 90);"><%=userPerson.getSystemID() == -1 ? "" : String.valueOf(userPerson.getSystemID())%>
        </td>
        <td class="text-center"
            style="font-size: 17px;color: rgb(85, 74, 90);"><%=userPerson.getUserID() == -1 ? "" : String.valueOf(userPerson.getUserID())%>
        </td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"><%=userPerson.getScreenName()%>
        </td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"><%=userPerson.getForename()%>
        </td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"><%=userPerson.getSurname()%>
        </td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"><%=userPerson.getGender()%>
        </td>
        <td class="text-center">
            <form action="<%=(!userPerson.getGender().equals(""))? "modify.jsp": "#"%>" method="post">
                <input type="hidden" name="screenname" value=<%=userPerson.getScreenName()%>>
                <button type=submit class="btn btn-default">modify</button>
            </form>
        </td>
        <td class="text-center">
            <form action="<%=(!userPerson.getGender().equals(""))? "remove.jsp": "#"%>" method="post">
                <input type="hidden" name="screenname" value=<%=userPerson.getScreenName()%>>
                <button type=submit class="btn btn-default">remove</button>
            </form>
        </td>
    </tr>

    <tr>
        <%
            userPerson = listIterator.hasNext() ? listIterator.next() : empty;
        %>
        <td class="text-center"
            style="font-size: 17px;color: rgb(85, 74, 90);"><%=userPerson.getSystemID() == -1 ? "" : String.valueOf(userPerson.getSystemID())%>
        </td>
        <td class="text-center"
            style="font-size: 17px;color: rgb(85, 74, 90);"><%=userPerson.getUserID() == -1 ? "" : String.valueOf(userPerson.getUserID())%>
        </td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"><%=userPerson.getScreenName()%>
        </td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"><%=userPerson.getForename()%>
        </td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"><%=userPerson.getSurname()%>
        </td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"><%=userPerson.getGender()%>
        </td>
        <td class="text-center">
            <form action="<%=(!userPerson.getGender().equals(""))? "modify.jsp": "#"%>" method="post">
                <input type="hidden" name="screenname" value=<%=userPerson.getScreenName()%>>
                <button type=submit class="btn btn-default">modify</button>
            </form>
        </td>
        <td class="text-center">
            <form action="<%=(!userPerson.getGender().equals(""))? "remove.jsp": "#"%>" method="post">
                <input type="hidden" name="screenname" value=<%=userPerson.getScreenName()%>>
                <button type=submit class="btn btn-default">remove</button>
            </form>
        </td>
    </tr>
</table>

<div align="center">                        <!--next page-->
    <nav aria-label="Page navigation">
        <ul class="pagination pagination-lg">  <!--large -->
            <li>
                <a href="admin.jsp?pageNum=<%=pageNum - 1%>" aria-label="Previous">
                    <span aria-hidden="true">Previous</span>
                </a>
            </li>

            <li>
                <a href="admin.jsp?pageNum=<%=pageNum + 1%>" aria-label="Next">
                    <span aria-hidden="true">Next</span>
                </a>
            </li>
        </ul>
    </nav>
</div>
</body>
</html>