package servelets.user_servlets;

import database.daos.LikesDAO;
import database.dynamicDAOs.DynamicLabelsDAO;
import database.dynamicDAOs.DynamicUserPersonDAO;
import database.tables.Likes;
import database.tables.UserPerson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@WebServlet("/userLikeOthersServlet")
public class UserLikeOthersServlet extends HttpServlet {

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String username = null;
        // String usernameMsg = null;
        int userID = -1;

        try {
            username = (String) request.getAttribute("user_username");
            // usernameMsg = (request.getAttribute("msg_username") != null) ? (String) request.getAttribute("msg_username") : "";
            userID = (int) request.getAttribute("user_id");
        } catch (NullPointerException ignore) {
        }

        if (username == null || userID == -1) {
            HttpSession session = request.getSession();
            username = (String) session.getAttribute("user_username");
            userID = (int) session.getAttribute("user_id");
        }

        DynamicUserPersonDAO userPersonDAO = new DynamicUserPersonDAO();
        UserPerson userPerson = userPersonDAO.getUserPersonByUserID(userID);

        // List<UserMatchingServlet.CompareNode> nodes = matchLabelObjs(userPerson);
        List<Likes> likesList = LikesDAO.getLikesByFirstKey(userPerson.getUserID());
        List<String> headIconList = new LinkedList<>();
        List<String> userNameList = new LinkedList<>();
        List<String> genderList = new LinkedList<>();
        List<String> ageList = new LinkedList<>();
        List<String> workList = new LinkedList<>();
        List<String> userIDList = new LinkedList<>();
        DynamicLabelsDAO labelsDAO = new DynamicLabelsDAO();
        for (Likes each : likesList) {
            UserPerson eachUserPerson = userPersonDAO.getUserPersonByUserID(each.getUid2());
            headIconList.add(eachUserPerson.getHeadIcon());
            userNameList.add(eachUserPerson.getScreenName());
            genderList.add(eachUserPerson.getGender());
            ageList.add(String.valueOf(eachUserPerson.getAge()));
            workList.add(labelsDAO.getLabelsByKey(eachUserPerson.getWork()).getWork());
            userIDList.add(String.valueOf(eachUserPerson.getUserID()));
            System.out.println(eachUserPerson.toString());
        }

        // always close them
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
        request.setAttribute("user_username", username);
        request.setAttribute("user_id", userID);
        HttpSession session = request.getSession();
        session.setAttribute("info_lists", infoLists);

        request.setAttribute("user_gender", userPerson.getGender());
        session.setAttribute("user_gender", userPerson.getGender());

        request.getRequestDispatcher("/like.jsp").forward(request, response);
    }
}
