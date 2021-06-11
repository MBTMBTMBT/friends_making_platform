package servelets.mentor_servlets;

import database.daos.EventDAO;
import database.daos.EventLocationDAO;
import database.daos.JoinEventDAO;
import database.dynamicDAOs.DynamicLabelsDAO;
import database.dynamicDAOs.DynamicUserPersonDAO;
import database.tables.Event;
import database.tables.EventLocation;
import database.tables.JoinEvent;
import database.tables.UserPerson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@WebServlet("/locationEventPushServlet")
public class MentorLocationEventPushServlet extends HttpServlet {

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();

        String mentorUsername = null;
        int mentorNumber = -1;

        try {
            mentorUsername = (String) request.getAttribute("mentor_username");
            mentorNumber = (int) request.getAttribute("mentor_number");
        } catch (NullPointerException ignore) {
        }

        if (mentorUsername == null || mentorNumber == -1) {
            mentorUsername = (String) session.getAttribute("mentor_username");
            mentorNumber = (int) session.getAttribute("mentor_number");
        }

        EventLocation eventLocation = EventLocationDAO.getEventLocationByMentorID(mentorNumber);
        Map<String, String> locationMap = new HashMap<>();
        locationMap.put("type", "");
        locationMap.put("geographical_location", "");
        locationMap.put("location_id", "");
        List<List<String>> infoLists = new LinkedList<>();
        List<String> eventTimeList = new LinkedList<>();
        List<String> eventTypeList = new LinkedList<>();
        List<String> participantsList = new LinkedList<>();
        List<String> locationIDList = new LinkedList<>();
        infoLists.add(eventTimeList); infoLists.add(eventTypeList);
        infoLists.add(participantsList); infoLists.add(locationIDList);

        // if this mentor get an event location, he/she is able to see the info inside
        // and see the events
        if (eventLocation != null) {
            locationMap.replace("type", eventLocation.getLocationType());
            locationMap.put("geographical_location", eventLocation.getGeographicalLocation());
            List<Event> events = EventDAO.getEventByLocation(eventLocation.getLocationID());
            if (events != null) {
                Iterator<Event> eventIterator = events.iterator();
                while (eventIterator.hasNext()) {
                    Event eachEvent = eventIterator.next();
                    eventTimeList.add(eachEvent.getTime());
                    eventTypeList.add(eachEvent.getActivities());
                    List<JoinEvent> joinEventList = JoinEventDAO.getJoinEventByLocation(eachEvent.getLocationID());
                    if (joinEventList == null) participantsList.add("0");
                    else participantsList.add(String.valueOf(joinEventList.size()));
                    locationIDList.add(String.valueOf(eachEvent.getLocationID()));
                }
            }
        }

        request.setAttribute("location_map", locationMap);
        request.setAttribute("info_lists", infoLists);
        request.setAttribute("mentor_username", mentorUsername);
        request.setAttribute("mentor_number", mentorNumber);

        session.setAttribute("location_map", locationMap);
        session.setAttribute("info_lists", infoLists);
        session.setAttribute("mentor_username", mentorUsername);
        session.setAttribute("mentor_number", mentorNumber);

        request.getRequestDispatcher("/location_event.jsp").forward(request, response);
    }
}
