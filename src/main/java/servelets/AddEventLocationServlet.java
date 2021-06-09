package servelets;

import database.daos.EventLocationDAO;
import database.daos.LocationDAO;
import database.tables.EventLocation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/addEventLocationServlet")
public class AddEventLocationServlet extends HttpServlet {

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

        String locationType = null, geographicalLocation = null;
        try {
            locationType = request.getParameter("location_type");
            geographicalLocation = request.getParameter("geographical_location");
        } catch (Exception ignore) {
        }
        if (locationType != null) {
            EventLocation eventLocation = new EventLocation();
            eventLocation.setLocationType(locationType);
            eventLocation.setGeographicalLocation(geographicalLocation);
            eventLocation.setManagerID(mentorNumber);
            if (EventLocationDAO.getEventLocationByMentorID(mentorNumber) == null) {
                EventLocationDAO.saveEventLocation(eventLocation);
            }
        }

        request.getRequestDispatcher("/locationEventPushServlet").forward(request, response);
    }
}
