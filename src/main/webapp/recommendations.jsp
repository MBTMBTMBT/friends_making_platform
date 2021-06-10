<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.LinkedList" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Recommendations</title>
  <link rel="stylesheet" type="text/css" href="static/css/userpage_css/bootstrap.min.css">
  <link rel="stylesheet" href="static/css/userpage_css/style.css">
</head>

<body>
<h2 style="text-align: center">Recommendations</h2>

<%
  String username = (String) request.getAttribute("user_username");
  int userID = (int) request.getAttribute("user_id");

  List<List<String>> infoLists = (List<List<String>>) request.getAttribute("info_lists");
  Iterator<List<String>> listIterator = infoLists.iterator();

  List<String> headIconList, userNameList, genderList, ageList, workList;
  headIconList = listIterator.next();
  userNameList = listIterator.next();
  genderList = listIterator.next();
  ageList = listIterator.next();
  workList = listIterator.next();

  Iterator<String> headIconListIter, userNameListIter, genderListIter, ageListIter, workListIter;
  headIconListIter = headIconList.iterator();
  userNameListIter = userNameList.iterator();
  genderListIter = genderList.iterator();
  ageListIter = ageList.iterator();
  workListIter = workList.iterator();

  // List<Integer> userNumbers = new LinkedList<>();
  // for (int i = 1; i <= headIconList.size(); i++) userNumbers.add(i);
  Iterator<String> userNumbersIter = listIterator.next().iterator();

  request.setAttribute("user_username", username);
  request.setAttribute("user_id", userID);
  HttpSession httpSession = request.getSession();
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
%>

<form action="#" method = "post" id="search_username">
  <br>
  <div align="center">
    <div>
      <form class="form-inline" action="mentorMainPageServlet" method="post" id="search_user_by_mentor">
        <input type="text" name="find_screenname" id="find_screenname" placeholder="Input screenname">
        <button type="submit" class="btn btn-warning" style="color: rgb(73, 41, 85)">Search</button>
        <button type="reset" class="btn btn-default" style="color: rgb(73, 41, 85)">reset</button>
        <a class="btn btn-default" href="userMatchingServlet">cancel</a>
      </form>
    </div>
  </div>

  <div align="right">
  <td colspan="2" align="center"><input type="button" onclick="window.location.href='userMainPageServlet';"
                                        value="return" style="font-size:large; font-weight: bolder; font-family: Arial, Helvetica, sans-serif;color:rgba(31, 53, 150, 0.945);background-image: linear-gradient(125deg,white,#bfd87b);border-radius: 6px;border: thistle;"></td>
  </div>
