<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html lang="en">

<%
    HttpSession httpSession = request.getSession();
    String username = null;
    int userID = -1;

    try {
        username = (String) request.getAttribute("user_username");
        userID = (int) request.getAttribute("user_id");
    } catch (NullPointerException ignore) {
    }

    if (username == null || userID == -1) {
        session = request.getSession();
        username = (String) session.getAttribute("user_username");
        userID = (int) session.getAttribute("user_id");
    }

    request.setAttribute("user_username", username);
    request.setAttribute("user_id", userID);
    session.setAttribute("user_username", username);
    session.setAttribute("user_id", userID);

    List<List<String>> infoLists = null;
    try {
        infoLists = (List<List<String>>) request.getAttribute("info_lists");
        if (infoLists == null) throw new NullPointerException();
    } catch (Exception ignore) {
        infoLists = (List<List<String>>) session.getAttribute("info_lists");
    }

    request.setAttribute("info_lists", infoLists);
    session.setAttribute("info_lists", infoLists);
    Iterator<List<String>> listIterator = infoLists.iterator();
    // listIterator.remove();

    List<String> headIconList, userNameList, genderList;
    headIconList = listIterator.next();
    userNameList = listIterator.next();
    genderList = listIterator.next();
    List<String> idList = listIterator.next();

    Iterator<String> headIconListIter, userNameListIter, genderListIter, idListIter;
    headIconListIter = headIconList.iterator();
    userNameListIter = userNameList.iterator();
    genderListIter = genderList.iterator();
    idListIter = idList.iterator();

    request.setAttribute("user_username", username);
    request.setAttribute("user_id", userID);
    httpSession.setAttribute("user_username", username);
    httpSession.setAttribute("user_id", userID);

    String darkIcon;
    if (genderListIter.hasNext() && genderList.get(0).equals("female")) {
        darkIcon = "static/images/femaledark.png";
    } else if (genderListIter.hasNext() && genderList.get(0).equals("female")) {
        darkIcon = "static/images/maledark.png";
    } else {
        darkIcon = "static/images/unknowndark.png";
    }

    String[] mentorNumValue = new String[5];
    for (int i = 0; i < 5; i++) {
        mentorNumValue[i] = idListIter.hasNext() ? idListIter.next() : "0";
    }

    Map<String, String> mentorMap = (Map<String, String>) request.getAttribute("mentor_map");
%>

<head>
    <meta charset="UTF-8">
    <title>My_mentor</title>
    <link rel="stylesheet" type="text/css" href="static/css/userpage_css/bootstrap.min.css">
    <link rel="stylesheet" href="static/css/userpage_css/style.css">
    <style>
        .table {
            width: 1200px;
        }
    </style>
</head>

<body>
<h1 style="text-align: center;color: rgb(85, 74, 90);text-shadow: rgb(156, 153, 153) 2.5px 1.5px 1.5px;">My Mentor
</h1>

<div align="center">
    <td colspan="2" align="center"><input type="button" onclick="window.location.href='userMainPageServlet';"
                                          value="return"
                                          style="font-size:large; font-weight: bolder; font-family: Arial, Helvetica, sans-serif;color:rgba(31, 53, 150, 0.945);background-image: linear-gradient(125deg,white,#bfd87b);border-radius: 6px;border: thistle;">
    </td>
</div>
<br>
<table class="table table-hover table-bordered" align="center">
    <tr>
        <th class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> head icon</th>
        <th class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> screen name</th>
        <th class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> gender</th>
        <th class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> operation</th>

    </tr>
    <tr>
        <td align="center"><img src="<%=mentorMap.get("headicon")%>" width="100" height="100"></td>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);">
            <%=mentorMap.get("screenname")%>
        </td>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);">
            <%=mentorMap.get("gender")%>
        </td>
        <td class="text-center" style="display:table-cell; vertical-align:middle;">
            <form action="<%=(!mentorNumValue[0].equals("0"))? "cancelMentorServlet": "#"%>" method="post">
                <input type="hidden" name="mentor" value=<%=mentorNumValue[0]%>>
                <button type=submit class="btn btn-default">cancel choice</button>
            </form>
        </td>
    </tr>
</table>

<br><br>
<h2 style="text-align: center;color: rgb(85, 74, 90);text-shadow: rgb(156, 153, 153) 2.5px 1.5px 1.5px;">Select your
    mentor
