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

@WebServlet("/deleteEventServlet")
public class DeleteEventServlet extends HttpServlet {

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

        String key = null;
        try {
            key = request.getParameter("num");
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert key != null;
        System.out.println(key);
        String[] keys = key.split("%");
        String locationID = keys[0];
        String eventTime = keys[1] + " " + keys[2];
        EventDAO.deleteEventByKey(Integer.parseInt(locationID), eventTime);

        request.getRequestDispatcher("/locationEventPushServlet").forward(request, response);
    }
}
