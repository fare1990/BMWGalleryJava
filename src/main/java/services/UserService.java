package services;

import database.UserDAO;
import entity.User;
import security.PasswordManager;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;


/**
 * Created by Fare on 21.07.2016.
 */
public class UserService {

    private PasswordManager passwordManager;
    private UserDAO userDAO;

    public UserService(PasswordManager passwordManager, UserDAO userDAO) {
        this.passwordManager = passwordManager;
        this.userDAO = userDAO;
    }

    public boolean checkLogin(String email, String password) {
        String existingPassword = getUserByEmail(email).getPassword();
        return (existingPassword != null && passwordManager.comparePasswordHex(existingPassword, password));
    }

    public void createUser(String name, String email, String password) {
        User user = new User();
        user.setUsername(name);
        user.setEmail(email);
        user.setPassword(passwordManager.getHexString(password));
        user.setGalleryName("none");
        user.setCreatedDate(new Date());
        userDAO.addUser(user);
    }

    public void setGalleryName (User user, String galleryName){
        user.setGalleryName(galleryName);
        userDAO.updateUser(user);
    }

    public User getUserByEmail (String email){
        return userDAO.getUser(email);
    }

}
