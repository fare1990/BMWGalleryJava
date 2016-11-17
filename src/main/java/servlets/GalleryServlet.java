package servlets;

import entity.User;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import services.ImageService;
import view.GalleryView;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Fare on 27.07.2016.
 */

@WebServlet("/gallery")
public class GalleryServlet extends HttpServlet {

    private ImageService imageService;

    /*public GalleryServlet() {
        this.imageService = AppContext.getInstance().get(ImageService.class);
    }*/

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        imageService = WebApplicationContextUtils
                .getRequiredWebApplicationContext(config.getServletContext()).getBean(ImageService.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        super.doPost(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        User user = (User)request.getAttribute("user");
        GalleryView view = new GalleryView();

        if (user != null) {
            view.setLoginFormVisible(false);
            view.setLogoutFormVisible(true);
            view.setUserName(user.getEmail());

            String galleryType = request.getParameter("name");
            view.setImagelist(imageService.getGallery(galleryType, user.getUserId()));
            if (!user.getGalleryName().equals("none")) {

                if (!galleryType.equals("user")) {
                    view.setImageAddFormVisible(false);
                    view.setUserGalleryName(galleryType);
                } else {
                    view.setUserGalleryName(user.getGalleryName());
                    view.setImageAddFormVisible(true);
                }
            }
            else {
                view.setImageAddFormVisible(false);
                view.setUserGalleryName(galleryType);
            }

        } else {
            view.setLoginFormVisible(true);
            view.setLogoutFormVisible(false);
            view.setUserName("Already have an account?");
            view.setImageAddFormVisible(false);
            view.setUserGalleryName("You need to login to see gallery images!");
        }

        request.setAttribute("view", view);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/gallery.jsp");
        dispatcher.forward(request, response);
    }

}