</h2>

<table class="table table-hover table-bordered" align="center">
    <br>
    <td align="center">
        <span style="font-size: 16px;color:red">
            <%=request.getAttribute("msg") == null ? "You can choose maximum one mentor" : request.getAttribute("msg")%>
        </span>
    </td>
    <br>
</table>

<br>
<table class="table table-hover table-bordered" align="center">
    <tr>
        <th class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> head icon</th>
        <th class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> screenname</th>
        <th class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> gender</th>
        <th class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> operation</th>
    </tr>
    <tr>
        <td align="center"><img src="<%=headIconListIter.hasNext()? headIconListIter.next(): darkIcon%>" width="100"
                                height="100"></td>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);">
            <%=userNameListIter.hasNext() ? userNameListIter.next() : ""%>
        </td>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);">
            <%=genderListIter.hasNext() ? genderListIter.next() : ""%>
        </td>
        <td class="text-center" style="display:table-cell; vertical-align:middle;">
            <form action="<%=(!mentorNumValue[0].equals("0"))? "addMentorServlet": "#"%>" method="post">
                <input type="hidden" name="mentor" value=<%=mentorNumValue[0]%>>
                <button type=submit class="btn btn-default">choose</button>
            </form>
        </td>
    </tr>
    <tr>
        <td align="center"><img src="<%=headIconListIter.hasNext()? headIconListIter.next(): darkIcon%>" width="100"
                                height="100"></td>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);">
            <%=userNameListIter.hasNext() ? userNameListIter.next() : ""%>
        </td>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);">
            <%=genderListIter.hasNext() ? genderListIter.next() : ""%>
        </td>
        <td class="text-center" style="display:table-cell; vertical-align:middle;">
            <form action="<%=(!mentorNumValue[1].equals("0"))? "addMentorServlet": "#"%>" method="post">
                <input type="hidden" name="mentor" value=<%=mentorNumValue[1]%>>
                <button type=submit class="btn btn-default">choose</button>
            </form>
        </td>
    </tr>
    <tr>
        <td align="center"><img src="<%=headIconListIter.hasNext()? headIconListIter.next(): darkIcon%>" width="100"
                                height="100"></td>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);">
            <%=userNameListIter.hasNext() ? userNameListIter.next() : ""%>
        </td>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);">
            <%=genderListIter.hasNext() ? genderListIter.next() : ""%>
        </td>
        <td class="text-center" style="display:table-cell; vertical-align:middle;">
            <form action="<%=(!mentorNumValue[2].equals("0"))? "addMentorServlet": "#"%>" method="post">
                <input type="hidden" name="mentor" value=<%=mentorNumValue[2]%>>
                <button type=submit class="btn btn-default">choose</button>
            </form>
        </td>
    </tr>
    <tr>
        <td align="center"><img src="<%=headIconListIter.hasNext()? headIconListIter.next(): darkIcon%>" width="100"
                                height="100"></td>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);">
            <%=userNameListIter.hasNext() ? userNameListIter.next() : ""%>
        </td>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);">
            <%=genderListIter.hasNext() ? genderListIter.next() : ""%>
        </td>
        <td class="text-center" style="display:table-cell; vertical-align:middle;">
            <form action="<%=(!mentorNumValue[3].equals("0"))? "addMentorServlet": "#"%>" method="post">
                <input type="hidden" name="mentor" value=<%=mentorNumValue[3]%>>
                <button type=submit class="btn btn-default">choose</button>
            </form>
        </td>
    </tr>
    <tr>
        <td align="center"><img src="<%=headIconListIter.hasNext()? headIconListIter.next(): darkIcon%>" width="100"
                                height="100"></td>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);">
            <%=userNameListIter.hasNext() ? userNameListIter.next() : ""%>
        </td>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);">
            <%=genderListIter.hasNext() ? genderListIter.next() : ""%>
        </td>
        <td class="text-center" style="display:table-cell; vertical-align:middle;">
            <form action="<%=(!mentorNumValue[4].equals("0"))? "addMentorServlet": "#"%>" method="post">
                <input type="hidden" name="mentor" value=<%=mentorNumValue[4]%>>
                <button type=submit class="btn btn-default">choose</button>
            </form>
        </td>
    </tr>
</table>
</body>

</html>