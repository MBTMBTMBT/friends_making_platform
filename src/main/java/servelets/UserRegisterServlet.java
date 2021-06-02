package servelets;

import database.daos.AdministractorDAO;
import database.daos.PersonDAO;
import database.daos.PsychologicalMentorDAO;
import database.daos.UserDAO;
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

@WebServlet("/userRegisterServlet")
public class UserRegisterServlet extends HttpServlet {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String surname = request.getParameter("surname");
        String forename = request.getParameter("forename");
        String sex = request.getParameter("sex");

        // none of these should be empty
        if (username.equals("")) {
            registerFail(request, response, "Please don't entre empty username");
        }
        if (password.equals("")) {
            registerFail(request, response, "Please don't entre empty password");
        }
        if (surname.equals("")) {
            registerFail(request, response, "Please don't entre empty surname");
        }
        if (forename.equals("")) {
            registerFail(request, response, "Please don't entre empty forename");
        }
        if (sex.equals("")) {
            registerFail(request, response, "Please choose your gender");
        }

        // see if the username already exists
        if (PersonDAO.getPersonByScreenName(surname) != null) {
            registerFail(request, response, "Sorry, this username already exists, please try to another one");
        }

        // if these all works, register a new Person entity and a new User entity.
        // first create the person
        Person person = new Person();
        person.setScreenName(username);
        person.setForename(forename);
        person.setSurname(surname);
        person.setGender(sex);
        person.setpassword(password);
        PersonDAO.savePerson(person);
        person = PersonDAO.getPersonByScreenName(username);
        assert person != null;  // the person have just been added so this shouldn't be null.
        int systemID = person.getSystemID();

        // then create the user
        User user = new User();
        user.setSystemID(systemID);
        UserDAO.saveUser(user);
        user = UserDAO.getUserBySystemID(systemID);
        int userID = user.getUserID();
        request.setAttribute("user_id", userID);
        request.setAttribute("user_username", username);
        request.getRequestDispatcher("/user_attributes.jsp").forward(request, response);
    }

    private static void registerFail(HttpServletRequest request, HttpServletResponse response, String msg) throws ServletException, IOException {
        request.setAttribute("msg", msg);
        System.out.println(msg);
        request.getRequestDispatcher("/register.jsp").forward(request, response);
    }
}
