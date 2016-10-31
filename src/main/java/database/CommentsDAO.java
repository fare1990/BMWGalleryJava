package database;

import entity.Comment;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by Fare on 04.08.2016.
 */
public class CommentsDAO {

    private SessionFactory sessionFactory;
    private final static Logger logger = Logger.getLogger(CommentsDAO.class);

    public CommentsDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addComment(Comment comment, int replyTo, int pictureId) {//todo: refactor this, sending pictureId is unnecessary
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        comment.setPictureId(pictureId);
        if (replyTo != 0) {
            Comment parent = getComment(replyTo);
            comment.setParent(parent);
            parent.getSubcomments().add(comment);
            session.saveOrUpdate(parent);
            tx.commit();
            session.close();
            return;
        }
        session.save(comment);
        tx.commit();
        session.close();
    }

    public Comment getComment (int id) {
        String hqlQuery = "from Comment where commentId = :commentId";
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Comment comment = (Comment)session.createQuery(hqlQuery).setParameter("commentId", id).uniqueResult();
        tx.commit();
        session.close();
        return comment;
    }

    public List<Comment> getComments(int imageId){
        String hqlQuery = "from Comment where pictureId = :pictureId and parent = null";
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        List<Comment> comments = session.createQuery(hqlQuery).setParameter("pictureId", imageId).list();
        tx.commit();
        session.close();
        return comments;
    }
}
