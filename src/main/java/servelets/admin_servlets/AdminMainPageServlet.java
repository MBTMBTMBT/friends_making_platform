package servelets.admin_servlets;

import database.dynamicDAOs.DynamicUserPersonDAO;
import database.tables.UserPerson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/adminMainPageServlet")
public class AdminMainPageServlet extends HttpServlet {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String adminUsername = null;
        // String usernameMsg = null;
        int adminID = -1;

        String searchedName = null;
        try {
            searchedName = request.getParameter("find_screenname");
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

        if (searchedName == null) searchedName = "";
        DynamicUserPersonDAO userPersonDAO = new DynamicUserPersonDAO();
        List<UserPerson> userPersonList = userPersonDAO.userPersonSearchNolimit(searchedName);

        HttpSession session = request.getSession();
        request.setAttribute("user_person_list", userPersonList);
        request.setAttribute("admin_username", adminUsername);
        request.setAttribute("admin_id", adminID);
        session.setAttribute("user_person_list", userPersonList);
        session.setAttribute("admin_username", adminUsername);
        session.setAttribute("admin_id", adminID);
        request.getRequestDispatcher("/admin.jsp").forward(request, response);
    }
}
