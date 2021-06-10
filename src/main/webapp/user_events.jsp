<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="javax.persistence.criteria.CriteriaBuilder" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Events</title>
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
    String userName = null;
    int userID = -1;

    try {
        userName = (String) request.getAttribute("user_username");
        userID = (int) request.getAttribute("user_id");
    } catch (NullPointerException ignore) {
    }

    if (userName == null || userID == -1) {
        session = request.getSession();
        userName = (String) session.getAttribute("user_username");
        userID = (int) session.getAttribute("user_id");
    }

    request.setAttribute("user_username", userName);
    request.setAttribute("user_id", userID);
    session.setAttribute("user_username", userName);
    session.setAttribute("user_id", userID);

    List<Map<String, String>> selectedEvents = null;
    try {
        selectedEvents = (List<Map<String, String>>) request.getAttribute("selected_events");
        if (selectedEvents == null) throw new NullPointerException();
    } catch (Exception ignore) {
        selectedEvents = (List<Map<String, String>>) session.getAttribute("selected_events");
    }

    List<String> joinedEventKeyList = new LinkedList<>();
    for (int i = 0; i < 2; i++) {
        try {
            String key = selectedEvents.get(i).get("locationID") + "%" + selectedEvents.get(i).get("time").split(" ")[0]
                    + "%" + selectedEvents.get(i).get("time").split(" ")[1];
            joinedEventKeyList.add(key);
        } catch (IndexOutOfBoundsException e) {
        }
    }

    Iterator<String> joinedEventKeyListIter = joinedEventKeyList.iterator();

    List<List<String>> infoLists = null;
    try {
        infoLists = (List<List<String>>) request.getAttribute("info_lists");
        if (infoLists == null) throw new NullPointerException();
    } catch (Exception ignore) {
        infoLists = (List<List<String>>) session.getAttribute("info_lists");
    }

    Iterator<List<String>> listIterator = infoLists.iterator();
    List<String> locationIDList, eventTimeList, eventTypeList, participantsList, locationTypeList, geographicalLocationList;
    eventTimeList = listIterator.next();
    eventTypeList = listIterator.next();
    participantsList = listIterator.next();
    locationIDList = listIterator.next();
    locationTypeList = listIterator.next();
    geographicalLocationList = listIterator.next();

    Iterator<String> eventTimeListIter, eventTypeListIter, participantsListIter, locationIDListIter, locationTypeListIter, geographicalLocationListIter;
    eventTimeListIter = eventTimeList.iterator();
    eventTypeListIter = eventTypeList.iterator();
    participantsListIter = participantsList.iterator();
    locationIDListIter = locationIDList.iterator();
    locationTypeListIter = locationTypeList.iterator();
    geographicalLocationListIter = geographicalLocationList.iterator();

    List<String> eventKeyList = new LinkedList<>();
    for (int i = 0; i < locationIDList.size(); i++) {
        try {
            String key = locationIDList.get(i) + "%" + eventTimeList.get(i).split(" ")[0]
                    + "%" + eventTimeList.get(i).split(" ")[1];
            eventKeyList.add(key);
            System.out.println(key);
        } catch (IndexOutOfBoundsException index) {
        }
    }

    Iterator<String> eventKeyListIter = eventKeyList.iterator();
%>

<body>
<h1 style="text-align: center;color: rgb(85, 74, 90);text-shadow: rgb(156, 153, 153) 2.5px 1.5px 1.5px;">Events

</h1>
<div align="right">
    <a class="btn btn-warning" href="userMainPageServlet">return</a>
</div>

