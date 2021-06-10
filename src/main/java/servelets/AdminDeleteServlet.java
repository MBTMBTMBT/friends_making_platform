package servelets;

import database.daos.PersonDAO;
import database.dynamicDAOs.DynamicUserPersonDAO;
import database.tables.Person;
import database.tables.UserPerson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/adminDeleteServlet")
public class AdminDeleteServlet extends HttpServlet {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String adminUsername = null;
        int adminID = -1;

        String screenName = null;
        String password = null;
        try {
            screenName = request.getParameter("screenname");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            password = request.getParameter("password");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            adminUsername = (String) request.getAttribute("admin_username");
            adminID = (int) request.getAttribute("admin_id");
        } catch (NullPointerException ignore) {
        }

        if (adminUsername == null || adminID == -1) {
            HttpSession session = request.getSession();
            adminUsername = (String) session.getAttribute("admin_username");
            adminID = (int) session.getAttribute("admin_id");
        }

        if (screenName != null && PersonDAO.getPersonByScreenName(adminUsername) != null
                && PersonDAO.getPersonByScreenName(adminUsername).getpassword().equals(password)) {
            Person person = PersonDAO.getPersonByScreenName(screenName);
            if (person != null) PersonDAO.deletePersonByID(person.getSystemID());
        }

        request.setAttribute("admin_username", adminUsername);
        request.setAttribute("admin_id", adminID);
        request.getRequestDispatcher("/adminMainPageServlet").forward(request, response);
    }
}
