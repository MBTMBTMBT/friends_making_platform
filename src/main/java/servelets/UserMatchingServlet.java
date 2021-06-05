package servelets;

import database.dynamicDAOs.*;
import database.exceptions.WrongAttributeNameException;
import database.standarizedTables.LabelObject;
import database.standarizedTables.StdLocation;
import database.tables.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class UserMatchingServlet extends HttpServlet {
    private static final int SEARCH_UP_LIMIT = 20;

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    private static class CompareNode implements Comparable {
        private User user;
        private int mark;

        CompareNode(User user, int mark) {
            this.user = user;
            this.mark = mark;
        }

        public int getMark() {
            return mark;
        }

        public User getUser() {
            return user;
        }

        public int addMark() {
            mark += 1;
            return mark;
        }

        @Override
        public int compareTo(Object o) {
            CompareNode competitor = (CompareNode) o;
            return Integer.compare(this.getMark(), competitor.getMark());
        }
    }

    private List<CompareNode> matchLabelObjs(String attributeName, User user) {
        UserCommonAttributesDAO dao;
        if (attributeName.equals("StdBooks")) dao = new DynamicBooksDAO();
        else if (attributeName.equals("StdFilms")) dao = new DynamicFilmsDAO();
        else if (attributeName.equals("StdFood")) dao = new DynamicFoodDAO();
        else if (attributeName.equals("StdLocation")) dao = new DynamicLocationDAO();
        else if (attributeName.equals("StdSports")) dao = new DynamicSportsDAO();
        else throw new WrongAttributeNameException("Attribute " + attributeName + " is not valid");
        DynamicUserDAO userDAO = new DynamicUserDAO();
        // DynamicLabelsDAO labelsDAO = new DynamicLabelsDAO();
        return null;
    }

    private List<CompareNode> matchForSingleLabel(String attributeName, String attributeValue,
                                                  User user, UserCommonAttributesDAO dao,
                                                  DynamicUserDAO userDAO) {
                                                  // DynamicLabelsDAO labelsDAO) {
        List<LabelObject> thisUsersAttributes = dao.getAllValuesWithUserID(user.getUserID());
        for (LabelObject eachObj: thisUsersAttributes) {
            List<LabelObject> allUsersWithThisLabel = dao.getAllValuesWithLabelID(eachObj.getLabelId());
            List<CompareNode> keepList = new LinkedList<>();
            if (allUsersWithThisLabel.size() <= SEARCH_UP_LIMIT) {
                for (LabelObject each: allUsersWithThisLabel) {
                    if (each.getUserId() != user.getUserID()) {
                        User eachUser = userDAO.getUserByID(user.getUserID());
                        keepList.add(new CompareNode(eachUser, 1));
                    }
                }
            } else {
                // todo: select random
            }
        }
        // labelsDAO.
        // dao.findCommon(user.getUserID(), )
        return null;
    }
}
