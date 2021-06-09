package database.dynamicDAOs;

import database.supports.HibernateUtil;
import database.supports.JDBCTool;
import org.hibernate.Session;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DynamicDAO {
    protected Session session;
    protected Connection connection;
    protected Statement statement;

    public DynamicDAO() {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            connection = JDBCTool.getConnection();
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            session.close();
            statement.close();
            connection.close();
            session = null;
            statement = null;
            connection = null;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (NullPointerException ignore) {
        }
    }
}
