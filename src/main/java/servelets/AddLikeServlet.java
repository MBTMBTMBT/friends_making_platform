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

@WebServlet("/addLikeServlet")
public class AddLikeServlet extends HttpServlet {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = null;
        // String usernameMsg = null;
        int userID = -1;

        String checkedUsername = null;
        // String usernameMsg = null;
        int checkedUserID = -1;

        try {
            username = (String) request.getAttribute("user_username");
            // usernameMsg = (request.getAttribute("msg_username") != null) ? (String) request.getAttribute("msg_username"): "";
            userID = (int) request.getAttribute("user_id");
        } catch (NullPointerException ignore) {
        }

        if (username == null || userID == -1) {
            HttpSession session = request.getSession();
            username = (String) session.getAttribute("user_username");
            userID = (int) session.getAttribute("user_id");
        }

        try {
            checkedUsername = (String) request.getAttribute("checked_username");
            // usernameMsg = (request.getAttribute("msg_username") != null) ? (String) request.getAttribute("msg_username"): "";
            checkedUserID = (int) request.getAttribute("checked_user_id");
        } catch (NullPointerException ignore) {
        }

        if (username == null || userID == -1) {
            HttpSession session = request.getSession();
            username = (String) session.getAttribute("checked_username");
            userID = (int) session.getAttribute("checked_user_id");
        }

        // I think this does not work here
        /*
        int checkedUserID = Integer.parseInt(request.getParameter("num"));
        DynamicUserPersonDAO userPersonDAO = new DynamicUserPersonDAO();
        UserPerson userPerson = userPersonDAO.getUserPersonByUserID(checkedUserID);
        String checkedUsername = userPerson.getScreenName();
         */

        Likes likes = new Likes();
        System.out.println("Add likes: " + userID + " - " + checkedUserID);
        likes.setUid1(userID); likes.setUid2(checkedUserID);
        LikesDAO.saveLikes(likes);

        request.setAttribute("checked_username", checkedUsername);
        request.setAttribute("checked_user_id", checkedUserID);
        request.setAttribute("user_username", username);
        request.setAttribute("user_id", userID);
        request.getRequestDispatcher("/userDetailServlet").forward(request, response);
    }
}
