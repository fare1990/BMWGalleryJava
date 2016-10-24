package services;

import database.CommentsDAO;
import database.DataBaseProvider;
import database.ImageDAO;
import database.UserDAO;

/**
 * Created by Fare on 02.09.2016.
 */
public class InitProcessor {

    private CommentsDAO commentsDAO;
    private ImageDAO imageDAO;
    private UserDAO userDAO;
    private DataBaseProvider provider;

    public InitProcessor (CommentsDAO commentsDAO, ImageDAO imageDAO, UserDAO userDAO, DataBaseProvider dataBaseProvider) {
        this.commentsDAO = commentsDAO;
        this.imageDAO = imageDAO;
        this.userDAO = userDAO;
        this.provider = dataBaseProvider;
    }

    public void checkTables () {
        if (!provider.checkTable("BB_USERS")) {
            userDAO.createTableUsers();
        }
        if (!provider.checkTable("BB_IMAGES")) {
            imageDAO.createTableImages();
        }
        if (!provider.checkTable("BB_COMMENTS")) {
            commentsDAO.createTableComments();
        }
    }

}