</form>

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
    <td align="center" >
      <img src="<%=headIconListIter.hasNext()? headIconListIter.next(): darkIcon%>" width="100" height="100">
    </td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=userNameListIter.hasNext()? userNameListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=genderListIter.hasNext()? genderListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=ageListIter.hasNext()? ageListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=workListIter.hasNext()? workListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;"><a class="btn btn-primary" href="#">
      <form action="<%=userNumbersIter.hasNext()?"userDetailServlet":"#"%>" method = "post">
        <input type = "hidden" name="num" value=<%=userNumbersIter.hasNext()? userNumbersIter.next(): "0"%>>
        <button type=submit class="btn btn-default">detail information</button>
      </form>
    </a></td>
  </tr>

  <tr>
    <td align="center" >
      <img src="<%=headIconListIter.hasNext()? headIconListIter.next(): darkIcon%>" width="100" height="100">
    </td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=userNameListIter.hasNext()? userNameListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=genderListIter.hasNext()? genderListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=ageListIter.hasNext()? ageListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=workListIter.hasNext()? workListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;"><a class="btn btn-primary" href="#">
      <form action="<%=userNumbersIter.hasNext()?"userDetailServlet":"#"%>" method = "post">
        <input type = "hidden" name="num" value=<%=userNumbersIter.hasNext()? userNumbersIter.next(): "0"%>>
        <button type=submit class="btn btn-default">detail information</button>
      </form>
    </a></td>
  </tr>

  <tr>
    <td align="center" >
      <img src="<%=headIconListIter.hasNext()? headIconListIter.next(): darkIcon%>" width="100" height="100">
    </td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=userNameListIter.hasNext()? userNameListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=genderListIter.hasNext()? genderListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=ageListIter.hasNext()? ageListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=workListIter.hasNext()? workListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;"><a class="btn btn-primary" href="#">
      <form action="<%=userNumbersIter.hasNext()?"userDetailServlet":"#"%>" method = "post">
        <input type = "hidden" name="num" value=<%=userNumbersIter.hasNext()? userNumbersIter.next(): "0"%>>
        <button type=submit class="btn btn-default">detail information</button>
      </form>
    </a></td>
  </tr>

  <tr>
    <td align="center" >
      <img src="<%=headIconListIter.hasNext()? headIconListIter.next(): darkIcon%>" width="100" height="100">
    </td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=userNameListIter.hasNext()? userNameListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=genderListIter.hasNext()? genderListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=ageListIter.hasNext()? ageListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=workListIter.hasNext()? workListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;"><a class="btn btn-primary" href="#">
      <form action="<%=userNumbersIter.hasNext()?"userDetailServlet":"#"%>" method = "post">
        <input type = "hidden" name="num" value=<%=userNumbersIter.hasNext()? userNumbersIter.next(): "0"%>>
        <button type=submit class="btn btn-default">detail information</button>
      </form>
    </a></td>
  </tr>

  <tr>
    <td align="center" >
      <img src="<%=headIconListIter.hasNext()? headIconListIter.next(): darkIcon%>" width="100" height="100">
    </td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=userNameListIter.hasNext()? userNameListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=genderListIter.hasNext()? genderListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=ageListIter.hasNext()? ageListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=workListIter.hasNext()? workListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;"><a class="btn btn-primary" href="#">
      <form action="<%=userNumbersIter.hasNext()?"userDetailServlet":"#"%>" method = "post">
        <input type = "hidden" name="num" value=<%=userNumbersIter.hasNext()? userNumbersIter.next(): "0"%>>
        <button type=submit class="btn btn-default">detail information</button>
      </form>
    </a></td>
  </tr>

  <tr>
    <td align="center" >
      <img src="<%=headIconListIter.hasNext()? headIconListIter.next(): darkIcon%>" width="100" height="100">
    </td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=userNameListIter.hasNext()? userNameListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=genderListIter.hasNext()? genderListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=ageListIter.hasNext()? ageListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=workListIter.hasNext()? workListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;"><a class="btn btn-primary" href="#">
      <form action="<%=userNumbersIter.hasNext()?"userDetailServlet":"#"%>" method = "post">
        <input type = "hidden" name="num" value=<%=userNumbersIter.hasNext()? userNumbersIter.next(): "0"%>>
        <button type=submit class="btn btn-default">detail information</button>
      </form>
    </a></td>
  </tr>

  <tr>
    <td align="center" >
      <img src="<%=headIconListIter.hasNext()? headIconListIter.next(): darkIcon%>" width="100" height="100">
    </td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=userNameListIter.hasNext()? userNameListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=genderListIter.hasNext()? genderListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=ageListIter.hasNext()? ageListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=workListIter.hasNext()? workListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;"><a class="btn btn-primary" href="#">
      <form action="<%=userNumbersIter.hasNext()?"userDetailServlet":"#"%>" method = "post">
        <input type = "hidden" name="num" value=<%=userNumbersIter.hasNext()? userNumbersIter.next(): "0"%>>
        <button type=submit class="btn btn-default">detail information</button>
      </form>
    </a></td>
  </tr>

  <tr>
    <td align="center" >
      <img src="<%=headIconListIter.hasNext()? headIconListIter.next(): darkIcon%>" width="100" height="100">
    </td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=userNameListIter.hasNext()? userNameListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=genderListIter.hasNext()? genderListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=ageListIter.hasNext()? ageListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=workListIter.hasNext()? workListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;"><a class="btn btn-primary" href="#">
      <form action="<%=userNumbersIter.hasNext()?"userDetailServlet":"#"%>" method = "post">
        <input type = "hidden" name="num" value=<%=userNumbersIter.hasNext()? userNumbersIter.next(): "0"%>>
        <button type=submit class="btn btn-default">detail information</button>
      </form>
    </a></td>
  </tr>

  <tr>
    <td align="center" >
      <img src="<%=headIconListIter.hasNext()? headIconListIter.next(): darkIcon%>" width="100" height="100">
    </td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=userNameListIter.hasNext()? userNameListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=genderListIter.hasNext()? genderListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=ageListIter.hasNext()? ageListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=workListIter.hasNext()? workListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;"><a class="btn btn-primary" href="#">
      <form action="<%=userNumbersIter.hasNext()?"userDetailServlet":"#"%>" method = "post">
        <input type = "hidden" name="num" value=<%=userNumbersIter.hasNext()? userNumbersIter.next(): "0"%>>
        <button type=submit class="btn btn-default">detail information</button>
      </form>
    </a></td>
  </tr>

  <tr>
    <td align="center" >
      <img src="<%=headIconListIter.hasNext()? headIconListIter.next(): darkIcon%>" width="100" height="100">
    </td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=userNameListIter.hasNext()? userNameListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=genderListIter.hasNext()? genderListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=ageListIter.hasNext()? ageListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=workListIter.hasNext()? workListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;"><a class="btn btn-primary" href="#">
      <form action="<%=userNumbersIter.hasNext()?"userDetailServlet":"#"%>" method = "post">
        <input type = "hidden" name="num" value=<%=userNumbersIter.hasNext()? userNumbersIter.next(): "0"%>>
        <button type=submit class="btn btn-default">detail information</button>
      </form>
    </a></td>
  </tr>

  <tr>
    <td align="center" >
      <img src="<%=headIconListIter.hasNext()? headIconListIter.next(): darkIcon%>" width="100" height="100">
    </td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=userNameListIter.hasNext()? userNameListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=genderListIter.hasNext()? genderListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=ageListIter.hasNext()? ageListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=workListIter.hasNext()? workListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;"><a class="btn btn-primary" href="#">
      <form action="<%=userNumbersIter.hasNext()?"userDetailServlet":"#"%>" method = "post">
        <input type = "hidden" name="num" value=<%=userNumbersIter.hasNext()? userNumbersIter.next(): "0"%>>
        <button type=submit class="btn btn-default">detail information</button>
      </form>
    </a></td>
  </tr>

  <tr>
    <td align="center" >
      <img src="<%=headIconListIter.hasNext()? headIconListIter.next(): darkIcon%>" width="100" height="100">
    </td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=userNameListIter.hasNext()? userNameListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=genderListIter.hasNext()? genderListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=ageListIter.hasNext()? ageListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=workListIter.hasNext()? workListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;"><a class="btn btn-primary" href="#">
      <form action="<%=userNumbersIter.hasNext()?"userDetailServlet":"#"%>" method = "post">
        <input type = "hidden" name="num" value=<%=userNumbersIter.hasNext()? userNumbersIter.next(): "0"%>>
        <button type=submit class="btn btn-default">detail information</button>
      </form>
    </a></td>
  </tr>

  <tr>
    <td align="center" >
      <img src="<%=headIconListIter.hasNext()? headIconListIter.next(): darkIcon%>" width="100" height="100">
    </td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=userNameListIter.hasNext()? userNameListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=genderListIter.hasNext()? genderListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=ageListIter.hasNext()? ageListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=workListIter.hasNext()? workListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;"><a class="btn btn-primary" href="#">
      <form action="<%=userNumbersIter.hasNext()?"userDetailServlet":"#"%>" method = "post">
        <input type = "hidden" name="num" value=<%=userNumbersIter.hasNext()? userNumbersIter.next(): "0"%>>
        <button type=submit class="btn btn-default">detail information</button>
      </form>
    </a></td>
  </tr>

  <tr>
    <td align="center" >
      <img src="<%=headIconListIter.hasNext()? headIconListIter.next(): darkIcon%>" width="100" height="100">
    </td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=userNameListIter.hasNext()? userNameListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=genderListIter.hasNext()? genderListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=ageListIter.hasNext()? ageListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=workListIter.hasNext()? workListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;"><a class="btn btn-primary" href="#">
      <form action="<%=userNumbersIter.hasNext()?"userDetailServlet":"#"%>" method = "post">
        <input type = "hidden" name="num" value=<%=userNumbersIter.hasNext()? userNumbersIter.next(): "0"%>>
        <button type=submit class="btn btn-default">detail information</button>
      </form>
    </a></td>
  </tr>

  <tr>
    <td align="center" >
      <img src="<%=headIconListIter.hasNext()? headIconListIter.next(): darkIcon%>" width="100" height="100">
    </td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=userNameListIter.hasNext()? userNameListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=genderListIter.hasNext()? genderListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=ageListIter.hasNext()? ageListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=workListIter.hasNext()? workListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;"><a class="btn btn-primary" href="#">
      <form action="<%=userNumbersIter.hasNext()?"userDetailServlet":"#"%>" method = "post">
        <input type = "hidden" name="num" value=<%=userNumbersIter.hasNext()? userNumbersIter.next(): "0"%>>
        <button type=submit class="btn btn-default">detail information</button>
      </form>
    </a></td>
  </tr>

</table>

</body>
</html>