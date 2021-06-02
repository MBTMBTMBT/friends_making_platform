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
