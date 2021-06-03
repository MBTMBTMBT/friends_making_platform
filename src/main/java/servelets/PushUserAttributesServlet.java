package servelets;

import database.daos.*;
import database.tables.*;

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

        int workID = user.getWork();
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
            sports += " ";
            if (count % 3 == 0) sports += "\n";
        }
        msgLst.add(sports);

        String food = "";
        List<Object> foodList = FoodDAO.getAllValuesWithUID(userID);
        count = 0;
        for (Object eachObject: foodList) {
            count += 1;
            Food eachFood = (Food) eachObject;
            food += LabelsDAO.getLabelsByKey(eachFood.getFid()).getFood();
            food += " ";
            if (count % 3 == 0) food += "\n";
        }
        msgLst.add(food);

        String location = "";
        List<Object> locationList = LocationDAO.getAllValuesWithUID(userID);
        count = 0;
        for (Object eachObject: locationList) {
            count += 1;
            Location eachLocation = (Location) eachObject;
            location += LabelsDAO.getLabelsByKey(eachLocation.getLid()).getLocations();
            location += " ";
            if (count % 3 == 0) location += "\n";
        }
        msgLst.add(location);

        String films = "";
        List<Object> filmsList = SportsDAO.getAllValuesWithUID(userID);
        count = 0;
        for (Object eachObject: filmsList) {
            count += 1;
            Films eachFilm = (Films) eachObject;
            films += LabelsDAO.getLabelsByKey(eachFilm.getFid()).getFilm();
            films += " ";
            if (count % 3 == 0) films += "\n";
        }
        msgLst.add(films);

        String books = "";
        List<Object> booksList = BooksDAO.getAllValuesWithUID(userID);
        count = 0;
        for (Object eachObject: booksList) {
            count += 1;
            Books eachBook = (Books) eachObject;
            books += LabelsDAO.getLabelsByKey(eachBook.getBid()).getBook();
            books += " ";
            if (count % 3 == 0) books += "\n";
        }
        msgLst.add(books);

        count = 0;
        for (String eachMsg: msgLst) {
            request.setAttribute("msg" + count, eachMsg);
        }
        request.getRequestDispatcher("/user_attributes.jsp").forward(request, response);
    }
}
