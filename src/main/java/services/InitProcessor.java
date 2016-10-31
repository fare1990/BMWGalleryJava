package services;

import database.JDBCCommentsDAO;
import database.DataBaseProvider;
import database.JDBCImageDAO;
import database.JDBCUserDAO;

/**
 * Created by Fare on 02.09.2016.
 */
public class InitProcessor {

    private JDBCCommentsDAO JDBCCommentsDAO;
    private JDBCImageDAO JDBCImageDAO;
    private JDBCUserDAO JDBCUserDAO;
    private DataBaseProvider provider;

    public InitProcessor (JDBCCommentsDAO JDBCCommentsDAO, JDBCImageDAO JDBCImageDAO, JDBCUserDAO JDBCUserDAO, DataBaseProvider dataBaseProvider) {
        this.JDBCCommentsDAO = JDBCCommentsDAO;
        this.JDBCImageDAO = JDBCImageDAO;
        this.JDBCUserDAO = JDBCUserDAO;
        this.provider = dataBaseProvider;
    }

    public void checkTables () {
        if (!provider.checkTable("BB_USERS")) {
            JDBCUserDAO.createTableUsers();
        }
        if (!provider.checkTable("BB_IMAGES")) {
            JDBCImageDAO.createTableImages();
        }
        if (!provider.checkTable("BB_COMMENTS")) {
            JDBCCommentsDAO.createTableComments();
        }
    }

}
