package servelets.mentor_servlets;

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
import java.util.*;

@WebServlet("/mentorMainPageServlet")
public class MentorMainPageServlet extends HttpServlet {

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String searchedName = null;

        try {
            searchedName = request.getParameter("find_screenname");
        } catch (Exception e) {
            e.printStackTrace();
        }

        HttpSession session = request.getSession();

        String mentorUsername = null;
        int mentorNumber = -1;

        try {
            mentorUsername = (String) request.getAttribute("mentor_username");
            mentorNumber = (int) request.getAttribute("mentor_number");
        } catch (NullPointerException ignore) {
        }

        if (mentorUsername == null || mentorNumber == -1) {
            mentorUsername = (String) session.getAttribute("mentor_username");
            mentorNumber = (int) session.getAttribute("mentor_number");
        }

        DynamicUserPersonDAO userPersonDAO = new DynamicUserPersonDAO();
        List<UserPerson> userPersonList = null;
        if (searchedName == null) userPersonList = userPersonDAO.getUserPersonByMentorID(mentorNumber);
        else userPersonList = userPersonDAO.getUserPersonByMentorNumAndUserScreenName(mentorNumber, searchedName);
        userPersonDAO.close();  // always close
        // Collections.shuffle(userPersonList);
        List<String> headIconList = new LinkedList<>();
        List<String> userNameList = new LinkedList<>();
        List<String> genderList = new LinkedList<>();
        List<String> ageList = new LinkedList<>();
        List<String> workList = new LinkedList<>();
        List<String> userIDList = new LinkedList<>();
        DynamicLabelsDAO labelsDAO = new DynamicLabelsDAO();
        for (UserPerson each: userPersonList) {
            headIconList.add(each.getHeadIcon());
            userNameList.add(each.getScreenName());
            genderList.add(each.getGender());
            ageList.add(String.valueOf(each.getAge()));
            workList.add(labelsDAO.getLabelsByKey(each.getWork()).getWork());
            userIDList.add(String.valueOf(each.getUserID()));
        }
        labelsDAO.close();
        userPersonDAO.close();

        List<List<String>> infoLists = new LinkedList<>();
        infoLists.add(headIconList);
        infoLists.add(userNameList);
        infoLists.add(genderList);
        infoLists.add(ageList);
        infoLists.add(workList);
        infoLists.add(userIDList);

        request.setAttribute("info_lists", infoLists);
        request.setAttribute("mentor_username", mentorUsername);
        request.setAttribute("mentor_number", mentorNumber);

        session.setAttribute("info_lists", infoLists);
        session.setAttribute("mentor_username", mentorUsername);
        session.setAttribute("mentor_number", mentorNumber);

        request.getRequestDispatcher("/mentor.jsp").forward(request, response);
    }
}
