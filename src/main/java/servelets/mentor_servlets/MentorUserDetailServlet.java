package servelets.mentor_servlets;

import database.daos.*;
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

@WebServlet("/mentorUserDetailServlet")
public class MentorUserDetailServlet extends HttpServlet {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mentorScreenName = null;
        int mentorNum = -1;

        try {
            mentorScreenName = (String) request.getAttribute("mentor_username");
            mentorNum = (int) request.getAttribute("mentor_number");
        } catch (NullPointerException ignore) {
        }

        if (mentorScreenName == null || mentorNum == -1) {
            HttpSession session = request.getSession();
            mentorScreenName = (String) session.getAttribute("mentor_username");
            mentorNum = (int) session.getAttribute("mentor_number");
        }

        String checkedUsername = null;
        int checkedUserID = -1;

        DynamicUserPersonDAO userPersonDAO = new DynamicUserPersonDAO();

        try {
            checkedUserID = Integer.parseInt(request.getParameter("num"));
        } catch (NullPointerException ignoreIgnore) {
            checkedUserID = -1;
        }

        UserPerson userPerson = userPersonDAO.getUserPersonByUserID(checkedUserID);
        checkedUsername = userPerson.getScreenName();

        // screen name; 1.email; 2.wechat; 3.birthday; 4.gender; 5.slogan; 6.work; 7.sports; 8.food; 9.locations; 10.film; 11.book;
        List<String> msgLst = new LinkedList<>();
        msgLst.add(checkedUsername);  // put username

        String email = userPerson.getEmailAddress();
        if (email == null || email.equals("")) email = "he/she hasn't selected set his/her email yet";
        msgLst.add(email);

        String wechat = userPerson.getWechat();
        if (wechat == null || wechat.equals("")) wechat = "he/she hasn't set his/her Wechat yet";
        msgLst.add(wechat);

        Date birth = userPerson.getDateOfBirth();
        String birthStr;
        if (birth == null) birthStr = "he/she hasn't set his/her birthday yet";
        else birthStr = birth.toString();
        msgLst.add(birthStr);

        String gender = userPerson.getGender();
        msgLst.add(gender);

        String slogan = userPerson.getSlogan();
        if (slogan == null || slogan.equals("")) slogan = "he/she hasn't wrote anything yet";
        msgLst.add(slogan);

        int workID = userPerson.getWork();
        Labels labels = LabelsDAO.getLabelsByKey(workID);
        String work = labels.getWork();
        msgLst.add(work);

        String sports = "";
        List<Object> sportsList = SportsDAO.getAllValuesWithUID(checkedUserID);
        int count = 0;
        for (Object eachObject: sportsList) {
            count += 1;
            Sports eachSport = (Sports) eachObject;
            sports += LabelsDAO.getLabelsByKey(eachSport.getSid()).getSport();
            sports += "; ";
            if (count % 3 == 0) sports += "\n";
        }
        if (sports.equals("")) sports = "he/she hasn't selected the sports he/she does";
        msgLst.add(sports);

        String food = "";
        List<Object> foodList = FoodDAO.getAllValuesWithUID(checkedUserID);
        count = 0;
        for (Object eachObject: foodList) {
            count += 1;
            Food eachFood = (Food) eachObject;
            food += LabelsDAO.getLabelsByKey(eachFood.getFid()).getFood();
            food += "; ";
            if (count % 3 == 0) food += "\n";
        }
        if (food.equals("")) food = "he/she hasn't selected the food he/she likes";
        msgLst.add(food);

        String location = "";
        List<Object> locationList = LocationDAO.getAllValuesWithUID(checkedUserID);
        count = 0;
        for (Object eachObject: locationList) {
            count += 1;
            Location eachLocation = (Location) eachObject;
            location += LabelsDAO.getLabelsByKey(eachLocation.getLid()).getLocations();
            location += "; ";
            if (count % 3 == 0) location += "\n";
        }
        if (location.equals("")) location = "he/she hasn't selected the locations he/she has been to";
        msgLst.add(location);

        String films = "";
        List<Object> filmsList = FilmsDAO.getAllValuesWithUID(checkedUserID);
        count = 0;
        for (Object eachObject: filmsList) {
            count += 1;
            Films eachFilm = (Films) eachObject;
            films += LabelsDAO.getLabelsByKey(eachFilm.getFid()).getFilm();
            films += "; ";
            if (count % 3 == 0) films += "\n";
        }
        if (films.equals("")) films = "he/she hasn't selected the films he/she likes";
        msgLst.add(films);

        String books = "";
        List<Object> booksList = BooksDAO.getAllValuesWithUID(checkedUserID);
        count = 0;
        for (Object eachObject: booksList) {
            count += 1;
            Books eachBook = (Books) eachObject;
            books += LabelsDAO.getLabelsByKey(eachBook.getBid()).getBook();
            books += "; ";
            if (count % 3 == 0) books += "\n";
        }
        if (books.equals("")) books = "he/she hasn't selected the books he/she has read";
        msgLst.add(books);

        msgLst.add(userPerson.getHeadIcon());

        count = 0;
        for (String eachMsg: msgLst) {
            request.setAttribute("msg" + count, eachMsg);
            count += 1;
        }

        Likes likes = LikesDAO.getLikesByKey(mentorNum, checkedUserID);

        request.setAttribute("likes", likes != null);
        request.setAttribute("checked_username", checkedUsername);
        request.setAttribute("checked_user_id", checkedUserID);
        request.setAttribute("mentor_username", mentorScreenName);
        request.setAttribute("mentor_number", mentorNum);

        request.getRequestDispatcher("/mentor_user_detail_information.jsp").forward(request, response);
        // else if (like) {
        // request.getRequestDispatcher("/like.jsp").forward(request, response);
        // }

        HttpSession session = request.getSession();
        session.setAttribute("checked_username", checkedUsername);
        session.setAttribute("checked_user_id", checkedUserID);
    }
}

// <a class="btn btn-info" href="modify.html">change my information</a>
