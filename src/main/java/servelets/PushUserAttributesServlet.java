package servelets;

import database.daos.PersonDAO;
import database.daos.UserDAO;
import database.tables.Person;
import database.tables.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

@WebServlet("/pushUserAttributesServlet")
public class PushUserAttributesServlet extends HttpServlet {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        // request.getRequestDispatcher("/user_attributes.jsp").forward(request, response);
        String username = (String) request.getAttribute("user_username");
        int userID = (int) request.getAttribute("user_id");

        User user = UserDAO.getUserByID(userID);
        Person person = PersonDAO.getPersonByScreenName(username);

        // screen name; email; wechat; birthday; gender orientation; slogan; sports; food; locations; film; book;
        List<String> msgLst = new LinkedList<>();
        msgLst.add(username);  // put username

        String email = user.getEmailaddress();
        if (email == null || email.equals("")) email = "entre your email";
        msgLst.add(email);

        String weChat = user.getWechat();
        if (weChat == null || weChat.equals("")) weChat = "entre your wechat";
        msgLst.add(weChat);

        Date birth = user.getDataOfBirth();
        String birthStr;
        if (birth == null) birthStr = "entre your birthday";
        else birthStr = birth.toString();
        msgLst.add(birthStr);

        String genderExpectation;
        assert person != null;
        String gender = person.getGender();
        String genderOrientation = user.getGenderOrientation();
        if (gender.equals("male")) {
            if (genderOrientation.equals("hetero")) {
                genderExpectation = "female";
            } else {
                genderExpectation = "male";
            }
        } else {
            if (genderOrientation.equals("hetero")) {
                genderExpectation = "male";
            } else {
                genderExpectation = "female";
            }
        }
        msgLst.add(genderExpectation);

        String slogan = user.getSlogan();
        if (slogan == null || slogan.equals("")) slogan = "entre your slogan";
        msgLst.add(slogan);


    }
}
