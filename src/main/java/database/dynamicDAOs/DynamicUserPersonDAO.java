package database.dynamicDAOs;

import database.tables.UserPerson;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DynamicUserPersonDAO extends DynamicDAO {
    public DynamicUserPersonDAO() {
        super();
    }

    public List<UserPerson> userPersonJoin() {
        List<UserPerson> list = new LinkedList<UserPerson>();
        try {
            String sql = "SELECT * FROM user inner join person on user.SystemID = person.SystemID";
            System.out.println(sql);
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                int systemID = rs.getInt("SystemID");
                int userID = rs.getInt("UserID");
                String emailAddress = rs.getString("Emailaddress");
                String wechat = rs.getString("Wechat");
                String genderOrientation = rs.getString("GenderOrientation");
                Date dataOfBirth = rs.getDate("DataOfBirth");
                String slogan = rs.getString("Slogan");
                int work = rs.getInt("Work");
                int mentorID = rs.getInt("MentorID");
                String surname = rs.getString("Surname");
                String forename = rs.getString("Forename");
                String gender = rs.getString("Gender");
                String screenName = rs.getString("ScreenName");
                String headIcon = rs.getString("HeadIcon");
                String password = rs.getString("password");

                UserPerson e = new UserPerson(systemID, userID, emailAddress, wechat, genderOrientation, dataOfBirth,
                        slogan, work, mentorID, surname, forename, gender, screenName, headIcon, password);
                list.add(e);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public UserPerson getUserPersonBySystemID(int systemID) {
        try {
            String columns = "person.SystemID, person.Surname, person.Forename, person.Gender, person.ScreenName," +
                    "person.HeadIcon, person.password, user.UserID, user.Emailaddress, user.Wechat, user.GenderOrientation, " +
                    "user.DataOfBirth, user.Slogan, user.Work, user.MentorID";
            String sql = "SELECT " + columns + " FROM user inner join person on user.SystemID = person.SystemID";
            sql = "SELECT * FROM (" + sql + ") as UserPerson WHERE SystemID = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, systemID);
            System.out.println(ps);
            ResultSet rs = ps.executeQuery();
            rs.next();

            int userID = rs.getInt("UserID");
            String emailAddress = rs.getString("Emailaddress");
            String wechat = rs.getString("Wechat");
            String genderOrientation = rs.getString("GenderOrientation");
            Date dataOfBirth = rs.getDate("DataOfBirth");
            String slogan = rs.getString("Slogan");
            int work = rs.getInt("Work");
            int mentorID = rs.getInt("MentorID");
            String surname = rs.getString("Surname");
            String forename = rs.getString("Forename");
            String gender = rs.getString("Gender");
            String screenName = rs.getString("ScreenName");
            String headIcon = rs.getString("HeadIcon");
            String password = rs.getString("password");

            ps.close();
            UserPerson userPerson = new UserPerson(systemID, userID, emailAddress, wechat, genderOrientation, dataOfBirth,
                    slogan, work, mentorID, surname, forename, gender, screenName, headIcon, password);
            return userPerson;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public UserPerson getUserPersonByUserID(int userID) {
        try {
            String columns = "person.SystemID, person.Surname, person.Forename, person.Gender, person.ScreenName," +
                    "person.HeadIcon, person.password, user.UserID, user.Emailaddress, user.Wechat, user.GenderOrientation, " +
                    "user.DataOfBirth, user.Slogan, user.Work, user.MentorID";
            String sql = "SELECT " + columns + " FROM user inner join person on user.SystemID = person.SystemID";
            sql = "SELECT * FROM (" + sql + ") as UserPerson WHERE UserID = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userID);
            System.out.println(ps);
            ResultSet rs = ps.executeQuery();
            rs.next();

            int systemID = rs.getInt("SystemID");
            String emailAddress = rs.getString("Emailaddress");
            String wechat = rs.getString("Wechat");
            String genderOrientation = rs.getString("GenderOrientation");
            Date dataOfBirth = rs.getDate("DataOfBirth");
            String slogan = rs.getString("Slogan");
            int work = rs.getInt("Work");
            int mentorID = rs.getInt("MentorID");
            String surname = rs.getString("Surname");
            String forename = rs.getString("Forename");
            String gender = rs.getString("Gender");
            String screenName = rs.getString("ScreenName");
            String headIcon = rs.getString("HeadIcon");
            String password = rs.getString("password");

            ps.close();
            UserPerson userPerson = new UserPerson(systemID, userID, emailAddress, wechat, genderOrientation, dataOfBirth,
                    slogan, work, mentorID, surname, forename, gender, screenName, headIcon, password);
            return userPerson;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public UserPerson getUserPersonByScreenName(String screenName) {
        try {
            String columns = "person.SystemID, person.Surname, person.Forename, person.Gender, person.ScreenName," +
                    "person.HeadIcon, person.password, user.UserID, user.Emailaddress, user.Wechat, user.GenderOrientation, " +
                    "user.DataOfBirth, user.Slogan, user.Work, user.MentorID";
            String sql = "SELECT " + columns + " FROM user inner join person on user.SystemID = person.SystemID";
            sql = "SELECT * FROM (" + sql + ") as UserPerson WHERE SystemID = " + screenName + ";";
            PreparedStatement ps = connection.prepareStatement(sql);
            System.out.println(ps);
            ResultSet rs = ps.executeQuery();
            rs.next();

            int userID = rs.getInt("UserID");
            int systemID = rs.getInt("SystemID");
            String emailAddress = rs.getString("Emailaddress");
            String wechat = rs.getString("Wechat");
            String genderOrientation = rs.getString("GenderOrientation");
            Date dataOfBirth = rs.getDate("DataOfBirth");
            String slogan = rs.getString("Slogan");
            int work = rs.getInt("Work");
            int mentorID = rs.getInt("MentorID");
            String surname = rs.getString("Surname");
            String forename = rs.getString("Forename");
            String gender = rs.getString("Gender");
            String headIcon = rs.getString("HeadIcon");
            String password = rs.getString("password");

            ps.close();
            UserPerson userPerson = new UserPerson(systemID, userID, emailAddress, wechat, genderOrientation, dataOfBirth,
                    slogan, work, mentorID, surname, forename, gender, screenName, headIcon, password);
            return userPerson;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        DynamicUserPersonDAO userPersonDAO = new DynamicUserPersonDAO();
        System.out.println(userPersonDAO.getUserPersonBySystemID(208));
        userPersonDAO.close();
    }
}
