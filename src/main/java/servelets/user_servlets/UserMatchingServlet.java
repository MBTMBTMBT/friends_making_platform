package servelets.user_servlets;

import database.dynamicDAOs.*;
import database.exceptions.WrongAttributeNameException;
import database.standarizedTables.LabelObject;
import database.supports.HibernateUtil;
import database.tables.Sports;
import database.tables.User;
import database.tables.UserPerson;
import org.hibernate.Session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@WebServlet("/userMatchingServlet")
public class UserMatchingServlet extends HttpServlet {
    private static final int SEARCH_UP_LIMIT = 20;
    private static final int OUTPUT_LIMIT = 15;
    private static final String[] LABELS = new String[] {"StdBooks", "StdFilms", "StdFood", "StdLocation", "StdSports"};

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String username = null;
        // String usernameMsg = null;
        int userID = -1;

        String searchedName = null;
        try {
            searchedName = request.getParameter("find_screenname");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            username = (String) request.getAttribute("user_username");
            // usernameMsg = (request.getAttribute("msg_username") != null) ? (String) request.getAttribute("msg_username"): "";
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

        List<CompareNode> nodes = new LinkedList<>();
        try {
            if (searchedName == null || searchedName.equals("")) nodes = matchLabelObjs(userPerson);
            else nodes = search(searchedName, userPerson);
        } catch (NullPointerException ignore) {
        }

        List<String> headIconList = new LinkedList<>();
        List<String> userNameList = new LinkedList<>();
        List<String> genderList = new LinkedList<>();
        List<String> ageList = new LinkedList<>();
        List<String> workList = new LinkedList<>();
        List<String> userIDList = new LinkedList<>();
        DynamicLabelsDAO labelsDAO = new DynamicLabelsDAO();
        for (CompareNode each: nodes) {
            UserPerson eachUserPerson = each.getUserPerson();
            headIconList.add(eachUserPerson.getHeadIcon());
            userNameList.add(eachUserPerson.getScreenName());
            genderList.add(eachUserPerson.getGender());
            ageList.add(String.valueOf(eachUserPerson.getAge()));
            workList.add(labelsDAO.getLabelsByKey(eachUserPerson.getWork()).getWork());
            userIDList.add(String.valueOf(eachUserPerson.getUserID()));
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
        request.setAttribute("user_username", username);
        request.setAttribute("user_id", userID);
        request.getRequestDispatcher("/recommendations.jsp").forward(request, response);
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

        public static List<CompareNode>[] mergeNodes(List<CompareNode> list1, List<CompareNode> list2) {
            if (list1 == null) list1 = new LinkedList<>();
            if (list2 == null) list2 = new LinkedList<>();
            Map<Integer, CompareNode> map1 = new HashMap<>(), map2 = new HashMap<>();
            for (CompareNode each: list1) map1.put(each.getUserPerson().getUserID(), each);
            for (CompareNode each: list2) map2.put(each.getUserPerson().getUserID(), each);
            for (int eachKey: map1.keySet()) {
                if (map2.keySet().contains(eachKey)) {
                    int mark1 = map1.get(eachKey).getMark();
                    int mark2 = map2.get(eachKey).getMark();
                    if (mark1 >= mark2) {
                        map2.replace(eachKey, map1.get(eachKey));
                    } else {
                        map1.replace(eachKey, map2.get(eachKey));
                    }
                }
            }
            list1 = new LinkedList<>();
            list2 = new LinkedList<>();
            for (int eachKey: map1.keySet()) list1.add(map1.get(eachKey));
            for (int eachKey: map2.keySet()) list2.add(map2.get(eachKey));
            return new List[] {list1, list2};
        }
    }

    private static List<CompareNode> search(String searched, UserPerson thisPerson) {
        DynamicUserPersonDAO userPersonDAO = new DynamicUserPersonDAO();
        List<UserPerson> rst = userPersonDAO.userPersonSearch(searched);
        if (rst != null && rst.size() > OUTPUT_LIMIT) {
            rst = rst.subList(0, OUTPUT_LIMIT);
        }

        List<CompareNode> output = new LinkedList<>();
        if (rst != null) {
            String expectedGender = null;
            String genderOrient = null;
            if (thisPerson.getGenderOrientation() == null) {
                genderOrient = "hetero";
                if (thisPerson.getGender().equals("female")) {
                    expectedGender = "male";
                } else {
                    expectedGender = "female";
                }
            } else {
                if (thisPerson.getGender().equals("female")) {
                    if (thisPerson.getGenderOrientation().equals("hetero")) {
                        expectedGender = "male";
                        genderOrient = "hetero";
                    } else {
                        expectedGender = "female";
                        genderOrient = "homosexual";
                    }
                } else {
                    if (thisPerson.getGenderOrientation().equals("hetero")) {
                        expectedGender = "female";
                        genderOrient = "hetero";
                    } else {
                        expectedGender = "male";
                        genderOrient = "homosexual";
                    }
                }
            }
            for (UserPerson each : rst) {
                if (each.getGender().equals(expectedGender) && each.getGenderOrientation().equals(genderOrient))
                    output.add(new CompareNode(each, 1));
            }
        }
        return output;
    }

    private static List<CompareNode> matchLabelObjs(UserPerson userPerson) {
        String genderPreference;
        String targetGenderOrientation;
        if (userPerson.getGenderOrientation() == null) {
            targetGenderOrientation = "hetero";
            if (userPerson.getGender().equals("female")) {
                genderPreference = "male";
            } else {
                genderPreference = "female";
            }
        } else {
            if (userPerson.getGender().equals("female")) {
                if (userPerson.getGenderOrientation().equals("hetero")) {
                    genderPreference = "male";
                    targetGenderOrientation = "hetero";
                } else {
                    genderPreference = "female";
                    targetGenderOrientation = "homosexual";
                }
            } else {
                if (userPerson.getGenderOrientation().equals("hetero")) {
                    genderPreference = "female";
                    targetGenderOrientation = "hetero";
                } else {
                    genderPreference = "male";
                    targetGenderOrientation = "homosexual";
                }
            }
        }

        DynamicUserPersonDAO userDAO = new DynamicUserPersonDAO();
        UserCommonAttributesDAO commonAttributesDAO;
        List<CompareNode> remain = null;

        shuffle(LABELS);

        for (String attributeName: LABELS) {
            switch (attributeName) {
                case "StdBooks":
                    commonAttributesDAO = new DynamicBooksDAO();
                    break;
                case "StdFilms":
                    commonAttributesDAO = new DynamicFilmsDAO();
                    break;
                case "StdFood":
                    commonAttributesDAO = new DynamicFoodDAO();
                    break;
                case "StdLocation":
                    commonAttributesDAO = new DynamicLocationDAO();
                    break;
                case "StdSports":
                    commonAttributesDAO = new DynamicSportsDAO();
                    break;
                default:
                    throw new WrongAttributeNameException("Attribute " + attributeName + " is not valid");
            }

            List<CompareNode> keepList = matchForOneTypeOfLabel(userPerson, commonAttributesDAO, userDAO,
                    genderPreference, targetGenderOrientation);
            if (remain != null) {
                // merge nodes from two lists
                List<CompareNode>[] lists = CompareNode.mergeNodes(remain, keepList);
                remain = lists[0]; keepList = lists[1];

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

                if (remain.size() > SEARCH_UP_LIMIT) remain = remain.subList(0, SEARCH_UP_LIMIT);
            } else {
                remain = keepList;
            }
            // close DAO
            commonAttributesDAO.close();  // very important
        }

        // close DAO
        userDAO.close();

        // sort the total mark
        try {
            Collections.sort(remain);
        } catch (Exception ignore) {
        }

        // take the first 15 person
        if (remain.size() > OUTPUT_LIMIT) remain = remain.subList(0, OUTPUT_LIMIT);
        return remain;
    }

    private static List<CompareNode> matchForOneTypeOfLabel(UserPerson userPerson, UserCommonAttributesDAO commonAttributesDAO,
                                                     DynamicUserPersonDAO userDAO, String genderPreference,
                                                     String targetGenderOrientation) {
        List<LabelObject> thisUsersAttributes = commonAttributesDAO.getAllValuesWithUserID(userPerson.getUserID());
        List<CompareNode> remain = null;
        for (LabelObject eachObj: thisUsersAttributes) {
            if (eachObj.getLabelId() == 1) continue;  // we don't consider "not selected"
            List<LabelObject> allUsersWithThisLabel = commonAttributesDAO.getAllValuesWithLabelID(eachObj.getLabelId());  // new ArrayList<>(commonAttributesDAO.getAllValuesWithLabelID(eachObj.getLabelId()));
            // System.out.println(allUsersWithThisLabel.size());
            // for (LabelObject each: allUsersWithThisLabel) System.out.println(each.getUserID());
            List<CompareNode> keepList = new LinkedList<>();
            if (allUsersWithThisLabel.size() <= SEARCH_UP_LIMIT) {
                for (LabelObject each: allUsersWithThisLabel) {
                    UserPerson eachUser = userDAO.getUserPersonByUserID(each.getUserID());
                    // System.out.println("looking for user " + eachUser.getScreenName());
                    if (each.getUserID() != userPerson.getUserID() && eachUser.getGender().equals(genderPreference)
                            && eachUser.getGenderOrientation().equals(targetGenderOrientation)) {
                        System.out.println("add in");
                        keepList.add(new CompareNode(eachUser, 1));
                    }
                }
            } else {
                // select random
                Collections.shuffle(allUsersWithThisLabel);
                for (int i = 0; i < SEARCH_UP_LIMIT; i++) {
                    UserPerson eachUser = userDAO.getUserPersonByUserID(allUsersWithThisLabel.get(i).getUserID());
                    System.out.println("looking for user " + eachUser.getScreenName());
                    if (eachUser.getUserID() != userPerson.getUserID() && eachUser.getGender().equals(genderPreference)
                            && eachUser.getGenderOrientation().equals(targetGenderOrientation)) {
                        System.out.println("add in");
                        keepList.add(new CompareNode(eachUser, 1));
                    }
                }
            }

            if (remain != null) {
                // merge nodes from two lists
                List<CompareNode>[] lists = CompareNode.mergeNodes(remain, keepList);
                remain = lists[0]; keepList = lists[1];

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

                if (remain.size() > SEARCH_UP_LIMIT) remain = remain.subList(0, SEARCH_UP_LIMIT);
            } else {
                remain = keepList;
            }
        }

        return remain;
    }

    private static <T> void swap(T[] a, int i, int j){
        T temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    // idea borrowed from https://blog.csdn.net/qq_33256688/article/details/80304048
    private static <T> void shuffle(T[] arr) {
        Random rand = new Random();
        int length = arr.length;
        for ( int i = length; i > 0; i-- ){
            int randInd = rand.nextInt(i);
            swap(arr, randInd, i - 1);
        }
    }

    public static void main(String[] args) {
        DynamicUserPersonDAO userPersonDAO = new DynamicUserPersonDAO();
        UserPerson userPerson = userPersonDAO.getUserPersonByScreenName("MBT");
        userPersonDAO.close();

        List<CompareNode> rst = matchLabelObjs(userPerson);
        // System.out.println(rst.size());
        for (CompareNode each: rst) {
            System.out.println(each.getUserPerson().toString());
        }
    }
}
