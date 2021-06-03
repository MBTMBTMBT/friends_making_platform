package servelets;

import database.daos.*;
import database.tables.*;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.Arrays;

@WebServlet("/setUserAttributesServlet")
public class SetUserAttributesServlet extends HttpServlet {
    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("user_username");
        int userID = Integer.parseInt(request.getParameter("user_id"));

        // "sports", "foods", "Travel Footprint", "film", "Books"
        String[] sports = request.getParameterValues("sports");  // csdn is my father!
        String[] food = request.getParameterValues("foods");
        String[] locations = request.getParameterValues("Travel Footprint");
        String[] films = request.getParameterValues("film");
        String[] books = request.getParameterValues("Books");

        for (String eachSport: sports){
            int labelSerial = LabelsDAO.getKeyByAttribute("Sports", eachSport);
            Sports sportsObj = new Sports(labelSerial, userID);
            SportsDAO.saveSports(sportsObj);
        }

        for (String eachFood: food){
            int labelSerial = LabelsDAO.getKeyByAttribute("Food", eachFood);
            Food foodObj = new Food(labelSerial, userID);
            FoodDAO.saveFood(foodObj);
        }

        for (String eachLocation: locations){
            int labelSerial = LabelsDAO.getKeyByAttribute("Location", eachLocation);
            Location locationObj = new Location(labelSerial, userID);
            LocationDAO.saveLocation(locationObj);
        }

        for (String eachFilm: films){
            int labelSerial = LabelsDAO.getKeyByAttribute("Films", eachFilm);
            Films filmsObj = new Films(labelSerial, userID);
            FilmsDAO.saveFilms(filmsObj);
        }

        for (String eachBook: books){
            int labelSerial = LabelsDAO.getKeyByAttribute("Books", eachBook);
            Books booksObj = new Books(labelSerial, userID);
            BooksDAO.saveBooks(booksObj);
        }

        request.setAttribute("user_username", username);
        request.setAttribute("user_id", userID);
        request.getRequestDispatcher("/pushUserAttributesServlet").forward(request, response);
    }
}
