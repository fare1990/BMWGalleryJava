package servlets;

import entity.User;
import org.springframework.web.context.support.WebApplicationContextUtils;
import services.ImageService;
import services.UserService;
import view.MainView;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by Fare on 15.07.2016.
 */

//@WebServlet("/home")
public class MainServlet extends HttpServlet {

    private UserService userService;
    private ImageService imageService;

    /*public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
    }*/

    /*public MainServlet(){
        this.userService = AppContext.getInstance().get(UserService.class);
        this.imageService = AppContext.getInstance().get(ImageService.class);
    }*/

    @Override
    public void init(ServletConfig config){
        userService = WebApplicationContextUtils.
                getRequiredWebApplicationContext(config.getServletContext()).
                getBean(UserService.class);
        imageService = WebApplicationContextUtils.
                getRequiredWebApplicationContext(config.getServletContext()).
                getBean(ImageService.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String gallname = request.getParameter("galleryname"); //todo:check for empty
        User user = (User)request.getAttribute("user");
        if(user != null) {
            userService.setGalleryName(user, gallname);
            response.sendRedirect("/home");
        }


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        String headerText = "Welcome to the BMW Gallery!";
        request.setAttribute("textH1", headerText);
        User user = (User)request.getAttribute("user");
        MainView view = new MainView();
        if (user != null) {
            view.setLoginFormVisible(false);

            view.setUserName(user.getEmail());
            String galleryName = user.getGalleryName();
            if(!galleryName.equals("none")) {
                view.setUserGalleryVisible(true);
                view.setUserGalleryName(galleryName);
                view.setUserGalleryFirstImagePath(imageService.getFirstUserImagePath(user.getUserId()));
            }
            else {
                view.setUserGalleryVisible(false);
            }

        } else {
            view.setLoginFormVisible(true);
            view.setUserGalleryVisible(false);
            view.setUserName("Already have an account?");
        }

        request.setAttribute("view", view);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.html");
        dispatcher.forward(request, response);
    }

}
