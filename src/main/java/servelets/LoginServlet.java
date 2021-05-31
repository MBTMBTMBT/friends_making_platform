package servelets;

import database.daos.AdministractorDAO;
import database.daos.PersonDAO;
import database.daos.PsychologicalMentorDAO;
import database.daos.UserDAO;
import database.exceptions.PersonNotExistsException;
import database.tables.Administractor;
import database.tables.Person;
import database.tables.PsychologicalMentor;
import database.tables.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        try {
            int id_int = Integer.parseInt(id);
            if (isAdmin(id_int)) {
                if (AdministractorDAO.adminLogin(id_int, password)) {
                    // login successful for admin
                    request.setAttribute("admin_id", id_int);
                    request.getRequestDispatcher("/adminMainPageServlet").forward(request, response);
                } else {
                    request.setAttribute("msg", "Wrong Pass Word");
                    System.out.println("Wrong Pass Word");
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                }
            } else if (UserDAO.isUser(id_int)) {
                if (UserDAO.userLogin(id_int, password)) {
                    // login successful for user
                    request.getRequestDispatcher("/user_interface.jsp").forward(request, response);
                } else {
                    request.setAttribute("msg", "Wrong Pass Word");
                    System.out.println("Wrong Pass Word");
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("msg", "User or Admin Does Not Exist");
                System.out.println("User or Admin Does Not Exist");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        } catch (NumberFormatException exception) {
            request.setAttribute("msg", "ID value should be in full numbers");
            System.out.println("ID value should be in full numbers");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    private static boolean isAdmin(String screenName) {
        Person person = PersonDAO.getPersonByScreenName(screenName);
        if (person == null) return false;
        int systemID = person.getSystemID();
        Administractor admin = AdministractorDAO.getAdministractorBySystemID(systemID);
        return admin != null;
    }

    private static boolean isMentor(String screenName) {
        Person person = PersonDAO.getPersonByScreenName(screenName);
        if (person == null) return false;
        int systemID = person.getSystemID();
        PsychologicalMentor mentor = PsychologicalMentorDAO.getPhsycological_MentorBySystemID(systemID);
        return mentor != null;
    }

    private static boolean isUser(String screenName) {
        Person person = PersonDAO.getPersonByScreenName(screenName);
        if (person == null) return false;
        int systemID = person.getSystemID();
        User user = UserDAO.getUserBySystemID(systemID);
        return user != null;
    }

    private static int personLogin(String screenName, String password) {
        Person person = PersonDAO.getPersonByScreenName(screenName);
        if (person == null) throw new PersonNotExistsException("Person with screen name " + screenName + " does not exist.");
        return person.getSystemID();
    }
}
