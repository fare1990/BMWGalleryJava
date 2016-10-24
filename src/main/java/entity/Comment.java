package entity;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Fare on 04.08.2016.
 */
public class Comment {

    private int commentId;
    private String commentText;
    private Date createdDate;
    private int commentAuthorId;
    private ArrayList<Comment> subcomments;

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getCommentAuthorId() {
        return commentAuthorId;
    }

    public void setCommentAuthorId(int commentAuthorId) {
        this.commentAuthorId = commentAuthorId;
    }

    public ArrayList<Comment> getSubcomments() {
        return subcomments;
    }

    public void setSubcomments(ArrayList<Comment> subcomments) {
        this.subcomments = subcomments;
    }
}