<table class="table table-hover table-bordered " align="center">
    <tr>
        <th class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> Location type</th>
        <th class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> Geographical location</th>
        <th class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> Activity time</th>
        <th class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> Activity type</th>
        <th class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> Number of participants</th>
        <th class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> Operation</th>
    </tr>

    <tr style="background-color: #4cae4c">
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> <%=selectedEvents.get(0).get("locationType")%></td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> <%=selectedEvents.get(0).get("geo")%></td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> <%=selectedEvents.get(0).get("time")%></td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> <%=selectedEvents.get(0).get("activityType")%></td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> <%=selectedEvents.get(0).get("partNum")%></td>
        <td class="text-center" style="display:table-cell; vertical-align:middle;">
            <form action="<%=joinedEventKeyListIter.hasNext()?"deleteJoinEventServlet":"#"%>" method="post">
                <input type="hidden" name="num" value=<%=joinedEventKeyListIter.hasNext()? joinedEventKeyListIter.next(): ""%>>
                <button type=submit class="btn btn-default">quit</button>
            </form>
        </td>
    </tr>
    <tr style="background-color: #4cae4c">
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> <%=selectedEvents.get(1).get("locationType")%></td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> <%=selectedEvents.get(1).get("geo")%></td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> <%=selectedEvents.get(1).get("time")%></td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> <%=selectedEvents.get(1).get("activityType")%></td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> <%=selectedEvents.get(1).get("partNum")%></td>
        <td class="text-center" style="display:table-cell; vertical-align:middle;">
            <form action="<%=joinedEventKeyListIter.hasNext()?"deleteJoinEventServlet":"#"%>" method="post">
                <input type="hidden" name="num" value=<%=joinedEventKeyListIter.hasNext()? joinedEventKeyListIter.next(): ""%>>
                <button type=submit class="btn btn-default">quit</button>
            </form>
        </td>
    </tr>

    <tr>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> <%=locationTypeListIter.hasNext()? locationTypeListIter.next(): ""%></td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> <%=geographicalLocationListIter.hasNext()? geographicalLocationListIter.next(): ""%></td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> <%=eventTimeListIter.hasNext()? eventTimeListIter.next(): ""%></td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> <%=eventTypeListIter.hasNext()? eventTypeListIter.next(): ""%></td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> <%=participantsListIter.hasNext()? participantsListIter.next(): ""%></td>
        <td class="text-center" style="display:table-cell; vertical-align:middle;">
            <form action="<%=eventKeyListIter.hasNext()?"addJoinEventServlet":"#"%>" method="post">
                <input type="hidden" name="num" value=<%=eventKeyListIter.hasNext()? eventKeyListIter.next(): ""%>>
                <button type=submit class="btn btn-default">join</button>
            </form>
        </td>
    </tr>
    <tr>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> <%=locationTypeListIter.hasNext()? locationTypeListIter.next(): ""%></td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> <%=geographicalLocationListIter.hasNext()? geographicalLocationListIter.next(): ""%></td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> <%=eventTimeListIter.hasNext()? eventTimeListIter.next(): ""%></td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> <%=eventTypeListIter.hasNext()? eventTypeListIter.next(): ""%></td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> <%=participantsListIter.hasNext()? participantsListIter.next(): ""%></td>
        <td class="text-center" style="display:table-cell; vertical-align:middle;">
            <form action="<%=eventKeyListIter.hasNext()?"addJoinEventServlet":"#"%>" method="post">
                <input type="hidden" name="num" value=<%=eventKeyListIter.hasNext()? eventKeyListIter.next(): ""%>>
                <button type=submit class="btn btn-default">join</button>
            </form>
        </td>
    </tr>
    <tr>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> <%=locationTypeListIter.hasNext()? locationTypeListIter.next(): ""%></td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> <%=geographicalLocationListIter.hasNext()? geographicalLocationListIter.next(): ""%></td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> <%=eventTimeListIter.hasNext()? eventTimeListIter.next(): ""%></td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> <%=eventTypeListIter.hasNext()? eventTypeListIter.next(): ""%></td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> <%=participantsListIter.hasNext()? participantsListIter.next(): ""%></td>
        <td class="text-center" style="display:table-cell; vertical-align:middle;">
            <form action="<%=eventKeyListIter.hasNext()?"addJoinEventServlet":"#"%>" method="post">
                <input type="hidden" name="num" value=<%=eventKeyListIter.hasNext()? eventKeyListIter.next(): ""%>>
                <button type=submit class="btn btn-default">join</button>
            </form>
        </td>
    </tr>
    <tr>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> <%=locationTypeListIter.hasNext()? locationTypeListIter.next(): ""%></td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> <%=geographicalLocationListIter.hasNext()? geographicalLocationListIter.next(): ""%></td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> <%=eventTimeListIter.hasNext()? eventTimeListIter.next(): ""%></td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> <%=eventTypeListIter.hasNext()? eventTypeListIter.next(): ""%></td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> <%=participantsListIter.hasNext()? participantsListIter.next(): ""%></td>
        <td class="text-center" style="display:table-cell; vertical-align:middle;">
            <form action="<%=eventKeyListIter.hasNext()?"addJoinEventServlet":"#"%>" method="post">
                <input type="hidden" name="num" value=<%=eventKeyListIter.hasNext()? eventKeyListIter.next(): ""%>>
                <button type=submit class="btn btn-default">join</button>
            </form>
        </td>
    </tr>
    <tr>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> <%=locationTypeListIter.hasNext()? locationTypeListIter.next(): ""%></td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> <%=geographicalLocationListIter.hasNext()? geographicalLocationListIter.next(): ""%></td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> <%=eventTimeListIter.hasNext()? eventTimeListIter.next(): ""%></td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> <%=eventTypeListIter.hasNext()? eventTypeListIter.next(): ""%></td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> <%=participantsListIter.hasNext()? participantsListIter.next(): ""%></td>
        <td class="text-center" style="display:table-cell; vertical-align:middle;">
            <form action="<%=eventKeyListIter.hasNext()?"addJoinEventServlet":"#"%>" method="post">
                <input type="hidden" name="num" value=<%=eventKeyListIter.hasNext()? eventKeyListIter.next(): ""%>>
                <button type=submit class="btn btn-default">join</button>
            </form>
        </td>
    </tr>
    <tr>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> <%=locationTypeListIter.hasNext()? locationTypeListIter.next(): ""%></td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> <%=geographicalLocationListIter.hasNext()? geographicalLocationListIter.next(): ""%></td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> <%=eventTimeListIter.hasNext()? eventTimeListIter.next(): ""%></td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> <%=eventTypeListIter.hasNext()? eventTypeListIter.next(): ""%></td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> <%=participantsListIter.hasNext()? participantsListIter.next(): ""%></td>
        <td class="text-center" style="display:table-cell; vertical-align:middle;">
            <form action="<%=eventKeyListIter.hasNext()?"addJoinEventServlet":"#"%>" method="post">
                <input type="hidden" name="num" value=<%=eventKeyListIter.hasNext()? eventKeyListIter.next(): ""%>>
                <button type=submit class="btn btn-default">join</button>
            </form>
        </td>
    </tr>
    <tr>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> <%=locationTypeListIter.hasNext()? locationTypeListIter.next(): ""%></td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> <%=geographicalLocationListIter.hasNext()? geographicalLocationListIter.next(): ""%></td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> <%=eventTimeListIter.hasNext()? eventTimeListIter.next(): ""%></td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> <%=eventTypeListIter.hasNext()? eventTypeListIter.next(): ""%></td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> <%=participantsListIter.hasNext()? participantsListIter.next(): ""%></td>
        <td class="text-center" style="display:table-cell; vertical-align:middle;">
            <form action="<%=eventKeyListIter.hasNext()?"addJoinEventServlet":"#"%>" method="post">
                <input type="hidden" name="num" value=<%=eventKeyListIter.hasNext()? eventKeyListIter.next(): ""%>>
                <button type=submit class="btn btn-default">join</button>
            </form>
        </td>
    </tr>
    <tr>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> <%=locationTypeListIter.hasNext()? locationTypeListIter.next(): ""%></td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> <%=geographicalLocationListIter.hasNext()? geographicalLocationListIter.next(): ""%></td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> <%=eventTimeListIter.hasNext()? eventTimeListIter.next(): ""%></td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> <%=eventTypeListIter.hasNext()? eventTypeListIter.next(): ""%></td>
        <td class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> <%=participantsListIter.hasNext()? participantsListIter.next(): ""%></td>
        <td class="text-center" style="display:table-cell; vertical-align:middle;">
            <form action="<%=eventKeyListIter.hasNext()?"addJoinEventServlet":"#"%>" method="post">
                <input type="hidden" name="num" value=<%=eventKeyListIter.hasNext()? eventKeyListIter.next(): ""%>>
                <button type=submit class="btn btn-default">join</button>
            </form>
        </td>
    </tr>

</table>

</body>
</html>