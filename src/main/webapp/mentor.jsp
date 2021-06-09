<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Mentor</title>
    <link rel="stylesheet" type="text/css" href="static/css/userpage_css/bootstrap.min.css">
    <link rel="stylesheet" href="static/css/userpage_css/style.css">
    <style>
        .table {
            width: 1200px;
        }
    </style>
</head>

<%
    HttpSession httpSession = request.getSession();
    String mentorScreenName = null;
    int mentorNum = -1;

    try {
        mentorScreenName = (String) request.getAttribute("mentor_username");
        mentorNum = (int) request.getAttribute("mentor_number");
    } catch (NullPointerException ignore) {
    }

    if (mentorScreenName == null || mentorNum == -1) {
        session = request.getSession();
        mentorScreenName = (String) session.getAttribute("mentor_username");
        mentorNum = (int) session.getAttribute("mentor_number");
    }

    request.setAttribute("mentor_username", mentorScreenName);
    request.setAttribute("mentor_number", mentorNum);
    session.setAttribute("mentor_username", mentorScreenName);
    session.setAttribute("mentor_number", mentorNum);

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

    List<String> headIconList, userNameList, genderList, ageList, workList;
    headIconList = listIterator.next();
    userNameList = listIterator.next();
    genderList = listIterator.next();
    ageList = listIterator.next();
    workList = listIterator.next();

    // get current page number
    int pageNum = 1;
    try {
        pageNum = Integer.parseInt(request.getParameter("pageNum"));
    } catch (NumberFormatException ignore) {
        try {
            pageNum = (int) request.getAttribute("pageNum");
        } catch (NullPointerException ignoreignore) {
        }
    }
    int maxPage = headIconList.size() / 5 + 1;
    if (headIconList.size() % 5 == 0) maxPage -= 1;
    if (maxPage < 1) maxPage = 1;
    if (pageNum > maxPage) pageNum = maxPage;
    if (pageNum < 1) pageNum = 1;
    request.setAttribute("pageNum", pageNum);

    Iterator<String> headIconListIter, userNameListIter, genderListIter, ageListIter, workListIter;
    headIconListIter = headIconList.iterator();
    userNameListIter = userNameList.iterator();
    genderListIter = genderList.iterator();
    ageListIter = ageList.iterator();
    workListIter = workList.iterator();

    for (int i = 0; i < (pageNum - 1) * 10; i++) {
        System.out.println(i);
        headIconListIter.next();
        userNameListIter.next();
        genderListIter.next();
        ageListIter.next();
        workListIter.next();
    }

    // List<Integer> userNumbers = new LinkedList<>();
    // for (int i = 1; i <= headIconList.size(); i++) userNumbers.add(i);
    Iterator<String> userNumbersIter = listIterator.next().iterator();

    String darkIcon;
    if (genderListIter.hasNext() && genderList.get(0).equals("female")) {
        darkIcon = "static/images/femaledark.png";
    } else if (genderListIter.hasNext() && genderList.get(0).equals("female")) {
        darkIcon = "static/images/maledark.png";
    } else {
        darkIcon = "static/images/unknowndark.png";
    }

    String[] userNumValue = new String[10];
    for (int i = 0; i < 10; i++) {
        userNumValue[i] = userNumbersIter.hasNext() ? userNumbersIter.next() : "0";
    }
    int j = 0;
%>

<body>
<h1 style="text-align: center;color: rgb(85, 74, 90);text-shadow: rgb(156, 153, 153) 2.5px 1.5px 1.5px;">Mentor</h1>
<br>
<div align="center">
    <a class="btn btn-info" href="location_event.html">set locations and events</a>
    <a class="btn btn-warning" href="login.jsp">logout</a>
</div>
<br>
<div align="center">
    <div>
        <form class="form-inline" action="mentorMainPageServlet" method="post" id="search_user_by_mentor">
            <input type="text" name="find_screenname" id="find_screenname" placeholder="Input screenname">
            <button type="submit" class="btn btn-warning" style="color: rgb(73, 41, 85)">Search</button>
            <button type="reset" class="btn btn-default" style="color: rgb(73, 41, 85)">reset</button>
            <a class="btn btn-default" href="mentorMainPageServlet">cancel</a>
        </form>
    </div>
