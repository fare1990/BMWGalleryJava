package controllers;

import entity.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import services.ImageService;
import services.UserService;
import view.MainView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Fare on 11.11.2016.
 */

@RestController
public class MainpageController {

    private final static Logger logger = Logger.getLogger(MainpageController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;

    public MainpageController (){
        logger.error("in controller constructor!!!!");
    }

    @RequestMapping(path = "/home", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public MainView getView(HttpServletRequest request) {
        MainView view = new MainView();
        view.setHeaderText("Welcome to the BMW Gallery!");
        User user = (User)request.getAttribute("user");
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

        return view;
    }

    @RequestMapping(path = "/home", method = RequestMethod.POST, consumes = MediaType.TEXT_PLAIN_VALUE)
    public void setUserGalleryName(@RequestBody String gallName, HttpServletRequest request) {
        User user = (User)request.getAttribute("user");
        if(user != null) {
            userService.setGalleryName(user, gallName);
        }
    }
}
