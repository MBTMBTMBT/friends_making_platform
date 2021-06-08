package servelets;

import database.daos.*;
import database.dynamicDAOs.DynamicLabelsDAO;
import database.dynamicDAOs.DynamicUserPersonDAO;
import database.tables.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

@WebServlet("/cancelMentorServlet")
public class CancelMentorServlet extends HttpServlet {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = null;
        int userID = -1;

        HttpSession session;

        try {
            username = (String) request.getAttribute("user_username");
            userID = (int) request.getAttribute("user_id");
        } catch (NullPointerException ignore) {
        }

        if (username == null || userID == -1) {
            session = request.getSession();
            username = (String) session.getAttribute("user_username");
            userID = (int) session.getAttribute("user_id");
        }

        User user = UserDAO.getUserByID(userID);
        System.out.println("Cancel mentor: " + userID + " - " + user.getMentorID());
        user.setMentorID(1);
        UserDAO.updateUser(user);

        request.setAttribute("user_username", username);
        request.setAttribute("user_id", userID);
        request.getRequestDispatcher("/userMentorServlet").forward(request, response);
    }
}
