<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.LinkedList" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>location_event</title>
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

    Map<String, String> locationMap = null;
    try {
        locationMap = (Map<String, String>) request.getAttribute("location_map");
        if (infoLists == null) throw new NullPointerException();
    } catch (Exception ignore) {
        locationMap = (Map<String, String>) session.getAttribute("location_map");
    }

    request.setAttribute("location_map", locationMap);
    session.setAttribute("location_map", locationMap);
    Iterator<List<String>> listIterator = infoLists.iterator();
    // listIterator.remove();

    List<String> locationIDList, eventTimeList, eventTypeList, participantsList;
    eventTimeList = listIterator.next();
    eventTypeList = listIterator.next();
    participantsList = listIterator.next();
    locationIDList = listIterator.next();

    Iterator<String> eventTimeListIter, eventTypeListIter, participantsListIter, locationIDListIter;
    eventTimeListIter = eventTimeList.iterator();
    eventTypeListIter = eventTypeList.iterator();
    participantsListIter = participantsList.iterator();
    locationIDListIter = locationIDList.iterator();

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
<h1 style="text-align: center;color: rgb(85, 74, 90);text-shadow: rgb(156, 153, 153) 2.5px 1.5px 1.5px;">Locations and
    Events</h1>
<div align="center">
    <input type="button" class="btn btn-info" onclick="window.location.href='mentorMainPageServlet';"
           value="return">
</div>

<table class="table table-hover table-bordered" align="center">
    <tr>
        <th class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> Location type</th>
        <th class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> Geographical location</th>
        <th class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> Operation</th>
    </tr>
    <tr>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"><%=locationMap.get("type")%>
        </td>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"><%=locationMap.get("geographical_location")%>
        </td>
        <td class="text-center">
            <input type="button" class="btn btn-danger" onclick="window.location.href='deleteEventLocationServlet';"
                   value="delete">
        </td>

    </tr>

    <!--<div align="center">
        <span  style="font-size: 16px;color:red" >
            You can organise maximum one location
        </span>
    </div>-->
    <br>

    <form action="addEventLocationServlet" method="post" id="addEventLocation">
        <tr>
            <td>
                <div class="form-group">
                    <label>Location type</label>
                    <select name="location_type" class="form-control " style=" width: 200px;">
                        <option value="coffee/tea house">coffee/tea house</option>
                        <option value="bar">bar</option>
                        <option value="restaurant">restaurant</option>
                        <option value="shopping centre">shopping centre</option>
                        <option value="park">park</option>
                        <option value="cinema">cinema</option>
                        <option value="theatre">theatre</option>
                    </select>
                </div>

            </td>
            <td>
                <div class="form-group">
                    <label>Geographical location</label>
                    <textarea name="geographical_location" class="form-control" rows="2"></textarea>
                </div>
            </td>
            <td class="text-center" style="vertical-align:middle">
                <input type="submit" class="btn btn-warning" placeholder="Confirm"
                       style="vertical-align:center; horiz-align:center"/>
            </td>
        </tr>
    </form>
</table>

