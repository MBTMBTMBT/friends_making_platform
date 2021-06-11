package servelets.user_servlets;

import database.daos.*;
import database.dynamicDAOs.*;
import database.standarizedTables.LabelObject;
import database.standarizedTables.StdSports;
import database.tables.*;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.prefs.Preferences;

@WebServlet("/setUserAttributesServlet")
public class SetUserAttributesServlet extends HttpServlet {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = null;
        int userID = -1;

        try {
            username = (String) request.getAttribute("user_username");
            userID = (int) request.getAttribute("user_id");
        } catch (NullPointerException ignore) {
        }

        if (username == null || userID == -1) {
            HttpSession session = request.getSession();
            username = (String) session.getAttribute("user_username");
            userID = (int) session.getAttribute("user_id");
        }

        // username email wechat birthday gender_preference slogan industry
        try {
            // set new user name
            String newUserName = request.getParameter("screenname");
            if (!newUserName.equals(username)) {  // see if this name has already existed
                if (PersonDAO.getPersonByScreenName(newUserName) != null) {
                    Person thisPerson = PersonDAO.getPersonByScreenName(username);
                    assert thisPerson != null;
                    thisPerson.setScreenName(newUserName);
                    PersonDAO.updatePerson(thisPerson);
                    username = newUserName;
                } else {
                    request.setAttribute("msg_username", "this username has already existed");
                }
            }

            // set new email
            String newEmail = request.getParameter("email");
            User user = UserDAO.getUserByID(userID);
            if (newEmail != null && !newEmail.equals("")) {
                user.setEmailaddress(newEmail);
                UserDAO.updateUser(user);
            }

            // set new Wechat
            String newWechat = request.getParameter("Wechat");
            if (newWechat != null && !newWechat.equals("")) {
                user.setWechat(newWechat);
                UserDAO.updateUser(user);
            }

            // set new birthday
            SimpleDateFormat parseDate = new java.text.SimpleDateFormat("yyyy-MM-dd");
            java.sql.Date birthday;
            if (request.getParameter("birthday") != null && !request.getParameter("birthday").equals("")) {
                try {
                    birthday = new java.sql.Date(parseDate.parse(request.getParameter("birthday")).getTime());
                    System.out.println(birthday);
                    user.setDataOfBirth(birthday);
                    UserDAO.updateUser(user);
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }
            }

            // gender preference
            String genderPreference = request.getParameter("orientation");
            if (genderPreference != null && !genderPreference.equals("")) {
                try {
                    String userGender = PersonDAO.getPersonByScreenName(username).getGender();
                    if (userGender.equals("male")) {
                        if (genderPreference.equals("female")) {
                            user.setGenderOrientation("hetero");
                        } else {
                            user.setGenderOrientation("homosexual");
                        }
                    } else {
                        if (genderPreference.equals("male")) {
                            user.setGenderOrientation("hetero");
                        } else {
                            user.setGenderOrientation("homosexual");
                        }
                    }
                    UserDAO.updateUser(user);
                } catch (NullPointerException nullPointerException) {
                    nullPointerException.printStackTrace();
                }
            }

            // set new slogan
            String newSlogan = request.getParameter("slogan");
            if (newSlogan != null && !newSlogan.equals("")) {
                user.setSlogan(newSlogan);
                UserDAO.updateUser(user);
            }

            String work = request.getParameter("work");
            if (work != null) {
                // System.out.println(work);
                if (!work.equals("") && !work.equals("not selected")) {
                    int labelSerial = LabelsDAO.getKeyByAttribute("work", work);
                    // System.out.println("key " + labelSerial);
                    user.setWork(labelSerial);
                } else {
                    user.setWork(1);
                }
                UserDAO.updateUser(user);
            }

            // "sports", "foods", "Travel Footprint", "film", "Books"
            String[] sports = request.getParameterValues("sports");  // csdn is my father!
            String[] food = request.getParameterValues("foods");
            String[] locations = request.getParameterValues("Travel Footprint");
            String[] films = request.getParameterValues("film");
            String[] books = request.getParameterValues("Books");
            System.out.println(Arrays.toString(sports));
            System.out.println(Arrays.toString(food));
            System.out.println(Arrays.toString(locations));
            System.out.println(Arrays.toString(films));
            System.out.println(Arrays.toString(books));

            // sports
            if (sports != null) {
                DynamicSportsDAO sportsDAO = new DynamicSportsDAO();
                for (String eachSport : sports) {
                    int labelSerial = LabelsDAO.getKeyByAttribute("Sport", eachSport);
                    LabelObject sportsObj = new StdSports(labelSerial, userID);
                    sportsDAO.saveLabel(sportsObj);
                }
                // it is important to remove those originally be in the table but should not be anymore
                Collection<String> collection = Arrays.asList(sports);
                List<LabelObject> sportsList = sportsDAO.getAllValuesWithUserID(userID);
                for (LabelObject eachSportObj : sportsList) {
                    // Sports eachSport = (Sports) eachSportObj;
                    int sid = eachSportObj.getLabelId();
                    String sportName = LabelsDAO.getLabelsByKey(sid).getSport();
                    if (!collection.contains(sportName)) {
                        sportsDAO.deleteLabelByKey(sid, userID);
                    }
                }
                sportsDAO.close();
            }

            // food
            if (food != null) {
                for (String eachFood : food) {
                    int labelSerial = LabelsDAO.getKeyByAttribute("Food", eachFood);
                    Food foodObj = new Food(labelSerial, userID);
                    FoodDAO.saveFood(foodObj);
                }
                Collection<String> collection = Arrays.asList(food);
                DynamicFoodDAO foodDAO = new DynamicFoodDAO();
                List<LabelObject> foodList = foodDAO.getAllValuesWithUserID(userID);
                for (LabelObject eachFoodObj : foodList) {
                    // Food eachFood = (Food) eachFoodObj;
                    int fid = eachFoodObj.getLabelId();
                    String foodName = LabelsDAO.getLabelsByKey(fid).getFood();
                    if (!collection.contains(foodName)) {
                        foodDAO.deleteLabelByKey(fid, userID);
                    }
                }
                foodDAO.close();
            }

            if (locations != null) {
                for (String eachLocation : locations) {
                    int labelSerial = LabelsDAO.getKeyByAttribute("Locations", eachLocation);
                    Location locationObj = new Location(labelSerial, userID);
                    LocationDAO.saveLocation(locationObj);
                }
                Collection<String> collection = Arrays.asList(locations);
                DynamicLocationDAO locationDAO = new DynamicLocationDAO();
                List<LabelObject> locationList = locationDAO.getAllValuesWithUserID(userID);
                for (LabelObject eachLocationObj : locationList) {
                    // Location eachLocation = (Location) eachLocationObj;
                    int lid = eachLocationObj.getLabelId();
                    String locationName = LabelsDAO.getLabelsByKey(lid).getLocations();
                    if (!collection.contains(locationName)) {
                        locationDAO.deleteLabelByKey(lid, userID);
                    }
                }
                locationDAO.close();
            }

            if (films != null) {
                for (String eachFilm : films) {
                    int labelSerial = LabelsDAO.getKeyByAttribute("Film", eachFilm);
                    Films filmsObj = new Films(labelSerial, userID);
                    FilmsDAO.saveFilms(filmsObj);
                }
                Collection<String> collection = Arrays.asList(films);
                DynamicFilmsDAO filmsDAO = new DynamicFilmsDAO();
                List<LabelObject> filmsList = filmsDAO.getAllValuesWithUserID(userID);
                for (LabelObject eachFilmObj : filmsList) {
                    // Films eachFilm = (Films) eachFilmObj;
                    int fid = eachFilmObj.getLabelId();
                    String filmName = LabelsDAO.getLabelsByKey(fid).getFilm();
                    if (!collection.contains(filmName)) {
                        filmsDAO.deleteLabelByKey(fid, userID);
                    }
                }
                filmsDAO.close();
            }

            if (books != null) {
                for (String eachBook : books) {
                    int labelSerial = LabelsDAO.getKeyByAttribute("Book", eachBook);
                    Books booksObj = new Books(labelSerial, userID);
                    BooksDAO.saveBooks(booksObj);
                }
                Collection<String> collection = Arrays.asList(books);
                DynamicBooksDAO booksDAO = new DynamicBooksDAO();
                List<LabelObject> bookList = booksDAO.getAllValuesWithUserID(userID);
                for (LabelObject eachBookObj : bookList) {
                    // Books eachBook = (Books) eachBookObj;
                    int bid = eachBookObj.getLabelId();
                    String bookName = LabelsDAO.getLabelsByKey(bid).getBook();
                    if (!collection.contains(bookName)) {
                        booksDAO.deleteLabelByKey(bid, userID);
                    }
                }
                booksDAO.close();
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        request.setAttribute("user_username", username);
        request.setAttribute("user_id", userID);
        request.getRequestDispatcher("/pushUserAttributesServlet").forward(request, response);
    }
}
