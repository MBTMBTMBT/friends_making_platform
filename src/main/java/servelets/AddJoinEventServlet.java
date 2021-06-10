package servelets;

import database.daos.EventDAO;
import database.daos.EventLocationDAO;
import database.daos.JoinEventDAO;
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
import java.sql.SQLException;
import java.util.*;

@WebServlet("/addJoinEventServlet")
public class AddJoinEventServlet extends HttpServlet {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("addJoinEventServlet");

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

        String key = null;
        try {
            key = request.getParameter("num");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // if ()
        System.out.println(key);
        String[] keys = key.split("%");
        String locationID = keys[0];
        String eventTime = keys[1] + " " + keys[2];

        // check how many
        List<JoinEvent> joinEventList = JoinEventDAO.getJoinEventByUid(userID);
        // System.out.println("TRY TO ADD " + userID + " " + Integer.parseInt(locationID) + " " + eventTime);
        if (joinEventList == null || joinEventList.size() < 2) {
            System.out.println("ADD " + userID + " " + Integer.parseInt(locationID) + " " + eventTime);
            if (JoinEventDAO.getJoinEventByKey(userID, Integer.parseInt(locationID), eventTime) == null) {
                JoinEventDAO.saveJoinEvent(new JoinEvent(userID, Integer.parseInt(locationID), eventTime));
                Event event = EventDAO.getEventByKey(Integer.parseInt(locationID), eventTime);
                event.setNumberofparticipants(event.getNumberofparticipants() + 1);
            }
        }

        HttpSession session = request.getSession();
        request.setAttribute("user_username", username);
        request.setAttribute("user_id", userID);
        session.setAttribute("user_username", username);
        session.setAttribute("user_id", userID);
        request.getRequestDispatcher("/userEventPushServlet").forward(request, response);
    }
}
