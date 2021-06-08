package servelets;

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

@WebServlet("/userMainPageServlet")
public class UserMainPageServlet extends HttpServlet {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = null;
        String usernameMsg = null;
        int userID = -1;

        try {
            username = (String) request.getAttribute("user_username");
            usernameMsg = (request.getAttribute("msg_username") != null) ? (String) request.getAttribute("msg_username"): "";
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

        // screen name; 1.email; 2.wechat; 3.birthday; 4.gender; 5.slogan; 6.work; 7.sports; 8.food; 9.locations; 10.film; 11.book;
        List<String> msgLst = new LinkedList<>();
        // msgLst.add(username);  // put username
        msgLst.add(userPerson.getHeadIcon());

        String email = userPerson.getEmailAddress();
        if (email == null || email.equals("")) email = "you haven't set your email yet";
        msgLst.add(email);

        String wechat = userPerson.getWechat();
        if (wechat == null || wechat.equals("")) wechat = "you haven't set your Wechat yet";
        msgLst.add(wechat);

        Date birth = userPerson.getDateOfBirth();
        String birthStr;
        if (birth == null) birthStr = "you haven't set your birthday yet";
        else birthStr = birth.toString();
        msgLst.add(birthStr);

        String gender = userPerson.getGender();
        msgLst.add(gender);

        String slogan = userPerson.getSlogan();
        if (slogan == null || slogan.equals("")) slogan = "write this, about what kind of person you are, or what " +
                "kind of him/her you are expecting";
        msgLst.add(slogan);

        int workID = userPerson.getWork();
        Labels labels = LabelsDAO.getLabelsByKey(workID);
        String work = labels.getWork();
        msgLst.add(work);

        String sports = "";
        List<Object> sportsList = SportsDAO.getAllValuesWithUID(userID);
        int count = 0;
        for (Object eachObject: sportsList) {
            count += 1;
            Sports eachSport = (Sports) eachObject;
            sports += LabelsDAO.getLabelsByKey(eachSport.getSid()).getSport();
            sports += "; ";
            if (count % 3 == 0) sports += "\n";
        }
        if (sports.equals("")) sports = "you can select the sports you do";
        msgLst.add(sports);

        String food = "";
        List<Object> foodList = FoodDAO.getAllValuesWithUID(userID);
        count = 0;
        for (Object eachObject: foodList) {
            count += 1;
            Food eachFood = (Food) eachObject;
            food += LabelsDAO.getLabelsByKey(eachFood.getFid()).getFood();
            food += "; ";
            if (count % 3 == 0) food += "\n";
        }
        if (food.equals("")) food = "you can select the food you like";
        msgLst.add(food);

        String location = "";
        List<Object> locationList = LocationDAO.getAllValuesWithUID(userID);
        count = 0;
        for (Object eachObject: locationList) {
            count += 1;
            Location eachLocation = (Location) eachObject;
            location += LabelsDAO.getLabelsByKey(eachLocation.getLid()).getLocations();
            location += "; ";
            if (count % 3 == 0) location += "\n";
        }
        if (location.equals("")) location = "you can select the locations you've been to";
        msgLst.add(location);

        String films = "";
        List<Object> filmsList = FilmsDAO.getAllValuesWithUID(userID);
        count = 0;
        for (Object eachObject: filmsList) {
            count += 1;
            Films eachFilm = (Films) eachObject;
            films += LabelsDAO.getLabelsByKey(eachFilm.getFid()).getFilm();
            films += "; ";
            if (count % 3 == 0) films += "\n";
        }
        if (films.equals("")) films = "you can select the films you like";
        msgLst.add(films);

        String books = "";
        List<Object> booksList = BooksDAO.getAllValuesWithUID(userID);
        count = 0;
        for (Object eachObject: booksList) {
            count += 1;
            Books eachBook = (Books) eachObject;
            books += LabelsDAO.getLabelsByKey(eachBook.getBid()).getBook();
            books += "; ";
            if (count % 3 == 0) books += "\n";
        }
        if (books.equals("")) books = "you can select the books you've read";
        msgLst.add(books);

        count = 0;
        for (String eachMsg: msgLst) {
            request.setAttribute("msg" + count, eachMsg);
            count += 1;
        }

        request.setAttribute("user_username", username);
        request.setAttribute("user_id", userID);
        request.getRequestDispatcher("/user_mainpage.jsp").forward(request, response);
    }
}

// <a class="btn btn-info" href="modify.html">change my information</a>
