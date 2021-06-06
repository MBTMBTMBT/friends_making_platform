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
%>

<form action="#" method = "post" id="search_username">
  <br>
  <input type = "text" placeholder="search username" name = "search_username" style="font-size: large"
         style="background-color:rgba(0,0,1,0.5)">
  <button type=submit>search</button>
  <td colspan="2" align="center"><input type="button" onclick="window.location.href='userMainPageServlet';"
                                        value="confirm and exit" style="font-size:large; font-weight: bolder; font-family: Arial, Helvetica, sans-serif;color:rgba(31, 53, 150, 0.945);background-image: linear-gradient(125deg,white,#bfd87b);border-radius: 6px;border: thistle;"></td>
</form>

<table class="table table-striped  table-bordered" >
  <tr>
    <th class="text-center"> head icon</th>
    <th class="text-center"> screenname</th>
    <th class="text-center"> gender</th>
    <th class="text-center"> age</th>
    <th class="text-center"> work</th>
    <th class="text-center"> operation</th>
  </tr>

  <tr>
    <td class="text-center"> <%=headIconListIter.hasNext()? headIconListIter.next(): ""%></td>
    <td class="text-center"> <%=userNameListIter.hasNext()? userNameListIter.next(): ""%></td>
    <td class="text-center"> <%=genderListIter.hasNext()? genderListIter.next(): ""%></td>
    <td class="text-center"> <%=ageListIter.hasNext()? ageListIter.next(): ""%></td>
    <td class="text-center"> <%=workListIter.hasNext()? workListIter.next(): ""%></td>
    <td class="text-center"><a class="btn btn-primary" href="#">
      <form action="userDetailServlet" method = "post" >
        <input type = "hidden" name="num" value=<%=userNumbersIter.hasNext()? userNumbersIter.next(): "0"%>>
        <button type=submit>detail information</button>
      </form>
    </a></td>

  </tr>

  <tr>
    <td class="text-center"> <%=headIconListIter.hasNext()? headIconListIter.next(): ""%></td>
    <td class="text-center"> <%=userNameListIter.hasNext()? userNameListIter.next(): ""%></td>
    <td class="text-center"> <%=genderListIter.hasNext()? genderListIter.next(): ""%></td>
    <td class="text-center"> <%=ageListIter.hasNext()? ageListIter.next(): ""%></td>
    <td class="text-center"> <%=workListIter.hasNext()? workListIter.next(): ""%></td>
    <td class="text-center"><a class="btn btn-primary" href="#">
      <form action="userDetailServlet" method = "post" >
        <input type = "hidden" name="num" value=<%=userNumbersIter.hasNext()? userNumbersIter.next(): "0"%>>
        <button type=submit>detail information</button>
      </form>
    </a></td>
  </tr>

  <tr>
    <td class="text-center"> <%=headIconListIter.hasNext()? headIconListIter.next(): ""%></td>
    <td class="text-center"> <%=userNameListIter.hasNext()? userNameListIter.next(): ""%></td>
    <td class="text-center"> <%=genderListIter.hasNext()? genderListIter.next(): ""%></td>
    <td class="text-center"> <%=ageListIter.hasNext()? ageListIter.next(): ""%></td>
    <td class="text-center"> <%=workListIter.hasNext()? workListIter.next(): ""%></td>
    <td class="text-center"><a class="btn btn-primary" href="#">
      <form action="userDetailServlet" method = "post" >
        <input type = "hidden" name="num" value=<%=userNumbersIter.hasNext()? userNumbersIter.next(): "0"%>>
        <button type=submit>detail information</button>
      </form>
    </a></td>
  </tr>

  <tr>
    <td class="text-center"> <%=headIconListIter.hasNext()? headIconListIter.next(): ""%></td>
    <td class="text-center"> <%=userNameListIter.hasNext()? userNameListIter.next(): ""%></td>
    <td class="text-center"> <%=genderListIter.hasNext()? genderListIter.next(): ""%></td>
    <td class="text-center"> <%=ageListIter.hasNext()? ageListIter.next(): ""%></td>
    <td class="text-center"> <%=workListIter.hasNext()? workListIter.next(): ""%></td>
    <td class="text-center"><a class="btn btn-primary" href="#">
      <form action="userDetailServlet" method = "post" >
        <input type = "hidden" name="num" value=<%=userNumbersIter.hasNext()? userNumbersIter.next(): "0"%>>
        <button type=submit>detail information</button>
      </form>
    </a></td>
  </tr>

  <tr>
    <td class="text-center"> <%=headIconListIter.hasNext()? headIconListIter.next(): ""%></td>
    <td class="text-center"> <%=userNameListIter.hasNext()? userNameListIter.next(): ""%></td>
    <td class="text-center"> <%=genderListIter.hasNext()? genderListIter.next(): ""%></td>
    <td class="text-center"> <%=ageListIter.hasNext()? ageListIter.next(): ""%></td>
    <td class="text-center"> <%=workListIter.hasNext()? workListIter.next(): ""%></td>
    <td class="text-center"><a class="btn btn-primary" href="#">
      <form action="userDetailServlet" method = "post" >
        <input type = "hidden" name="num" value=<%=userNumbersIter.hasNext()? userNumbersIter.next(): "0"%>>
        <button type=submit>detail information</button>
      </form>
    </a></td>
  </tr>

  <tr>
    <td class="text-center"> <%=headIconListIter.hasNext()? headIconListIter.next(): ""%></td>
    <td class="text-center"> <%=userNameListIter.hasNext()? userNameListIter.next(): ""%></td>
    <td class="text-center"> <%=genderListIter.hasNext()? genderListIter.next(): ""%></td>
    <td class="text-center"> <%=ageListIter.hasNext()? ageListIter.next(): ""%></td>
    <td class="text-center"> <%=workListIter.hasNext()? workListIter.next(): ""%></td>
    <td class="text-center"><a class="btn btn-primary" href="#">
      <form action="userDetailServlet" method = "post" >
        <input type = "hidden" name="num" value=<%=userNumbersIter.hasNext()? userNumbersIter.next(): "0"%>>
        <button type=submit>detail information</button>
      </form>
    </a></td>
  </tr>

  <tr>
    <td class="text-center"> <%=headIconListIter.hasNext()? headIconListIter.next(): ""%></td>
    <td class="text-center"> <%=userNameListIter.hasNext()? userNameListIter.next(): ""%></td>
    <td class="text-center"> <%=genderListIter.hasNext()? genderListIter.next(): ""%></td>
    <td class="text-center"> <%=ageListIter.hasNext()? ageListIter.next(): ""%></td>
    <td class="text-center"> <%=workListIter.hasNext()? workListIter.next(): ""%></td>
    <td class="text-center"><a class="btn btn-primary" href="#">
      <form action="userDetailServlet" method = "post" >
        <input type = "hidden" name="num" value=<%=userNumbersIter.hasNext()? userNumbersIter.next(): "0"%>>
        <button type=submit>detail information</button>
      </form>
    </a></td>
  </tr>

  <tr>
    <td class="text-center"> <%=headIconListIter.hasNext()? headIconListIter.next(): ""%></td>
    <td class="text-center"> <%=userNameListIter.hasNext()? userNameListIter.next(): ""%></td>
    <td class="text-center"> <%=genderListIter.hasNext()? genderListIter.next(): ""%></td>
    <td class="text-center"> <%=ageListIter.hasNext()? ageListIter.next(): ""%></td>
    <td class="text-center"> <%=workListIter.hasNext()? workListIter.next(): ""%></td>
    <td class="text-center"><a class="btn btn-primary" href="#">
      <form action="userDetailServlet" method = "post" >
        <input type = "hidden" name="num" value=<%=userNumbersIter.hasNext()? userNumbersIter.next(): "0"%>>
        <button type=submit>detail information</button>
      </form>
    </a></td>
  </tr>

  <tr>
    <td class="text-center"> <%=headIconListIter.hasNext()? headIconListIter.next(): ""%></td>
    <td class="text-center"> <%=userNameListIter.hasNext()? userNameListIter.next(): ""%></td>
    <td class="text-center"> <%=genderListIter.hasNext()? genderListIter.next(): ""%></td>
    <td class="text-center"> <%=ageListIter.hasNext()? ageListIter.next(): ""%></td>
    <td class="text-center"> <%=workListIter.hasNext()? workListIter.next(): ""%></td>
    <td class="text-center"><a class="btn btn-primary" href="#">
      <form action="userDetailServlet" method = "post" >
        <input type = "hidden" name="num" value=<%=userNumbersIter.hasNext()? userNumbersIter.next(): "0"%>>
        <button type=submit>detail information</button>
      </form>
    </a></td>
  </tr>

  <tr>
    <td class="text-center"> <%=headIconListIter.hasNext()? headIconListIter.next(): ""%></td>
    <td class="text-center"> <%=userNameListIter.hasNext()? userNameListIter.next(): ""%></td>
    <td class="text-center"> <%=genderListIter.hasNext()? genderListIter.next(): ""%></td>
    <td class="text-center"> <%=ageListIter.hasNext()? ageListIter.next(): ""%></td>
    <td class="text-center"> <%=workListIter.hasNext()? workListIter.next(): ""%></td>
    <td class="text-center"><a class="btn btn-primary" href="#">
      <form action="userDetailServlet" method = "post" >
        <input type = "hidden" name="num" value=<%=userNumbersIter.hasNext()? userNumbersIter.next(): "0"%>>
        <button type=submit>detail information</button>
      </form>
    </a></td>
  </tr>

  <tr>
    <td class="text-center"> <%=headIconListIter.hasNext()? headIconListIter.next(): ""%></td>
    <td class="text-center"> <%=userNameListIter.hasNext()? userNameListIter.next(): ""%></td>
    <td class="text-center"> <%=genderListIter.hasNext()? genderListIter.next(): ""%></td>
    <td class="text-center"> <%=ageListIter.hasNext()? ageListIter.next(): ""%></td>
    <td class="text-center"> <%=workListIter.hasNext()? workListIter.next(): ""%></td>
    <td class="text-center"><a class="btn btn-primary" href="#">
      <form action="userDetailServlet" method = "post" >
        <input type = "hidden" name="num" value=<%=userNumbersIter.hasNext()? userNumbersIter.next(): "0"%>>
        <button type=submit>detail information</button>
      </form>
    </a></td>
  </tr>

  <tr>
    <td class="text-center"> <%=headIconListIter.hasNext()? headIconListIter.next(): ""%></td>
    <td class="text-center"> <%=userNameListIter.hasNext()? userNameListIter.next(): ""%></td>
    <td class="text-center"> <%=genderListIter.hasNext()? genderListIter.next(): ""%></td>
    <td class="text-center"> <%=ageListIter.hasNext()? ageListIter.next(): ""%></td>
    <td class="text-center"> <%=workListIter.hasNext()? workListIter.next(): ""%></td>
    <td class="text-center"><a class="btn btn-primary" href="#">
      <form action="userDetailServlet" method = "post" >
        <input type = "hidden" name="num" value=<%=userNumbersIter.hasNext()? userNumbersIter.next(): "0"%>>
        <button type=submit>detail information</button>
      </form>
    </a></td>
  </tr>

  <tr>
    <td class="text-center"> <%=headIconListIter.hasNext()? headIconListIter.next(): ""%></td>
    <td class="text-center"> <%=userNameListIter.hasNext()? userNameListIter.next(): ""%></td>
    <td class="text-center"> <%=genderListIter.hasNext()? genderListIter.next(): ""%></td>
    <td class="text-center"> <%=ageListIter.hasNext()? ageListIter.next(): ""%></td>
    <td class="text-center"> <%=workListIter.hasNext()? workListIter.next(): ""%></td>
    <td class="text-center"><a class="btn btn-primary" href="#">
      <form action="userDetailServlet" method = "post" >
        <input type = "hidden" name="num" value=<%=userNumbersIter.hasNext()? userNumbersIter.next(): "0"%>>
        <button type=submit>detail information</button>
      </form>
    </a></td>
  </tr>

  <tr>
    <td class="text-center"> <%=headIconListIter.hasNext()? headIconListIter.next(): ""%></td>
    <td class="text-center"> <%=userNameListIter.hasNext()? userNameListIter.next(): ""%></td>
    <td class="text-center"> <%=genderListIter.hasNext()? genderListIter.next(): ""%></td>
    <td class="text-center"> <%=ageListIter.hasNext()? ageListIter.next(): ""%></td>
    <td class="text-center"> <%=workListIter.hasNext()? workListIter.next(): ""%></td>
    <td class="text-center"><a class="btn btn-primary" href="#">
      <form action="userDetailServlet" method = "post" >
        <input type = "hidden" name="num" value=<%=userNumbersIter.hasNext()? userNumbersIter.next(): "0"%>>
        <button type=submit>detail information</button>
      </form>
    </a></td>
  </tr>

  <tr>
    <td class="text-center"> <%=headIconListIter.hasNext()? headIconListIter.next(): ""%></td>
    <td class="text-center"> <%=userNameListIter.hasNext()? userNameListIter.next(): ""%></td>
    <td class="text-center"> <%=genderListIter.hasNext()? genderListIter.next(): ""%></td>
    <td class="text-center"> <%=ageListIter.hasNext()? ageListIter.next(): ""%></td>
    <td class="text-center"> <%=workListIter.hasNext()? workListIter.next(): ""%></td>
    <td class="text-center"><a class="btn btn-primary" href="#">
      <form action="userDetailServlet" method = "post" >
        <input type = "hidden" name="num" value=<%=userNumbersIter.hasNext()? userNumbersIter.next(): "0"%>>
        <button type=submit>detail information</button>
      </form>
    </a></td>
  </tr>




</table>

</body>
</html>