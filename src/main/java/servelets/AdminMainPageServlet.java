package servelets;

import database.daos.AdministractorDAO;
import database.tables.Administractor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@WebServlet("/adminMainPageServlet")
public class AdminMainPageServlet extends HttpServlet {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        Admin admin = AdministractorDAO.getAdminByID((Integer) request.getAttribute("admin_id"));
        String surname = admin.getFamilyname();
        String forename = admin.getFirstname();
        int id = admin.getId();
        request.setAttribute("id", id);
        request.setAttribute("admin_surname", surname);
        request.setAttribute("admin_forename", forename);
        List<Admin> adminList = AdministractorDAO.getAllAdmins();
        List<Integer> adminIDList = new LinkedList<>();
        List<String> adminSurnamesList = new LinkedList<>();
        List<String> adminForenamesList = new LinkedList<>();
        List<String> adminGenderList = new LinkedList<>();
        for (Admin adminNext : adminList) {
            adminIDList.add(adminNext.getId());
            adminSurnamesList.add(adminNext.getFamilyname());
            adminForenamesList.add(adminNext.getFirstname());
            adminGenderList.add(adminNext.getGender());
        }
        request.setAttribute("admin_id_list", adminIDList);
        request.setAttribute("admin_surnames_list", adminSurnamesList);
        request.setAttribute("admin_forenames_list", adminForenamesList);
        request.setAttribute("admin_gender_list", adminGenderList);
        request.getRequestDispatcher("/admin_interface.jsp").forward(request, response);

         */
    }
}
