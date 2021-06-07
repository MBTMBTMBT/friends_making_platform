package servelets;

import database.daos.PersonDAO;
import database.dynamicDAOs.DynamicUserPersonDAO;
import database.tables.Person;
import database.tables.UserPerson;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/uploadHeadIconServlet")
public class UploadHeadIconServlet extends HttpServlet {
    // idea borrowed from https://www.cnblogs.com/xiaotaoqi/p/5856044.html
    // and https://www.runoob.com/jsp/jsp-file-uploading.html

    // notation by Benteng Ma:
    // this method requires commons-fileupload.x.x.jar and commons-io-x.x.jar
    // download at http://static.runoob.com/download/commons-fileupload-1.3.2.jar
    // and http://static.runoob.com/download/commons-io-2.5.jar

    // Upload target dir
    private static final String UPLOAD_DIRECTORY = "D:/MyFiles/PROGRAMS/java/comp2004jgroupwork/src/main/webapp";
    private static final String UPLOAD_DIRECTORY_RELATIVE = "static/images";

    // upload limits
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = null;
        String usernameMsg = null;
        int userID = -1;

        try {
            username = (String) request.getAttribute("user_username");
            userID = (int) request.getAttribute("user_id");
            usernameMsg = (request.getAttribute("msg_username") != null) ? (String) request.getAttribute("msg_username") : "";
        } catch (NullPointerException ignore) {
        }

        if (username == null || userID == -1) {
            HttpSession session = request.getSession();
            username = (String) session.getAttribute("user_username");
            userID = (int) session.getAttribute("user_id");
        }

        DynamicUserPersonDAO userPersonDAO = new DynamicUserPersonDAO();
        UserPerson userPerson = userPersonDAO.getUserPersonByUserID(userID);
        userPersonDAO.close();  // always close it

        // configure upload limits
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // set temp repository
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(MAX_FILE_SIZE);
        upload.setSizeMax(MAX_REQUEST_SIZE);

        // allows to accept names with different languages
        upload.setHeaderEncoding("UTF-8");

        // configure the saved path
        String uploadPath = getServletContext().getRealPath("/") + File.separator + UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        try {
            // extract the file data
            List<FileItem> formItems = upload.parseRequest(request);
            // System.out.println("a");

            if (formItems != null && formItems.size() > 0) {
                // System.out.println("b");
                FileItem item = formItems.get(0);
                // see if this is a form of file
                if (!item.isFormField()) {
                    // System.out.println("c");
                    // I don't know why, but as I've tested, name of image can not contain " " and "_",
                    // otherwise I get 404
                    String originalFileName = new File(item.getName()).getName();
                    String[] suffixArr = originalFileName.split("\\.");
                    if (!suffixArr[suffixArr.length - 1].equals("")) {
                        // System.out.println("d");
                        // System.out.println(suffixArr[suffixArr.length - 1]);
                        String suffix = "." + suffixArr[suffixArr.length - 1];
                        String fileName = (username + "headicon").replaceAll("[\\s*\\t\\n\\r]", "");

                        Person person = PersonDAO.getPersonByScreenName(username);
                        assert person != null;

                        // this if for double updating
                        // I hope buffer of tomcat would flush faster
                        // "I wish."
                        int bufferFlag = 1;

                        String[] split = person.getHeadIcon().split("/");
                        String last = "1" + split[split.length - 1].substring(1);
                        String finalFileName = "";
                        for (int i = 0; i < split.length - 2; i++) finalFileName += split[i];
                        finalFileName += "/images/" + last;
                        File deleteFile = new File(UPLOAD_DIRECTORY + "/" + finalFileName);
                        System.out.println("Check " + UPLOAD_DIRECTORY + "/" + finalFileName);
                        if (deleteFile.exists()) {
                            System.out.println("Delete " + UPLOAD_DIRECTORY + "/" + finalFileName);
                            deleteFile.delete();
                            bufferFlag = 2;
                        }

                        //D:/MyFiles/PROGRAMS/java/comp2004jgroupwork/src/main/webapp/static/1MBTheadicon.jpg
                        //D:\MyFiles\PROGRAMS\java\comp2004jgroupwork\src\main\webapp\static\images\1MBTheadicon.jpg

                        split = person.getHeadIcon().split("/");
                        last = "2" + split[split.length - 1].substring(1);
                        finalFileName = "";
                        for (int i = 0; i < split.length - 2; i++) finalFileName += split[i];
                        finalFileName += "/images/" + last;
                        deleteFile = new File(UPLOAD_DIRECTORY + "/" + finalFileName);
                        System.out.println("Check " + UPLOAD_DIRECTORY + "/" + finalFileName);
                        if (deleteFile.exists()) {
                            System.out.println("Delete " + UPLOAD_DIRECTORY + "/" + finalFileName);
                            deleteFile.delete();
                            bufferFlag = 1;
                        }

                        String filePath = UPLOAD_DIRECTORY + "/" + UPLOAD_DIRECTORY_RELATIVE + "/" + bufferFlag + fileName + suffix;
                        System.out.println(filePath);

                        File storeFile = new File(filePath);
                        item.write(storeFile);
                        person.setHeadIcon(UPLOAD_DIRECTORY_RELATIVE + "/" + bufferFlag + fileName + suffix);
                        // System.out.println("1." + filePath);
                        // System.out.println("2." + UPLOAD_DIRECTORY_RELATIVE + "/" + fileName);
                        PersonDAO.updatePerson(person);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("img_error",
                    "update fail:" + ex.getMessage());
        }

        getServletContext().getRequestDispatcher("/userMainPageServlet").forward(request, response);
    }
}
