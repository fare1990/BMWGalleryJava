package database;

import entity.Image;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by Fare on 27.07.2016.
 */
public class ImageDAO {

    private SessionFactory sessionFactory;
    private final static Logger logger = Logger.getLogger(ImageDAO.class);

    public ImageDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Image> getImages(String rowName, int value) {
        String hqlQuery = null;
        switch (rowName){
            case "user_id": hqlQuery = "from Image where userId = :value";
                break;
            case "id": hqlQuery = "from Image where id = :value";
                break;
        }

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        List<Image> images =  session.createQuery(hqlQuery).setParameter("value", value).list();
        tx.commit();
        session.close();
        return images;
    }

    public List<Image> getImages(String rowName, String value) {
        String hqlQuery = "from Image where " + rowName + "= :value";
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        List<Image> images =  session.createQuery(hqlQuery).setParameter("value", value).list();
        tx.commit();
        session.close();
        return images;
    }

    public void addImage(Image img) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(img);
        tx.commit();
        session.close();
    }
}