</div>
<br>
<table class="table table-hover table-bordered" align="center">
    <tr>
        <th class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> head icon</th>
        <th class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> screenname</th>
        <th class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> gender</th>
        <th class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> age</th>
        <th class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> work</th>
        <th class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> operation</th>
    </tr>

    <tr>
        <td align="center">
            <img src="<%=headIconListIter.hasNext()? headIconListIter.next(): darkIcon%>" width="100" height="100">
        </td>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"><%=userNameListIter.hasNext() ? userNameListIter.next() : ""%>
        </td>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"><%=genderListIter.hasNext() ? genderListIter.next() : ""%>
        </td>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"><%=ageListIter.hasNext() ? ageListIter.next() : ""%>
        </td>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"><%=workListIter.hasNext() ? workListIter.next() : ""%>
        </td>
        <td class="text-center" style="display:table-cell; vertical-align:middle;"><a class="btn btn-primary" href="mentorMainPageServlet">
            <form action="<%=!userNumValue[j].equals("0")?"mentorUserDetailServlet":"mentorMainPageServlet"%>" method="post">
                <input type="hidden" name="num" value=<%=userNumValue[j++]%>>
                <button type=submit class="btn btn-default">detail information</button>
            </form>
        </a></td>
    </tr>

    <tr>
        <td align="center">
            <img src="<%=headIconListIter.hasNext()? headIconListIter.next(): darkIcon%>" width="100" height="100">
        </td>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"><%=userNameListIter.hasNext() ? userNameListIter.next() : ""%>
        </td>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"><%=genderListIter.hasNext() ? genderListIter.next() : ""%>
        </td>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"><%=ageListIter.hasNext() ? ageListIter.next() : ""%>
        </td>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"><%=workListIter.hasNext() ? workListIter.next() : ""%>
        </td>
        <td class="text-center" style="display:table-cell; vertical-align:middle;"><a class="btn btn-primary" href="mentorMainPageServlet">
            <form action="<%=!userNumValue[j].equals("0")?"mentorUserDetailServlet":"mentorMainPageServlet"%>" method="post">
                <input type="hidden" name="num" value=<%=userNumValue[j++]%>>
                <button type=submit class="btn btn-default">detail information</button>
            </form>
        </a></td>
    </tr>

    <tr>
        <td align="center">
            <img src="<%=headIconListIter.hasNext()? headIconListIter.next(): darkIcon%>" width="100" height="100">
        </td>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"><%=userNameListIter.hasNext() ? userNameListIter.next() : ""%>
        </td>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"><%=genderListIter.hasNext() ? genderListIter.next() : ""%>
        </td>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"><%=ageListIter.hasNext() ? ageListIter.next() : ""%>
        </td>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"><%=workListIter.hasNext() ? workListIter.next() : ""%>
        </td>
        <td class="text-center" style="display:table-cell; vertical-align:middle;"><a class="btn btn-primary" href="mentorMainPageServlet">
            <form action="<%=!userNumValue[j].equals("0")?"mentorUserDetailServlet":"mentorMainPageServlet"%>" method="post">
                <input type="hidden" name="num" value=<%=userNumValue[j++]%>>
                <button type=submit class="btn btn-default">detail information</button>
            </form>
        </a></td>
    </tr>

    <tr>
        <td align="center">
            <img src="<%=headIconListIter.hasNext()? headIconListIter.next(): darkIcon%>" width="100" height="100">
        </td>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"><%=userNameListIter.hasNext() ? userNameListIter.next() : ""%>
        </td>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"><%=genderListIter.hasNext() ? genderListIter.next() : ""%>
        </td>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"><%=ageListIter.hasNext() ? ageListIter.next() : ""%>
        </td>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"><%=workListIter.hasNext() ? workListIter.next() : ""%>
        </td>
        <td class="text-center" style="display:table-cell; vertical-align:middle;"><a class="btn btn-primary" href="mentorMainPageServlet">
            <form action="<%=!userNumValue[j].equals("0")?"mentorUserDetailServlet":"mentorMainPageServlet"%>" method="post">
                <input type="hidden" name="num" value=<%=userNumValue[j++]%>>
                <button type=submit class="btn btn-default">detail information</button>
            </form>
        </a></td>
    </tr>

    <tr>
        <td align="center">
            <img src="<%=headIconListIter.hasNext()? headIconListIter.next(): darkIcon%>" width="100" height="100">
        </td>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"><%=userNameListIter.hasNext() ? userNameListIter.next() : ""%>
        </td>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"><%=genderListIter.hasNext() ? genderListIter.next() : ""%>
        </td>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"><%=ageListIter.hasNext() ? ageListIter.next() : ""%>
        </td>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"><%=workListIter.hasNext() ? workListIter.next() : ""%>
        </td>
        <td class="text-center" style="display:table-cell; vertical-align:middle;"><a class="btn btn-primary" href="mentorMainPageServlet">
            <form action="<%=!userNumValue[j].equals("0")?"mentorUserDetailServlet":"mentorMainPageServlet"%>" method="post">
                <input type="hidden" name="num" value=<%=userNumValue[j++]%>>
                <button type=submit class="btn btn-default">detail information</button>
            </form>
        </a></td>
    </tr>

    <tr>
        <td align="center">
            <img src="<%=headIconListIter.hasNext()? headIconListIter.next(): darkIcon%>" width="100" height="100">
        </td>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"><%=userNameListIter.hasNext() ? userNameListIter.next() : ""%>
        </td>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"><%=genderListIter.hasNext() ? genderListIter.next() : ""%>
        </td>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"><%=ageListIter.hasNext() ? ageListIter.next() : ""%>
        </td>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"><%=workListIter.hasNext() ? workListIter.next() : ""%>
        </td>
        <td class="text-center" style="display:table-cell; vertical-align:middle;"><a class="btn btn-primary" href="mentorMainPageServlet">
            <form action="<%=!userNumValue[j].equals("0")?"mentorUserDetailServlet":"mentorMainPageServlet"%>" method="post">
                <input type="hidden" name="num" value=<%=userNumValue[j++]%>>
                <button type=submit class="btn btn-default">detail information</button>
            </form>
        </a></td>
    </tr>

    <tr>
        <td align="center">
            <img src="<%=headIconListIter.hasNext()? headIconListIter.next(): darkIcon%>" width="100" height="100">
        </td>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"><%=userNameListIter.hasNext() ? userNameListIter.next() : ""%>
        </td>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"><%=genderListIter.hasNext() ? genderListIter.next() : ""%>
        </td>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"><%=ageListIter.hasNext() ? ageListIter.next() : ""%>
        </td>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"><%=workListIter.hasNext() ? workListIter.next() : ""%>
        </td>
        <td class="text-center" style="display:table-cell; vertical-align:middle;"><a class="btn btn-primary" href="mentorMainPageServlet">
            <form action="<%=!userNumValue[j].equals("0")?"mentorUserDetailServlet":"mentorMainPageServlet"%>" method="post">
                <input type="hidden" name="num" value=<%=userNumValue[j++]%>>
                <button type=submit class="btn btn-default">detail information</button>
            </form>
        </a></td>
    </tr>

    <tr>
        <td align="center">
            <img src="<%=headIconListIter.hasNext()? headIconListIter.next(): darkIcon%>" width="100" height="100">
        </td>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"><%=userNameListIter.hasNext() ? userNameListIter.next() : ""%>
        </td>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"><%=genderListIter.hasNext() ? genderListIter.next() : ""%>
        </td>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"><%=ageListIter.hasNext() ? ageListIter.next() : ""%>
        </td>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"><%=workListIter.hasNext() ? workListIter.next() : ""%>
        </td>
        <td class="text-center" style="display:table-cell; vertical-align:middle;"><a class="btn btn-primary" href="mentorMainPageServlet">
            <form action="<%=!userNumValue[j].equals("0")?"mentorUserDetailServlet":"mentorMainPageServlet"%>" method="post">
                <input type="hidden" name="num" value=<%=userNumValue[j++]%>>
                <button type=submit class="btn btn-default">detail information</button>
            </form>
        </a></td>
    </tr>

    <tr>
        <td align="center">
            <img src="<%=headIconListIter.hasNext()? headIconListIter.next(): darkIcon%>" width="100" height="100">
        </td>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"><%=userNameListIter.hasNext() ? userNameListIter.next() : ""%>
        </td>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"><%=genderListIter.hasNext() ? genderListIter.next() : ""%>
        </td>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"><%=ageListIter.hasNext() ? ageListIter.next() : ""%>
        </td>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"><%=workListIter.hasNext() ? workListIter.next() : ""%>
        </td>
        <td class="text-center" style="display:table-cell; vertical-align:middle;"><a class="btn btn-primary" href="mentorMainPageServlet">
            <form action="<%=!userNumValue[j].equals("0")?"mentorUserDetailServlet":"mentorMainPageServlet"%>" method="post">
                <input type="hidden" name="num" value=<%=userNumValue[j++]%>>
                <button type=submit class="btn btn-default">detail information</button>
            </form>
        </a></td>
    </tr>

    <tr>
        <td align="center">
            <img src="<%=headIconListIter.hasNext()? headIconListIter.next(): darkIcon%>" width="100" height="100">
        </td>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"><%=userNameListIter.hasNext() ? userNameListIter.next() : ""%>
        </td>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"><%=genderListIter.hasNext() ? genderListIter.next() : ""%>
        </td>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"><%=ageListIter.hasNext() ? ageListIter.next() : ""%>
        </td>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"><%=workListIter.hasNext() ? workListIter.next() : ""%>
        </td>
        <td class="text-center" style="display:table-cell; vertical-align:middle;"><a class="btn btn-primary" href="mentorMainPageServlet">
            <form action="<%=!userNumValue[j].equals("0")?"mentorUserDetailServlet":"mentorMainPageServlet"%>" method="post">
                <input type="hidden" name="num" value=<%=userNumValue[j++]%>>
                <button type=submit class="btn btn-default">detail information</button>
            </form>
        </a></td>
    </tr>
</table>

<div align="center">                        <!--next page-->
    <nav aria-label="Page navigation">
        <ul class="pagination pagination-lg">  <!--large -->
            <li>
                <a href="like.jsp?pageNum=<%=pageNum - 1%>" aria-label="Previous">
                    <span aria-hidden="true">Previous</span>
                </a>
            </li>

            <li>
                <a href="like.jsp?pageNum=<%=pageNum + 1%>" aria-label="Next">
                    <span aria-hidden="true">Next</span>
                </a>
            </li>
        </ul>
    </nav>
</div>

</body>
</html>