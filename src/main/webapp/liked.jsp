<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Likes</title>

    <link rel="stylesheet" type="text/css" href="static/css/userpage_css/bootstrap.min.css">
    <link rel="stylesheet" href="static/css/userpage_css/style.css">
</head>
<style>
    .table {
        width: 1200px;

    }
</style>
<body>

<%
    HttpSession httpSession = request.getSession();
    String username = null;
    int userID = -1;

    try {
        username = (String) request.getAttribute("user_username");
        // usernameMsg = (request.getAttribute("msg_username") != null) ? (String) request.getAttribute("msg_username"): "";
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

    for (int i = 0; i < (pageNum - 1) * 5; i++) {
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

    String[] userNumValue = new String[5];
    for (int i = 0; i < 5; i++) {
        userNumValue[i] = userNumbersIter.hasNext()? userNumbersIter.next(): "0";
    }
%>

<h2 style="text-align: center;color: rgb(85, 74, 90);text-shadow: rgb(156, 153, 153) 2.5px 1.5px 1.5px;">People who are
    interested you</h2>

<div align="right">
    <td colspan="2" align="center"><input type="button" onclick="window.location.href='userMainPageServlet';"
                                          value="return" style="font-size:large; font-weight: bolder; font-family: Arial, Helvetica, sans-serif;color:rgba(31, 53, 150, 0.945);background-image: linear-gradient(125deg,white,#bfd87b);border-radius: 6px;border: thistle;"></td>
</div>

<table class="table table-hover table-bordered" align="center">
    <tr>
        <th class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> head icon</th>
        <th class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> username</th>
        <th class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> gender</th>
        <th class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> age</th>
        <th class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> work</th>
        <th class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> operation</th>
    </tr>

    <tr>
        <td align="center"><img src="<%=headIconListIter.hasNext()? headIconListIter.next(): darkIcon%>" width="100"
                                height="100"></td>
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
        <td class="text-center" style="display:table-cell; vertical-align:middle;"><a class="btn btn-primary" href="#">
            <form action="<%=(!userNumValue[0].equals("0"))? "userDetailServlet": "#"%>" method="post">
                <input type="hidden" name="num_liked" value=<%=userNumValue[0]%>>
                <button type=submit class="btn btn-default">detail information</button>
            </form>
        </a></td>
        <!--<td class="text-center" style="display:table-cell; vertical-align:middle;"><a class="btn btn-primary" href="#">
            <form action="userDetailServlet" method="post">
                <input type="hidden" name="num_like" value=</%=userNumValue[0]%>>
                <button type=submit class="btn btn-default">Upgrade relations</button>
            </form>
        </a></td>
        <td class="text-center" style="display:table-cell; vertical-align:middle;"><a class="btn btn-primary" href="#">
            <form action="userDetailServlet" method="post">
                <input type="hidden" name="num_like" value=</%=userNumValue[0]%>>
                <button type=submit class="btn btn-default">Degrade relations</button>
            </form>
        </a></td>
        <td class="text-center" style="display:table-cell; vertical-align:middle;"><a class="btn btn-primary" href="#">
            <form action="userDetailServlet" method="post">
                <input type="hidden" name="num_like" value=</%=userNumValue[0]%>>
                <button type=submit class="btn btn-default">Cancel relations</button>
            </form>
        </a></td>-->
    </tr>
    <tr>
        <td align="center"><img src="<%=headIconListIter.hasNext()? headIconListIter.next(): darkIcon%>" width="100"
                                height="100"></td>
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
        <td class="text-center" style="display:table-cell; vertical-align:middle;"><a class="btn btn-primary" href="#">
            <form action="<%=(!userNumValue[1].equals("0"))? "userDetailServlet": "#"%>" method="post">
                <input type="hidden" name="num_liked" value=<%=userNumValue[1]%>>
                <button type=submit class="btn btn-default">detail information</button>
            </form>
        </a></td>
        <!--<td class="text-center" style="display:table-cell; vertical-align:middle;"><a class="btn btn-primary" href="#">
            <form action="userDetailServlet" method="post">
                <input type="hidden" name="num_like" value=</%=userNumValue[0]%>>
                <button type=submit class="btn btn-default">Upgrade relations</button>
            </form>
        </a></td>
        <td class="text-center" style="display:table-cell; vertical-align:middle;"><a class="btn btn-primary" href="#">
            <form action="userDetailServlet" method="post">
                <input type="hidden" name="num_like" value=</%=userNumValue[0]%>>
                <button type=submit class="btn btn-default">Degrade relations</button>
            </form>
        </a></td>
        <td class="text-center" style="display:table-cell; vertical-align:middle;"><a class="btn btn-primary" href="#">
            <form action="userDetailServlet" method="post">
                <input type="hidden" name="num_like" value=</%=userNumValue[0]%>>
                <button type=submit class="btn btn-default">Cancel relations</button>
            </form>
        </a></td>-->
    </tr>
    <tr>
        <td align="center"><img src="<%=headIconListIter.hasNext()? headIconListIter.next(): darkIcon%>" width="100"
                                height="100"></td>
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
        <td class="text-center" style="display:table-cell; vertical-align:middle;"><a class="btn btn-primary" href="#">
            <form action="<%=(!userNumValue[2].equals("0"))? "userDetailServlet": "#"%>" method="post">
                <input type="hidden" name="num_liked" value=<%=userNumValue[2]%>>
                <button type=submit class="btn btn-default">detail information</button>
            </form>
        </a></td>
        <!--<td class="text-center" style="display:table-cell; vertical-align:middle;"><a class="btn btn-primary" href="#">
            <form action="userDetailServlet" method="post">
                <input type="hidden" name="num_like" value=</%=userNumValue[0]%>>
                <button type=submit class="btn btn-default">Upgrade relations</button>
            </form>
        </a></td>
        <td class="text-center" style="display:table-cell; vertical-align:middle;"><a class="btn btn-primary" href="#">
            <form action="userDetailServlet" method="post">
                <input type="hidden" name="num_like" value=</%=userNumValue[0]%>>
                <button type=submit class="btn btn-default">Degrade relations</button>
            </form>
        </a></td>
        <td class="text-center" style="display:table-cell; vertical-align:middle;"><a class="btn btn-primary" href="#">
            <form action="userDetailServlet" method="post">
                <input type="hidden" name="num_like" value=</%=userNumValue[0]%>>
                <button type=submit class="btn btn-default">Cancel relations</button>
            </form>
        </a></td>-->
    </tr>
    <tr>
        <td align="center"><img src="<%=headIconListIter.hasNext()? headIconListIter.next(): darkIcon%>" width="100"
                                height="100"></td>
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
        <td class="text-center" style="display:table-cell; vertical-align:middle;"><a class="btn btn-primary" href="#">
            <form action="<%=(!userNumValue[3].equals("0"))? "userDetailServlet": "#"%>" method="post">
                <input type="hidden" name="num_liked" value=<%=userNumValue[3]%>>
                <button type=submit class="btn btn-default">detail information</button>
            </form>
        </a></td>
        <!--<td class="text-center" style="display:table-cell; vertical-align:middle;"><a class="btn btn-primary" href="#">
            <form action="userDetailServlet" method="post">
                <input type="hidden" name="num_like" value=</%=userNumValue[0]%>>
                <button type=submit class="btn btn-default">Upgrade relations</button>
            </form>
        </a></td>
        <td class="text-center" style="display:table-cell; vertical-align:middle;"><a class="btn btn-primary" href="#">
            <form action="userDetailServlet" method="post">
                <input type="hidden" name="num_like" value=</%=userNumValue[0]%>>
                <button type=submit class="btn btn-default">Degrade relations</button>
            </form>
        </a></td>
        <td class="text-center" style="display:table-cell; vertical-align:middle;"><a class="btn btn-primary" href="#">
            <form action="userDetailServlet" method="post">
                <input type="hidden" name="num_like" value=</%=userNumValue[0]%>>
                <button type=submit class="btn btn-default">Cancel relations</button>
            </form>
        </a></td>-->
    </tr>

    <tr>
        <td align="center"><img src="<%=headIconListIter.hasNext()? headIconListIter.next(): darkIcon%>" width="100"
                                height="100"></td>
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
        <td class="text-center" style="display:table-cell; vertical-align:middle;"><a class="btn btn-primary" href="#">
            <form action="<%=(!userNumValue[4].equals("0"))? "userDetailServlet": "#"%>" method="post">
                <input type="hidden" name="num_liked" value=<%=userNumValue[4]%>>
                <button type=submit class="btn btn-default">detail information</button>
            </form>
        </a></td>
        <!--<td class="text-center" style="display:table-cell; vertical-align:middle;"><a class="btn btn-primary" href="#">
            <form action="userDetailServlet" method="post">
                <input type="hidden" name="num_like" value=</%=userNumValue[0]%>>
                <button type=submit class="btn btn-default">Upgrade relations</button>
            </form>
        </a></td>
        <td class="text-center" style="display:table-cell; vertical-align:middle;"><a class="btn btn-primary" href="#">
            <form action="userDetailServlet" method="post">
                <input type="hidden" name="num_like" value=</%=userNumValue[0]%>>
                <button type=submit class="btn btn-default">Degrade relations</button>
            </form>
        </a></td>
        <td class="text-center" style="display:table-cell; vertical-align:middle;"><a class="btn btn-primary" href="#">
            <form action="userDetailServlet" method="post">
                <input type="hidden" name="num_like" value=</%=userNumValue[0]%>>
                <button type=submit class="btn btn-default">Cancel relations</button>
            </form>
        </a></td>-->
    </tr>
</table>

<div align="center">                        <!--next page-->
    <nav aria-label="Page navigation">
        <ul class="pagination pagination-lg">  <!--large -->
            <li>
                <a href="liked.jsp?pageNum=<%=pageNum - 1%>" aria-label="Previous">
                    <span aria-hidden="true">Previous</span>
                </a>
            </li>

            <li>
                <a href="liked.jsp?pageNum=<%=pageNum + 1%>" aria-label="Next">
                    <span aria-hidden="true">Next</span>
                </a>
            </li>
        </ul>
    </nav>
</div>

</body>
</html>