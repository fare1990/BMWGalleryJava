package services;

import database.*;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import security.PasswordManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Fare on 17.10.2016.
 */
public class ApplicationContext {

    private static final ApplicationContext APPLICATION_CONTEXT = new ApplicationContext();
    private final static Logger logger = Logger.getLogger(ApplicationContext.class);
    //private static DBConnectionPool dbConnectionPool;
    private static UserService userService;
    private static ImageService imageService;
    private static CommentsService commentsService;
    private static SessionFactory sessionFactory;

    private ApplicationContext() {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream input = classLoader.getResourceAsStream("bmwgallery.properties");
            Properties properties = new Properties();
            properties.load(input);
            sessionFactory = new Configuration()
                    .addResource("User.hbm.xml")
                    .addResource("Image.hbm.xml")
                    .addResource("Comment.hbm.xml")
                    .setProperties(System.getProperties())
                    .configure()
                    .buildSessionFactory();
            /*dbConnectionPool = new DBConnectionPool(properties.getProperty("CONNECTION_URL"),
                    properties.getProperty("DB_DRIVER"),
                    properties.getProperty("DB_USER"),
                    properties.getProperty("DB_PASSWORD"),
                    Integer.parseInt(properties.getProperty("CONN_COUNT")));*/
        } catch (IOException e) {
            logger.error("Error while creating ApplicationContext", e);
        }
    }

    public static ApplicationContext getInstance() {
        return APPLICATION_CONTEXT;
    }

    private UserService getUserService() {
        if (userService == null) {
            userService = new UserService(new PasswordManager(), new UserDAO(sessionFactory));
        }
        return userService;
    }

    private ImageService getImageService() {
        if (imageService == null) {
            imageService = new ImageService(new ImageDAO(sessionFactory));
        }
        return imageService;
    }

    private CommentsService getCommentsService() {
        if (commentsService == null) {
            commentsService = new CommentsService(new CommentsDAO(sessionFactory));
        }
        return commentsService;
    }

    public synchronized <T> T get(Class<T> clazz) {
        switch (clazz.getCanonicalName()) {
            case "services.UserService":
                return (T) getUserService();
            case "services.ImageService":
                return (T) getImageService();
            case "services.CommentsService":
                return (T) getCommentsService();
            default:
                throw new IllegalStateException("Cannot find service class!!!");
        }
    }
}
