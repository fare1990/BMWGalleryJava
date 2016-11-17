package servlets;

import entity.User;
import org.springframework.web.context.support.WebApplicationContextUtils;
import services.ImageService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;


/**
 * Created by Fare on 29.07.2016.
 */

@WebServlet ("/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {

    private ImageService imageService;

    /*public UploadServlet (){
        this.imageService = AppContext.getInstance().get(ImageService.class);
    }*/

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        imageService = WebApplicationContextUtils
                .getRequiredWebApplicationContext(config.getServletContext()).getBean(ImageService.class);
    }

    //todo: fix hardcoded dir!!!
    private static final String UPLOAD_DIR = "C:\\Users\\Fare\\BMWGalleryJava\\src\\main\\webapp\\users_images\\";
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String description = request.getParameter("description"); // Retrieves <input type="text" name="description">
        Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
        String fileName = getSubmittedFileName(filePart);//todo:need checking for image file type

        saveFile(filePart, fileName);

        User user = (User)request.getAttribute("user");
        imageService.addImage(user.getUserId(),description,"users_images/"+fileName); //todo:do this well!!!
        response.sendRedirect("/gallery?name=user");

    }

    private void saveFile (Part filePart, String fileName) throws IOException {
        if (filePart != null){
            InputStream fileContent = filePart.getInputStream();
            File target = new File(UPLOAD_DIR+fileName);
            OutputStream outputStream = new FileOutputStream(target);
            byte[] buffer = new byte[8*1024];
            int bytesRead;
            while((bytesRead = fileContent.read(buffer)) != -1)
                outputStream.write(buffer,0,bytesRead);
            fileContent.close();
            outputStream.close();
        }
    }

    private static String getSubmittedFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        super.doGet(request,response);
    }
}
