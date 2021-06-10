package servelets.user_servlets;

import database.daos.EventDAO;
import database.daos.EventLocationDAO;
import database.daos.JoinEventDAO;
import database.daos.LocationDAO;
import database.dynamicDAOs.DynamicLabelsDAO;
import database.dynamicDAOs.DynamicUserPersonDAO;
import database.tables.*;
import org.hibernate.Session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@WebServlet("/userEventPushServlet")
public class UserEventPushServlet extends HttpServlet {

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String username = null;
        int userID = -1;

        try {
            username = (String) request.getAttribute("user_username");
            userID = (int) request.getAttribute("user_id");
        } catch (NullPointerException ignore) {
        }

        if (username == null || userID == -1) {
            HttpSession session = request.getSession();
            username = (String) session.getAttribute("user_username");
            userID = (int) session.getAttribute("user_id");
        }

        DynamicUserPersonDAO userPersonDAO = new DynamicUserPersonDAO();
        UserPerson userPerson = userPersonDAO.getUserPersonByUserID(userID);

        // first find the event this user has already took part in
        // create two empty events
        List<Map<String, String>> selectedEvents = new ArrayList<>(2);
        for (int i = 0; i < 2; i++) {
            Map<String, String> each = new HashMap<>();
            each.put("locationID", "");
            each.put("locationType", "");
            each.put("geo", "");
            each.put("time", "");
            each.put("activityType", "");
            each.put("partNum", "");
            selectedEvents.add(each);
        }
        // check "join_event"
        List<JoinEvent> joinEventList = JoinEventDAO.getJoinEventByUid(userID);
        Iterator<JoinEvent> joinEventIterator = joinEventList.iterator();
        for (int i = 0; i < 2; i++) {
            if (joinEventIterator.hasNext()) {
                JoinEvent eachJoinEvent = joinEventIterator.next();
                Event eachEvent = EventDAO.getEventByKey(eachJoinEvent.getEventLocationID(), eachJoinEvent.getTime());
                EventLocation eachLocation = EventLocationDAO.getEventLocationByKey(eachJoinEvent.getEventLocationID());
                Map<String, String> each = selectedEvents.get(i);
                each.put("locationID", String.valueOf(eachLocation.getLocationID()));
                each.put("locationType", eachLocation.getLocationType());
                each.put("geo", eachLocation.getGeographicalLocation());
                each.put("time", eachJoinEvent.getTime());
                each.put("activityType", eachEvent.getActivities());
                each.put("partNum", String.valueOf(eachEvent.getNumberofparticipants()));
            }
        }

        List<List<String>> infoLists = new LinkedList<>();
        List<String> eventTimeList = new LinkedList<>();
        List<String> eventTypeList = new LinkedList<>();
        List<String> participantsList = new LinkedList<>();
        List<String> locationIDList = new LinkedList<>();
        List<String> locationTypeList = new LinkedList<>();
        List<String> geographicalLocationList = new LinkedList<>();
        // List<String> activityTypeList = new LinkedList<>();
        infoLists.add(eventTimeList); infoLists.add(eventTypeList);
        infoLists.add(participantsList); infoLists.add(locationIDList);
        infoLists.add(locationTypeList); infoLists.add(geographicalLocationList);
        // infoLists.add(activityTypeList);

        List<Event> events = EventDAO.getAllEvent();
        if (events != null) {
            Collections.shuffle(events);
            if (events.size() > 8) {
                events = events.subList(0, 8);
            }
            OUTER: for (Event eachEvent : events) {
                String time = eachEvent.getTime();
                int locationID = eachEvent.getLocationID();
                for (int i = 0; i < 2; i++) {
                    if (selectedEvents.get(i).get("time").equals(time) && selectedEvents.get(i).get("locationID").equals(String.valueOf(locationID))) {
                        continue OUTER;
                    }
                }

                eventTimeList.add(eachEvent.getTime());
                eventTypeList.add(eachEvent.getActivities());
                List<JoinEvent> eachJoinEventList = JoinEventDAO.getJoinEventByLocation(eachEvent.getLocationID());
                if (eachJoinEventList != null) participantsList.add("0");
                else participantsList.add(String.valueOf(eachJoinEventList.size()));
                locationIDList.add(String.valueOf(eachEvent.getLocationID()));

                EventLocation location = EventLocationDAO.getEventLocationByKey(eachEvent.getLocationID());
                locationTypeList.add(location.getLocationType());
                geographicalLocationList.add(location.getGeographicalLocation());
            }
        }

        HttpSession session = request.getSession();
        request.setAttribute("user_username", username);
        request.setAttribute("user_id", userID);
        request.setAttribute("selected_events", selectedEvents);
        request.setAttribute("info_lists", infoLists);

        session.setAttribute("user_username", username);
        session.setAttribute("user_id", userID);
        session.setAttribute("selected_events", selectedEvents);
        session.setAttribute("info_lists", infoLists);
        request.getRequestDispatcher("/user_events.jsp").forward(request, response);
    }
}
