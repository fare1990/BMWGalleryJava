package services;

import database.CommentsDAO;
import entity.Comment;

import java.util.Date;
import java.util.List;

/**
 * Created by Fare on 05.08.2016.
 */
public class CommentsService {

    private CommentsDAO commentsDAO;

    public CommentsService(CommentsDAO commentsDAO){
        this.commentsDAO = commentsDAO;
    }

    public List<Comment> getCommentsToPicture (int pictureId) {
        return commentsDAO.getComments(pictureId);
    }

    public void saveComment (String pictureId, String replyTo, String commentText, int authorId) {
        Comment comment = new Comment();
        comment.setCreatedDate(new Date());
        comment.setCommentAuthorId(authorId);
        comment.setCommentText(commentText);
        if(replyTo.isEmpty()) {
            commentsDAO.addComment(comment, 0, Integer.parseInt(pictureId));
        }
        else{
            commentsDAO.addComment(comment, Integer.parseInt(replyTo), Integer.parseInt(pictureId));
        }
    }
}
