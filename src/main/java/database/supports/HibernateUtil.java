package database.supports;

import java.util.Properties;

import database.tables.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	

	private static SessionFactory sessionFactory;
	
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/groupwork?useSSL=false");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/groupwork?serverTimezone=UTC&characterEncoding=utf-8");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "premacybenny");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                configuration.setProperties(settings);
                configuration.addAnnotatedClass(Person.class);
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Employee.class);
                configuration.addAnnotatedClass(Administractor.class);
                configuration.addAnnotatedClass(Date.class);
                configuration.addAnnotatedClass(Event.class);
                configuration.addAnnotatedClass(EventLocation.class);
                configuration.addAnnotatedClass(PsychologicalMentor.class);
                
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}


