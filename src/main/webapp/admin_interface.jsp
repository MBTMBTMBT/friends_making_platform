<%--
  Created by IntelliJ IDEA.
  User: 13769
  Date: 21/5/16
  Time: 21:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="database.tables.Administrator" %>
<%@ page import="database.daos.AdministractorDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.LinkedList" %>
<%
    /*
    int id = (int) request.getAttribute("id");
    String surname = (String) request.getAttribute("admin_surname");
    String forename = (String) request.getAttribute("admin_forename");
    List<String> idList = (List<String>) request.getAttribute("admin_id_list");
    List<String> surnamesList = (List<String>) request.getAttribute("admin_surnames_list");
    List<String> forenamesList = (List<String>) request.getAttribute("admin_forenames_list");
    List<String> genderList = (List<String>) request.getAttribute("admin_gender_list");
    */
    int id = 1;
    String surname = "Wang";
    String forename = "Yushi";
    List<Integer> idList = new LinkedList<>();
    List<String> surnamesList = new LinkedList<>();
    List<String> forenamesList = new LinkedList<>();
    List<String> genderList = new LinkedList<>();
    String str = "abcdefghijklmnopqrstuvwxyz";
    for (int i = 0; i < str.length(); i++) {
        idList.add(i);
        surnamesList.add(String.valueOf(str.charAt(i)));
        forenamesList.add(String.valueOf(str.charAt(i)));
        genderList.add((i % 2 == 0)? "female" : "male");
    }
%>
<html>
<head>
    <title>Admin</title>
</head>
<body>
<h2>This is admin interface</h2>
Welcome, Admin <%=id%> <%=forename%> <%=surname%>!


<table border="1" width="1000px" align="center"><%-- border为边框，wideth为表格宽度，align居中表格--%>
    <tr> <%-- 行 --%>
        <th> id</th><%-- 表头单元格,th具有标题效果，字体加粗，居中显示 --%>
        <th> forename</th>
        <th> surname</th>
        <th> gender</th>
    </tr>

    <%for (int j = 0; j < idList.size(); j++) {%>
    <tr>
        <td><%=idList.get(j)%></td>
        <td><%=forenamesList.get(j)%></td>
        <td><%=surnamesList.get(j)%></td>
        <td><%=genderList.get(j)%></td>
    </tr>
    <%}%>
    }

</table>

</body>
</html>