<br>
<br>
<table class="table table-hover table-bordered" align="center">
    <tr>
        <th class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);">Activity time</th>
        <th class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);">Activity type</th>
        <th class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);">participants</th>
        <th class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);">Operation</th>
    </tr>

    <!-- <div align="center">
        <span style="font-size: 16px;color:red">
            You can organise maximum five events
        </span>
    </div> -->

    <tr>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);">
            <%=eventTimeListIter.hasNext() ? eventTimeListIter.next() : ""%>
        </td>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);">
            <%=eventTypeListIter.hasNext() ? eventTypeListIter.next() : ""%>
        </td>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);">
            <%=participantsListIter.hasNext() ? participantsListIter.next() : ""%>
        </td>
        <td align="center">
            <form action="<%=eventKeyListIter.hasNext()?"deleteEventServlet":"userEventPushServlet"%>" method="post">
                <input type="hidden" name="num" value=<%=eventKeyListIter.hasNext()? eventKeyListIter.next(): ""%>>
                <button type=submit class="btn btn-danger">remove</button>
            </form>
            <!--<a class="btn btn-primary" href="userEventPushServlet">
                <form action="</%=eventKeyListIter.hasNext()?"deleteEventServlet":"userEventPushServlet"%>" method="post">
                    <input type="hidden" name="num" value=</%=eventKeyListIter.hasNext()? eventKeyListIter.next(): ""%>>
                    <button type=submit class="btn btn-danger">remove</button>
                </form>
            </a>-->
        </td>
    </tr>
    <tr>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);">
            <%=eventTimeListIter.hasNext() ? eventTimeListIter.next() : ""%>
        </td>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);">
            <%=eventTypeListIter.hasNext() ? eventTypeListIter.next() : ""%>
        </td>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);">
            <%=participantsListIter.hasNext() ? participantsListIter.next() : ""%>
        </td>
        <td align="center">
            <form action="<%=eventKeyListIter.hasNext()?"deleteEventServlet":"userEventPushServlet"%>" method="post">
                <input type="hidden" name="num" value=<%=eventKeyListIter.hasNext()? eventKeyListIter.next(): ""%>>
                <button type=submit class="btn btn-danger">remove</button>
            </form>
            <!--<a class="btn btn-primary" href="userEventPushServlet">
                <form action="</%=eventKeyListIter.hasNext()?"deleteEventServlet":"userEventPushServlet"%>" method="post">
                    <input type="hidden" name="num" value=</%=eventKeyListIter.hasNext()? eventKeyListIter.next(): ""%>>
                    <button type=submit class="btn btn-danger">remove</button>
                </form>
            </a>-->
        </td>
    </tr>
    <tr>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);">
            <%=eventTimeListIter.hasNext() ? eventTimeListIter.next() : ""%>
        </td>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);">
            <%=eventTypeListIter.hasNext() ? eventTypeListIter.next() : ""%>
        </td>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);">
            <%=participantsListIter.hasNext() ? participantsListIter.next() : ""%>
        </td>
        <td align="center">
            <form action="<%=eventKeyListIter.hasNext()?"deleteEventServlet":"userEventPushServlet"%>" method="post">
                <input type="hidden" name="num" value=<%=eventKeyListIter.hasNext()? eventKeyListIter.next(): ""%>>
                <button type=submit class="btn btn-danger">remove</button>
            </form>
            <!--<a class="btn btn-primary" href="userEventPushServlet">
                <form action="</%=eventKeyListIter.hasNext()?"deleteEventServlet":"userEventPushServlet"%>" method="post">
                    <input type="hidden" name="num" value=</%=eventKeyListIter.hasNext()? eventKeyListIter.next(): ""%>>
                    <button type=submit class="btn btn-danger">remove</button>
                </form>
            </a>-->
        </td>
    </tr>
    <tr>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);">
            <%=eventTimeListIter.hasNext() ? eventTimeListIter.next() : ""%>
        </td>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);">
            <%=eventTypeListIter.hasNext() ? eventTypeListIter.next() : ""%>
        </td>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);">
            <%=participantsListIter.hasNext() ? participantsListIter.next() : ""%>
        </td>
        <td align="center">
            <form action="<%=eventKeyListIter.hasNext()?"deleteEventServlet":"userEventPushServlet"%>" method="post">
                <input type="hidden" name="num" value=<%=eventKeyListIter.hasNext()? eventKeyListIter.next(): ""%>>
                <button type=submit class="btn btn-danger">remove</button>
            </form>
            <!--<a class="btn btn-primary" href="userEventPushServlet">
                <form action="</%=eventKeyListIter.hasNext()?"deleteEventServlet":"userEventPushServlet"%>" method="post">
                    <input type="hidden" name="num" value=</%=eventKeyListIter.hasNext()? eventKeyListIter.next(): ""%>>
                    <button type=submit class="btn btn-danger">remove</button>
                </form>
            </a>-->
        </td>
    </tr>
    <tr>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);">
            <%=eventTimeListIter.hasNext() ? eventTimeListIter.next() : ""%>
        </td>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);">
            <%=eventTypeListIter.hasNext() ? eventTypeListIter.next() : ""%>
        </td>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);">
            <%=participantsListIter.hasNext() ? participantsListIter.next() : ""%>
        </td>
        <td align="center">
            <form action="<%=eventKeyListIter.hasNext()?"deleteEventServlet":"userEventPushServlet"%>" method="post">
                <input type="hidden" name="num" value=<%=eventKeyListIter.hasNext()? eventKeyListIter.next(): ""%>>
                <button type=submit class="btn btn-danger">remove</button>
            </form>
            <!--<a class="btn btn-primary" href="userEventPushServlet">
                <form action="</%=eventKeyListIter.hasNext()?"deleteEventServlet":"userEventPushServlet"%>" method="post">
                    <input type="hidden" name="num" value=</%=eventKeyListIter.hasNext()? eventKeyListIter.next(): ""%>>
                    <button type=submit class="btn btn-danger">remove</button>
                </form>
            </a>-->
        </td>
    </tr>

    <tr>
        <form action="addEventServlet" method="post" id="addEvent">
            <td class="text-center"
                style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);">
                <input type="datetime-local" name="activity_time" id="activity_time">
            </td>
            <td class="text-center"
                style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);">
                <label class="radio-inline">
                    <input type="radio" name="activity_type" id="blind date" value="blind date"> blind date
                </label>
                <label class="radio-inline">
                    <input type="radio" name="activity_type" id="picnic" value="picnic"> picnic
                </label>
                <label class="radio-inline">
                    <input type="radio" name="activity_type" id="home party" value="home party"> home party
                </label>
                <label class="radio-inline">
                    <input type="radio" name="activity_type" id="barbeque" value="barbeque"> barbeque
                </label><br>
                <label class="radio-inline">
                    <input type="radio" name="activity_type" id="card game" value="card game"> card game
                </label>
                <label class="radio-inline">
                    <input type="radio" name="activity_type" id="KTV" value="KTV"> KTV
                </label>
                <label class="radio-inline">
                    <input type="radio" name="activity_type" id="hiking" value="hiking"> hiking
                </label>
                <label class="radio-inline">
                    <input type="radio" name="activity_type" id="others" value="others"> others
                </label>
            </td>
            <td class="text-center" style="display:table-cell; vertical-align:middle;">
                <input type="submit" class="btn btn-primary"/>
            </td>
        </form>
    </tr>


</table>

</body>
</html>