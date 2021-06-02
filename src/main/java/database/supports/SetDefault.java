package database.supports;

import database.daos.EmployeeDAO;
import database.daos.LabelsDAO;
import database.daos.PersonDAO;
import database.daos.PsychologicalMentorDAO;
import database.tables.Employee;
import database.tables.Labels;
import database.tables.Person;
import database.tables.PsychologicalMentor;

public class SetDefault {
    public static void setDefaultMentor() {
        try {
            // we will need a default mentor for users
            // check if this default mentor exists
            PsychologicalMentor defaultMentor = PsychologicalMentorDAO.getPhsycologicalMentorByID(0);
            if (defaultMentor == null) {
                String defaultMentorName = "mentordefault";
                Person person = new Person();
                person.setpassword(defaultMentorName);
                person.setGender("female");
                person.setForename("default");
                person.setSurname("mentor");
                person.setScreenName("No mentor");
                PersonDAO.savePerson(person);
                person = PersonDAO.getPersonByScreenName(defaultMentorName);
                assert person != null;
                int systemID = person.getSystemID();

                Employee employee = new Employee();
                employee.setSystemID(systemID);
                EmployeeDAO.saveEmployee(employee);
                int employeeNum = EmployeeDAO.getEmployeeBySystemID(systemID).getEmployeeID();

                PsychologicalMentor mentor = new PsychologicalMentor();
                mentor.setMentorNumber(0);
                mentor.setSystemID(systemID);
                mentor.setEmployeeID(employeeNum);
                PsychologicalMentorDAO.savePhsycologicalMentor(mentor);
            }
        } catch (Exception ignore) {
            System.out.println("meet an error when setting default mentor");
        }
    }

    public static void setDefaultLabels() {
        try {
            // we hope there's one line default values for all the labels
            // check if it exists
            Labels labels = LabelsDAO.getLabelsByKey(0);
            if (labels == null) {
                labels = new Labels();
                labels.setSerial(0);
                labels.setActivity("not selected");
                labels.setBook("not selected");
                labels.setFilm("not selected");
                labels.setFood("not selected");
                labels.setLocations("not selected");
                labels.setSport("not selected");
                labels.setWork("not selected");
                LabelsDAO.saveLabels(labels);
            }
        } catch (Exception ignore) {
            System.out.println("meet an error when setting default labels");
        }
    }
}