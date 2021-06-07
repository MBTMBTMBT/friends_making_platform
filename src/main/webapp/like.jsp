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
  .table
  {
    width:1200px;

  }
</style>
<body>

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

<h2 style="text-align: center;color: rgb(85, 74, 90);text-shadow: rgb(156, 153, 153) 2.5px 1.5px 1.5px;">People you are interested in</h2>

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
    <td align="center" ><img src="<%=headIconListIter.hasNext()? headIconListIter.next(): ""%>" width="100" height="100"></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=userNameListIter.hasNext()? userNameListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=genderListIter.hasNext()? genderListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=ageListIter.hasNext()? ageListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=workListIter.hasNext()? workListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;"><a class="btn btn-primary" href="#">
      <form action="userDetailServlet" method = "post">
        <input type = "hidden" name="num" value=<%=userNumbersIter.hasNext()? userNumbersIter.next(): "0"%>>
        <button type=submit class="btn btn-default">detail information</button>
      </form>
    </a></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;"><a class="btn btn-primary" href="#">
      <form action="userDetailServlet" method = "post">
        <input type = "hidden" name="num" value=<%=userNumbersIter.hasNext()? userNumbersIter.next(): "0"%>>
        <button type=submit class="btn btn-default">Upgrade relations</button>
      </form>
    </a></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;"><a class="btn btn-primary" href="#">
      <form action="userDetailServlet" method = "post">
        <input type = "hidden" name="num" value=<%=userNumbersIter.hasNext()? userNumbersIter.next(): "0"%>>
        <button type=submit class="btn btn-default">Degrade relations</button>
      </form>
    </a></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;"><a class="btn btn-primary" href="#">
      <form action="userDetailServlet" method = "post">
        <input type = "hidden" name="num" value=<%=userNumbersIter.hasNext()? userNumbersIter.next(): "0"%>>
        <button type=submit class="btn btn-default">Cancel relations</button>
      </form>
    </a></td>
  </tr>
  <tr>
    <td align="center" ><img src="<%=headIconListIter.hasNext()? headIconListIter.next(): ""%>" width="100" height="100"></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=userNameListIter.hasNext()? userNameListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=genderListIter.hasNext()? genderListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=ageListIter.hasNext()? ageListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=workListIter.hasNext()? workListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;"><a class="btn btn-primary" href="#">
      <form action="userDetailServlet" method = "post">
        <input type = "hidden" name="num" value=<%=userNumbersIter.hasNext()? userNumbersIter.next(): "0"%>>
        <button type=submit class="btn btn-default">detail information</button>
      </form>
    </a></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;"><a class="btn btn-primary" href="#">
      <form action="userDetailServlet" method = "post">
        <input type = "hidden" name="num" value=<%=userNumbersIter.hasNext()? userNumbersIter.next(): "0"%>>
        <button type=submit class="btn btn-default">Upgrade relations</button>
      </form>
    </a></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;"><a class="btn btn-primary" href="#">
      <form action="userDetailServlet" method = "post">
        <input type = "hidden" name="num" value=<%=userNumbersIter.hasNext()? userNumbersIter.next(): "0"%>>
        <button type=submit class="btn btn-default">Degrade relations</button>
      </form>
    </a></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;"><a class="btn btn-primary" href="#">
      <form action="userDetailServlet" method = "post">
        <input type = "hidden" name="num" value=<%=userNumbersIter.hasNext()? userNumbersIter.next(): "0"%>>
        <button type=submit class="btn btn-default">Cancel relations</button>
      </form>
    </a></td>
  </tr>
  <tr>
    <td align="center" ><img src="<%=headIconListIter.hasNext()? headIconListIter.next(): ""%>" width="100" height="100"></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=userNameListIter.hasNext()? userNameListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=genderListIter.hasNext()? genderListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=ageListIter.hasNext()? ageListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=workListIter.hasNext()? workListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;"><a class="btn btn-primary" href="#">
      <form action="userDetailServlet" method = "post">
        <input type = "hidden" name="num" value=<%=userNumbersIter.hasNext()? userNumbersIter.next(): "0"%>>
        <button type=submit class="btn btn-default">detail information</button>
      </form>
    </a></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;"><a class="btn btn-primary" href="#">
      <form action="userDetailServlet" method = "post">
        <input type = "hidden" name="num" value=<%=userNumbersIter.hasNext()? userNumbersIter.next(): "0"%>>
        <button type=submit class="btn btn-default">Upgrade relations</button>
      </form>
    </a></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;"><a class="btn btn-primary" href="#">
      <form action="userDetailServlet" method = "post">
        <input type = "hidden" name="num" value=<%=userNumbersIter.hasNext()? userNumbersIter.next(): "0"%>>
        <button type=submit class="btn btn-default">Degrade relations</button>
      </form>
    </a></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;"><a class="btn btn-primary" href="#">
      <form action="userDetailServlet" method = "post">
        <input type = "hidden" name="num" value=<%=userNumbersIter.hasNext()? userNumbersIter.next(): "0"%>>
        <button type=submit class="btn btn-default">Cancel relations</button>
      </form>
    </a></td>
  </tr>
  <tr>
    <td align="center" ><img src="<%=headIconListIter.hasNext()? headIconListIter.next(): ""%>" width="100" height="100"></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=userNameListIter.hasNext()? userNameListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=genderListIter.hasNext()? genderListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=ageListIter.hasNext()? ageListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=workListIter.hasNext()? workListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;"><a class="btn btn-primary" href="#">
      <form action="userDetailServlet" method = "post">
        <input type = "hidden" name="num" value=<%=userNumbersIter.hasNext()? userNumbersIter.next(): "0"%>>
        <button type=submit class="btn btn-default">detail information</button>
      </form>
    </a></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;"><a class="btn btn-primary" href="#">
      <form action="userDetailServlet" method = "post">
        <input type = "hidden" name="num" value=<%=userNumbersIter.hasNext()? userNumbersIter.next(): "0"%>>
        <button type=submit class="btn btn-default">Upgrade relations</button>
      </form>
    </a></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;"><a class="btn btn-primary" href="#">
      <form action="userDetailServlet" method = "post">
        <input type = "hidden" name="num" value=<%=userNumbersIter.hasNext()? userNumbersIter.next(): "0"%>>
        <button type=submit class="btn btn-default">Degrade relations</button>
      </form>
    </a></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;"><a class="btn btn-primary" href="#">
      <form action="userDetailServlet" method = "post">
        <input type = "hidden" name="num" value=<%=userNumbersIter.hasNext()? userNumbersIter.next(): "0"%>>
        <button type=submit class="btn btn-default">Cancel relations</button>
      </form>
    </a></td>
  </tr>
  <tr>
    <td align="center" ><img src="<%=headIconListIter.hasNext()? headIconListIter.next(): ""%>" width="100" height="100"></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=userNameListIter.hasNext()? userNameListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=genderListIter.hasNext()? genderListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=ageListIter.hasNext()? ageListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> <%=workListIter.hasNext()? workListIter.next(): ""%></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;"><a class="btn btn-primary" href="#">
      <form action="userDetailServlet" method = "post">
        <input type = "hidden" name="num" value=<%=userNumbersIter.hasNext()? userNumbersIter.next(): "0"%>>
        <button type=submit class="btn btn-default">detail information</button>
      </form>
    </a></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;"><a class="btn btn-primary" href="#">
      <form action="userDetailServlet" method = "post">
        <input type = "hidden" name="num" value=<%=userNumbersIter.hasNext()? userNumbersIter.next(): "0"%>>
        <button type=submit class="btn btn-default">Upgrade relations</button>
      </form>
    </a></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;"><a class="btn btn-primary" href="#">
      <form action="userDetailServlet" method = "post">
        <input type = "hidden" name="num" value=<%=userNumbersIter.hasNext()? userNumbersIter.next(): "0"%>>
        <button type=submit class="btn btn-default">Degrade relations</button>
      </form>
    </a></td>
    <td class="text-center" style="display:table-cell; vertical-align:middle;"><a class="btn btn-primary" href="#">
      <form action="userDetailServlet" method = "post">
        <input type = "hidden" name="num" value=<%=userNumbersIter.hasNext()? userNumbersIter.next(): "0"%>>
        <button type=submit class="btn btn-default">Cancel relations</button>
      </form>
    </a></td>
  </tr>
</table>

<div align="center">                        <!--next page-->
  <nav aria-label="Page navigation">
    <ul class="pagination pagination-lg">  <!--large -->
      <li>
        <a href="#" aria-label="Previous">
          <span aria-hidden="true">Previous</span>
        </a>
      </li>

      <li>
        <a href="#" aria-label="Next">
          <span aria-hidden="true">Next</span>
        </a>
      </li>
    </ul>
  </nav>
</div>

</body>
</html>