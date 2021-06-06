package servelets;

import database.dynamicDAOs.*;
import database.exceptions.WrongAttributeNameException;
import database.standarizedTables.LabelObject;
import database.tables.UserPerson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class UserMatchingServlet extends HttpServlet {
    private static final int SEARCH_UP_LIMIT = 20;
    private static final int OUTPUT_LIMIT = 15;
    private static final String[] LABELS = new String[] {"StdBooks", "StdFilms", "StdFood", "StdLocation", "StdSports"};

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    private static class CompareNode implements Comparable<CompareNode> {
        private UserPerson userPerson;
        private int mark;

        CompareNode(UserPerson userPerson, int mark) {
            this.userPerson = userPerson;
            this.mark = mark;
        }

        public int getMark() {
            return mark;
        }

        public UserPerson getUserPerson() {
            return userPerson;
        }

        public void addMark() {
            mark += 1;
        }

        @Override
        public int compareTo(CompareNode o) {
            return Integer.compare(this.getMark(), o.getMark());
        }
    }

    private List<CompareNode> matchLabelObjs(UserPerson userPerson) {
        String genderPreference;
        String targetGenderOrientation;
        if (userPerson.getGender().equals("female")) {
            if (userPerson.getGenderOrientation().equals("hetero")) {
                genderPreference = "male";
            }
            else {
                genderPreference = "female";
            }
            targetGenderOrientation = "hetero";
        } else {
            if (userPerson.getGenderOrientation().equals("hetero")) {
                genderPreference = "female";
            }
            else {
                genderPreference = "male";
            }
            targetGenderOrientation = "homosexual";
        }
        DynamicUserPersonDAO userDAO = new DynamicUserPersonDAO();
        UserCommonAttributesDAO commonAttributesDAO;
        List<CompareNode> remain = null;
        for (String attributeName: LABELS) {
            if (attributeName.equals("StdBooks")) commonAttributesDAO = new DynamicBooksDAO();
            else if (attributeName.equals("StdFilms")) commonAttributesDAO = new DynamicFilmsDAO();
            else if (attributeName.equals("StdFood")) commonAttributesDAO = new DynamicFoodDAO();
            else if (attributeName.equals("StdLocation")) commonAttributesDAO = new DynamicLocationDAO();
            else if (attributeName.equals("StdSports")) commonAttributesDAO = new DynamicSportsDAO();
            else throw new WrongAttributeNameException("Attribute " + attributeName + " is not valid");

            List<CompareNode> keepList = matchForOneTypeOfLabel(userPerson, commonAttributesDAO, userDAO,
                    genderPreference, targetGenderOrientation);
            if (remain != null) {
                // take intersection of the old and new
                Set<CompareNode> intersection = new HashSet<>(remain);
                intersection.retainAll(keepList);

                // also take the union
                Set<CompareNode> union = new HashSet<>(remain);
                union.addAll(keepList);

                // for each in the intersection, we add one mark to itself
                for (CompareNode each: intersection) {
                    if (union.contains(each)) each.addMark();
                }

                // sort the union
                remain = new ArrayList<>(union);
                Collections.sort(remain);

                remain = remain.subList(0, 20);
            } else {
                remain = keepList;
            }
            // close DAO
            commonAttributesDAO.close();  // very important
        }

        // close DAO
        userDAO.close();

        // sort the total mark
        Collections.sort(remain);

        // take the first 15 person
        remain = remain.subList(0, OUTPUT_LIMIT);
        return remain;
    }

    private List<CompareNode> matchForOneTypeOfLabel(UserPerson userPerson, UserCommonAttributesDAO commonAttributesDAO,
                                                     DynamicUserPersonDAO userDAO, String genderPreference,
                                                     String targetGenderOrientation) {
        List<LabelObject> thisUsersAttributes = commonAttributesDAO.getAllValuesWithUserID(userPerson.getUserID());
        List<CompareNode> remain = null;
        for (LabelObject eachObj: thisUsersAttributes) {
            List<LabelObject> allUsersWithThisLabel = new ArrayList<>(commonAttributesDAO.getAllValuesWithLabelID(eachObj.getLabelId()));
            List<CompareNode> keepList = new LinkedList<>();
            if (allUsersWithThisLabel.size() <= SEARCH_UP_LIMIT) {
                for (LabelObject each: allUsersWithThisLabel) {
                    UserPerson eachUser = userDAO.getUserPersonByUserID(each.getUserID());
                    if (each.getUserID() != userPerson.getUserID() && eachUser.getGender().equals(genderPreference)
                            && eachUser.getGenderOrientation().equals(targetGenderOrientation)) {
                        keepList.add(new CompareNode(eachUser, 1));
                    }
                }
            } else {
                // select random
                Collections.shuffle(allUsersWithThisLabel);
                for (int i = 0; i < SEARCH_UP_LIMIT; i++) {
                    UserPerson eachUser = userDAO.getUserPersonByUserID(allUsersWithThisLabel.get(i).getUserID());
                    if (eachUser.getUserID() != userPerson.getUserID() && eachUser.getGender().equals(genderPreference)
                            && eachUser.getGenderOrientation().equals(targetGenderOrientation)) {
                        keepList.add(new CompareNode(eachUser, 1));
                    }
                }
            }

            if (remain != null) {
                // take intersection of the old and new
                Set<CompareNode> intersection = new HashSet<>(remain);
                intersection.retainAll(keepList);

                // also take the union
                Set<CompareNode> union = new HashSet<>(remain);
                union.addAll(keepList);

                // for each in the intersection, we add one mark to itself
                for (CompareNode each: intersection) {
                    if (union.contains(each)) each.addMark();
                }

                // sort the union
                remain = new ArrayList<>(union);
                Collections.sort(remain);

                remain = remain.subList(0, SEARCH_UP_LIMIT);
            } else {
                remain = keepList;
            }
        }

        return remain;
    }
}
