package servelets;

import database.daos.EventDAO;
import database.daos.EventLocationDAO;
import database.daos.LocationDAO;
import database.tables.Event;
import database.tables.EventLocation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/addEventServlet")
public class AddEventServlet extends HttpServlet {

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

        request.setAttribute("mentor_username", mentorUsername);
        request.setAttribute("mentor_number", mentorNumber);
        session.setAttribute("mentor_username", mentorUsername);
        session.setAttribute("mentor_number", mentorNumber);

        String activityTime = null, activityType = null;
        try {
            activityTime = request.getParameter("activity_time");
            activityType = request.getParameter("activity_type");
            activityTime = activityTime.replace('T', ' ');
            activityTime += ":00";
            System.out.println(activityTime + " " + activityType);
        } catch (Exception ignore) {
        }
        EventLocation eventLocation = EventLocationDAO.getEventLocationByMentorID(mentorNumber);
        if (activityType != null && eventLocation != null) {
            int eventNum = EventDAO.getEventByLocation(eventLocation.getLocationID()).size();
            if (eventNum < 5) {
                Event event = new Event();
                event.setActivities(activityType);
                event.setTime(activityTime);
                event.setLocationID(eventLocation.getLocationID());
                try {
                    EventDAO.saveEvent(event);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        request.getRequestDispatcher("/locationEventPushServlet").forward(request, response);
    }
}
