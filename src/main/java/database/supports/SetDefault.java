package database.supports;

import database.daos.*;
import database.tables.*;

public class SetDefault {
    public static void setDefaultMentor() {
        try {
            // we will need a default mentor for users
            // check if this default mentor exists
            PsychologicalMentor defaultMentor = PsychologicalMentorDAO.getPhsycologicalMentorByID(1);
            if (defaultMentor == null) {
                String defaultMentorName = "No mentor";
                Person person = new Person();
                person.setpassword("mentordefault");
                person.setGender("female");
                person.setForename("default");
                person.setSurname("mentor");
                person.setScreenName(defaultMentorName);
                PersonDAO.savePerson(person);
                person = PersonDAO.getPersonByScreenName(defaultMentorName);
                assert person != null;
                int systemID = person.getSystemID();

                Employee employee = new Employee();
                employee.setSystemID(systemID);
                EmployeeDAO.saveEmployee(employee);
                int employeeNum = EmployeeDAO.getEmployeeBySystemID(systemID).getEmployeeID();

                PsychologicalMentor mentor = new PsychologicalMentor();
                mentor.setMentorNumber(1);
                mentor.setSystemID(systemID);
                mentor.setEmployeeID(employeeNum);
                PsychologicalMentorDAO.savePhsycologicalMentor(mentor);
            }
        } catch (Exception exception) {
            System.out.println("meet an error when setting default mentor");
            exception.printStackTrace();
        }
    }

    public static void setDefaultAdmin() {
        try {
            // we also need a default admin
            // check if this default mentor exists
            Administrator administrator = AdministractorDAO.getAdministractorByID(1);
            if (administrator == null) {
                String defaultAdminName = "Admin 1";
                Person person = new Person();
                person.setpassword("administrator");
                person.setGender("male");
                person.setForename("Benteng");
                person.setSurname("Ma");
                person.setScreenName(defaultAdminName);
                PersonDAO.savePerson(person);
                person = PersonDAO.getPersonByScreenName(defaultAdminName);
                assert person != null;
                int systemID = person.getSystemID();

                Employee employee = new Employee();
                employee.setSystemID(systemID);
                EmployeeDAO.saveEmployee(employee);
                int employeeNum = EmployeeDAO.getEmployeeBySystemID(systemID).getEmployeeID();

                Administrator admin = new Administrator();
                admin.setAdminNumber(1);
                admin.setSystemID(systemID);
                admin.setEmployeeID(employeeNum);
                AdministractorDAO.saveAdministrator(admin);
            }
        } catch (Exception exception) {
            System.out.println("meet an error when setting default mentor");
            exception.printStackTrace();
        }
    }

    public static void setDefaultLabels() {
        try {
            // we hope there's one line default values for all the labels
            // check if it exists
            Labels labels = LabelsDAO.getLabelsByKey(1);
            if (labels == null) {
                labels = new Labels();
                labels.setSerial(1);
                labels.setActivity("not selected");
                labels.setBook("not selected");
                labels.setFilm("not selected");
                labels.setFood("not selected");
                labels.setLocations("not selected");
                labels.setSport("not selected");
                labels.setWork("not selected");
                LabelsDAO.saveLabels(labels);
            }
        } catch (Exception exception) {
            System.out.println("meet an error when setting default labels");
            exception.printStackTrace();
        }
    }
}
