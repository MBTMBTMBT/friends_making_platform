<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
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
  System.out.println("I set this fucking session!!!!!!!!!");
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

<head>
  <meta charset="UTF-8">
  <title>My_mentor</title>
  <link rel="stylesheet" type="text/css" href="main/webapp/static/css/userpage_css/bootstrap.min.css">
  <link rel="stylesheet" href="main/webapp/static/css/userpage_css/style.css">
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
    <a class="btn btn-primary" href="user.html">Return</a>

    <button class="btn btn-warning" href="choose_my_mentor.html" type="submit" style="color: rgb(73, 41, 85);">Choose my
      mentor</button>
  </div>
  <br>
  <table class="table table-hover table-bordered" align="center">
    <tr>
      <th class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> head icon</th>
      <th class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> screenname</th>
      <th class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> gender</th>
      <th class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> age</th>
      <th class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> operation</th>

    </tr>
    <tr>
      <td align="center"><img src="image.jpg" width="100" height="100"></td>
      <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);">
        2</td>
      <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);">
        2</td>
      <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);">
        2</td>
      <td class="text-center" style="display:table-cell; vertical-align:middle;"><button class="btn btn-danger">Cancle
          choose</button></td>
    </tr>
  </table>

  <br><br>
  <h2 style="text-align: center;color: rgb(85, 74, 90);text-shadow: rgb(156, 153, 153) 2.5px 1.5px 1.5px;">Select your mentor
  </h2>

  <br> <span style="font-size: 16px;color:red"><%=request.getAttribute("msg")%></span><br>

  <br>
  <table class="table table-hover table-bordered" align="center">
    <tr>
      <th class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> head icon</th>
      <th class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> screenname</th>
      <th class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> gender</th>
      <th class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> operation</th>

    </tr>
    <tr>
      <td align="center"><img src="image.jpg" width="100" height="100"></td>
      <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);">
        2</td>
      <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);">
        2</td>
      <td class="text-center" style="display:table-cell; vertical-align:middle;"><button
          class="btn btn-danger">Choose</button></td>
    </tr>
    <tr>
      <td align="center"><img src="image.jpg" width="100" height="100"></td>
      <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);">
        2</td>
      <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);">
        2</td>
      <td class="text-center" style="display:table-cell; vertical-align:middle;"><button
          class="btn btn-danger">Choose</button></td>
    </tr>
    <tr>
      <td align="center"><img src="image.jpg" width="100" height="100"></td>
      <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);">
        2</td>
      <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);">
        2</td>
      <td class="text-center" style="display:table-cell; vertical-align:middle;"><button
          class="btn btn-danger">Choose</button></td>
    </tr>
    <tr>
      <td align="center"><img src="image.jpg" width="100" height="100"></td>
      <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);">
        2</td>
      <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);">
        2</td>
      <td class="text-center" style="display:table-cell; vertical-align:middle;"><button
          class="btn btn-danger">Choose</button></td>
    </tr>
    <tr>
      <td align="center"><img src="image.jpg" width="100" height="100"></td>
      <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);">
        2</td>
      <td class="text-center" style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);">
        2</td>
      <td class="text-center" style="display:table-cell; vertical-align:middle;"><button
          class="btn btn-danger">Choose</button></td>
    </tr>
  </table>
</body>

</html>