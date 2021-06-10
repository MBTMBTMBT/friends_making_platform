package servelets;

import database.daos.AdministratorDAO;
import database.daos.PersonDAO;
import database.daos.PsychologicalMentorDAO;
import database.daos.UserDAO;
import database.tables.Administrator;
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
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            if (username.equals("")) {
                loginFail(request, response, "Cannot login with empty username");
                return;
            }
            Person person = PersonDAO.getPersonByScreenName(username);  // first get this person if possible

            if (person == null) {
                loginFail(request, response);  // if I get null, the username (screen name) doesn't exist.
                return;
            }

            if (personLogin(person, password)) {  // now try to login with the password
                if (isUser(person)) {
                    // login successfully for user
                    User user = UserDAO.getUserBySystemID(person.getSystemID());
                    int userID = user.getUserID();
                    request.setAttribute("user_id", userID);
                    request.setAttribute("user_username", username);
                    request.getRequestDispatcher("/userMainPageServlet").forward(request, response);

                }  else if (isMentor(person)) {
                    // login successfully for mentor
                    PsychologicalMentor mentor = PsychologicalMentorDAO.getPsychologicalMentorBySystemID(person.getSystemID());
                    int mentorNumber = mentor.getMentorNumber();
                    request.setAttribute("mentor_number", mentorNumber);
                    request.setAttribute("mentor_username", username);
                    request.getRequestDispatcher("/mentorMainPageServlet").forward(request, response);

                }  else if (isAdmin(person)) {
                    // login successfully for admin
                    Administrator administrator = AdministratorDAO.getAdministractorBySystemID(person.getSystemID());
                    int adminNumber = administrator.getAdminNumber();
                    request.setAttribute("admin_id", adminNumber);
                    request.setAttribute("admin_username", username);
                    request.getRequestDispatcher("/adminMainPageServlet").forward(request, response);

                } else {
                    loginFail(request, response);  // this should never happen
                }

            } else loginFail(request, response);  // if password is not correct, return with message

        } catch (Exception ignore) {
            loginFail(request, response, "System Exception");
        }
    }

    //  three methods to see what type is this person
    private static boolean isAdmin(Person person) {
        if (person == null) return false;
        int systemID = person.getSystemID();
        Administrator admin = AdministratorDAO.getAdministractorBySystemID(systemID);
        return admin != null;
    }

    private static boolean isMentor(Person person) {
        if (person == null) return false;
        int systemID = person.getSystemID();
        PsychologicalMentor mentor = PsychologicalMentorDAO.getPsychologicalMentorBySystemID(systemID);
        return mentor != null;
    }

    private static boolean isUser(Person person) {
        if (person == null) return false;
        int systemID = person.getSystemID();
        User user = UserDAO.getUserBySystemID(systemID);
        return user != null;
    }

    // check the user's password
    private static boolean personLogin(Person person, String password) {
        return person.getpassword().equals(password);
    }

    // wrong password or invalid username - return and give message
    private static void loginFail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("msg", "Wrong Pass Word, Or invalid username.");
        System.out.println("Login failed!");
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    private static void loginFail(HttpServletRequest request, HttpServletResponse response, String msg) throws ServletException, IOException {
        request.setAttribute("msg", msg);
        System.out.println(msg);
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
}
