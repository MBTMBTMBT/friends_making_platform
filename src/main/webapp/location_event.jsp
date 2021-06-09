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
        eventKeyList.add(locationIDList.get(i) + " " + eventTimeList.get(i));
    }

    Iterator<String> eventKeyListIter = eventKeyList.iterator();
%>

<body>
<h1 style="text-align: center;color: rgb(85, 74, 90);text-shadow: rgb(156, 153, 153) 2.5px 1.5px 1.5px;">Locations and
    Events</h1>
<div align="center">
    <input type="button" onclick="window.location.href='mentorMainPageServlet';"
           value="return" style="font-size:large; font-weight: bolder; font-family: Arial, Helvetica, sans-serif;color:rgba(31, 53, 150, 0.945);background-image: linear-gradient(125deg,white,#bfd87b);border-radius: 6px;border: thistle;">
</div>
<table class="table table-hover table-bordered" align="center">
    <tr>
        <th class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> Location type</th>
        <th class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> Geographical location</th>
        <th class="text-center" style="font-size: 17px;color: rgb(85, 74, 90);"> Operation</th>
    </tr>
    <tr>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> 2
        </td>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);"> 2
        </td>
        <td class="text-center">
            <button type="submit" class="btn btn-default">Delete</button>
        </td>
    </tr>


    <tr align="center">
        <span style="font-size: 16px;color:red">
            You can organise maximum one location
        </span>
    </tr>

    <form action="addEventLocationServlet" method="post" id="addEventLocation">
        <tr>
            <td>
                <form class="form-inline" style="text-align: center">
                    <div class="form-group">
                        <label>Location type</label>
                        <select class="form-control " style=" width: 200px;">
                            <option>coffee/tea house</option>
                            <option>bar</option>
                            <option>restaurant</option>
                            <option>shopping centre</option>
                            <option>park</option>
                            <option>cinema</option>
                            <option>theatre</option>
                        </select>
                    </div>
                </form>

            </td>
            <td>
                <form class="form-inline" style="text-align: center">
                    <div class="form-group">
                        <label>Geographical location</label>
                        <label>
                            <textarea class="form-control" rows="2"></textarea>
                        </label>
                    </div>
                </form>

            </td>
            <td class="text-center">
                <input type="submit" class="btn btn-default">Confirm</input>
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

    <tr align="center">
        <span style="font-size: 16px;color:red">
            You can organise maximum five events
        </span>
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
            <a class="btn btn-primary" href="#">
                <form action="<%=eventKeyListIter.hasNext()?"deleteEvent":"#"%>" method="post">
                    <input type="hidden" name="num" value=<%=eventKeyListIter.hasNext()? eventKeyListIter.next(): ""%>>
                    <button type=submit class="btn btn-default">remove</button>
                </form>
            </a>
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
            <a class="btn btn-primary" href="#">
                <form action="<%=eventKeyListIter.hasNext()?"deleteEvent":"#"%>" method="post">
                    <input type="hidden" name="num" value=<%=eventKeyListIter.hasNext()? eventKeyListIter.next(): ""%>>
                    <button type=submit class="btn btn-default">remove</button>
                </form>
            </a>
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
            <a class="btn btn-primary" href="#">
                <form action="<%=eventKeyListIter.hasNext()?"deleteEvent":"#"%>" method="post">
                    <input type="hidden" name="num" value=<%=eventKeyListIter.hasNext()? eventKeyListIter.next(): ""%>>
                    <button type=submit class="btn btn-default">remove</button>
                </form>
            </a>
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
            <a class="btn btn-primary" href="#">
                <form action="<%=eventKeyListIter.hasNext()?"deleteEvent":"#"%>" method="post">
                    <input type="hidden" name="num" value=<%=eventKeyListIter.hasNext()? eventKeyListIter.next(): ""%>>
                    <button type=submit class="btn btn-default">remove</button>
                </form>
            </a>
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
            <a class="btn btn-primary" href="#">
                <form action="<%=eventKeyListIter.hasNext()?"deleteEvent":"#"%>" method="post">
                    <input type="hidden" name="num" value=<%=eventKeyListIter.hasNext()? eventKeyListIter.next(): ""%>>
                    <button type=submit class="btn btn-default">remove</button>
                </form>
            </a>
        </td>
    </tr>

    <tr>
        <form action="addEventServlet" method="post" id="addEvent">
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);">
            <input type="datetime-local" name="Activity_time1" id="Activity_time1">
        </td>
        <td class="text-center"
            style="display:table-cell; vertical-align:middle;font-size: 17px;color: rgb(85, 74, 90);">
            <label class="checkbox-inline">
                <input type="checkbox" id="blind date" value="blind date"> blind date
            </label>
            <label class="checkbox-inline">
                <input type="checkbox" id="picnic" value="picnic"> picnic
            </label>
            <label class="checkbox-inline">
                <input type="checkbox" id="home party" value="home party"> home party
            </label>
            <label class="checkbox-inline">
                <input type="checkbox" id="barbeque" value="barbeque"> barbeque
            </label><br>
            <label class="checkbox-inline">
                <input type="checkbox" id="role playing detective" value="role playing detective"> role playing
                detective
            </label>
            <label class="checkbox-inline">
                <input type="checkbox" id="KTV" value="KTV"> KTV
            </label>
            <label class="checkbox-inline">
                <input type="checkbox" id="hiking" value="hiking"> hiking
            </label>
            <label class="checkbox-inline">
                <input type="checkbox" id="others" value="others"> others
            </label>
        </td>
        <td class="text-center" style="display:table-cell; vertical-align:middle;">
            <input type="submit" class="btn btn-primary">add</input>
        </td>
        </form>
    </tr>


</table>

</body>
</html>