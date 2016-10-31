package database;


import entity.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


/**
 * Created by Fare on 21.07.2016.
 */
public class UserDAO {

    private static final Logger logger = Logger.getLogger(UserDAO.class);
    private SessionFactory sessionFactory;

    public UserDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(user);
        tx.commit();
        session.close();
    }

    public User getUser(String userEmail){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        String hqlQuery = "from User where email = :userEmail";
        User user =  (User)session.createQuery(hqlQuery).setParameter("userEmail", userEmail).uniqueResult();
        tx.commit();
        session.close();
        return user;
    }

    public void updateUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(user);
        tx.commit();
        session.close();
    }

}
