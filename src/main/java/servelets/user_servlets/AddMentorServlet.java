package servelets.user_servlets;

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

@WebServlet("/addMentorServlet")
public class AddMentorServlet extends HttpServlet {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = null;
        int userID = -1;

        HttpSession session = request.getSession();

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

        int mentorID = -1;
        try {
            mentorID = Integer.parseInt(request.getParameter("mentor"));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        if (mentorID != -1 && mentorID != 0) {  // mentor can't be the default one!
            User user = UserDAO.getUserByID(userID);
            if (user.getMentorID() != 1) request.setAttribute("msg", "You can't have more than one mentor!");
            else {
                System.out.println("Add mentor: " + userID + " - " + mentorID);
                user.setMentorID(mentorID);
                UserDAO.updateUser(user);
            }
        }

        request.setAttribute("user_username", username);
        request.setAttribute("user_id", userID);
        request.getRequestDispatcher("/userMentorServlet").forward(request, response);
    }
}
