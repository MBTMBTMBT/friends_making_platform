package servelets;

import database.daos.PersonDAO;
import database.daos.PsychologicalMentorDAO;
import database.dynamicDAOs.DynamicLabelsDAO;
import database.dynamicDAOs.DynamicUserPersonDAO;
import database.tables.Person;
import database.tables.PsychologicalMentor;
import database.tables.UserPerson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@WebServlet("/userMentorServlet")
public class UserMentorServlet extends HttpServlet {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();

        String username = null;
        int userID = -1;

        try {
            username = (String) request.getAttribute("user_username");
            userID = (int) request.getAttribute("user_id");
        } catch (NullPointerException ignore) {
        }

        if (username == null || userID == -1) {
            username = (String) session.getAttribute("user_username");
            userID = (int) session.getAttribute("user_id");
        }

        List<PsychologicalMentor> mentors = getRecommendedMentor(userID);
        Collections.shuffle(mentors);
        try {
            mentors = mentors.subList(0, 5);
        } catch (IndexOutOfBoundsException ignore) {
        }
        List<Person> people = new LinkedList<>();
        for (PsychologicalMentor eachMentor: mentors) {
            if (eachMentor.getMentorNumber() == 1) continue;
            int eachSystemId = eachMentor.getSystemID();
            people.add(PersonDAO.getPersonByID(eachSystemId));
        }

        List<String> headIconList = new LinkedList<>();
        List<String> userNameList = new LinkedList<>();
        List<String> genderList = new LinkedList<>();
        List<String> idList = new LinkedList<>();

        for (Person each: people) {
            headIconList.add(each.getHeadIcon());
            userNameList.add(each.getScreenName());
            genderList.add(each.getGender());
            PsychologicalMentor eachMentor = PsychologicalMentorDAO.getPsychologicalMentorBySystemID(each.getSystemID());
            idList.add(String.valueOf(eachMentor.getMentorNumber()));
        }

        List<List<String>> infoLists = new LinkedList<>();
        infoLists.add(headIconList);
        infoLists.add(userNameList);
        infoLists.add(genderList);
        infoLists.add(idList);

        request.setAttribute("info_lists", infoLists);
        request.setAttribute("user_username", username);
        request.setAttribute("user_id", userID);

        session.setAttribute("info_lists", infoLists);
        session.setAttribute("user_username", username);
        session.setAttribute("user_id", userID);

        request.getRequestDispatcher("/my_mentor.jsp").forward(request, response);
    }

    private static List<PsychologicalMentor> getRecommendedMentor(int uid) {
        DynamicUserPersonDAO userPersonDAO = new DynamicUserPersonDAO();
        UserPerson userPerson = userPersonDAO.getUserPersonByUserID(uid);
        userPersonDAO.close();
        int age;
        try {
            age = userPerson.getAge();
        } catch (NullPointerException nullPointerException) {
            age = 20;
        }
        int ageRange;
        if (age < 30) ageRange = 10;
        else if (age < 50) ageRange = 20;
        else ageRange = 80;
        String orient = userPerson.getGenderOrientation();

        List<PsychologicalMentor> mentors = PsychologicalMentorDAO.getMentorsWIthAgeRangeAndGenOrient(ageRange, orient);
        return mentors;
    }
}
