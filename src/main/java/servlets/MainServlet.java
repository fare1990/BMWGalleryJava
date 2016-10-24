package servlets;

import entity.User;
import services.ApplicationContext;
import services.ImageService;
import services.InitProcessor;
import services.UserService;
import view.MainView;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by Fare on 15.07.2016.
 */

@WebServlet("/home")
public class MainServlet extends HttpServlet {

    private UserService userService;
    private ImageService imageService;

    public MainServlet(){
        this.userService = ApplicationContext.getInstance().get(UserService.class);
        this.imageService = ApplicationContext.getInstance().get(ImageService.class);
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
            view.setLogoutFormVisible(true);
            view.setUserName(user.getEmail());
            String galleryName = user.getGalleryName();
            if(!galleryName.equals("none")) {
                view.setGalleryCreateFormVisible(false);
                view.setUserGalleryVisible(true);
                view.setUserGalleryName(galleryName);
                view.setUserGalleryFirstImagePath(imageService.getFirstUserImagePath(user.getUserid()));
            }
            else {
                view.setGalleryCreateFormVisible(true);
                view.setUserGalleryVisible(false);
            }

        } else {
            view.setLoginFormVisible(true);
            view.setGalleryCreateFormVisible(false);
            view.setUserGalleryVisible(false);
            view.setUserName("Already have an account?");
            view.setLogoutFormVisible(false);
        }

        request.setAttribute("view", view);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }

}
