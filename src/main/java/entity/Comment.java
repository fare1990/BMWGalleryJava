package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Fare on 04.08.2016.
 */
public class Comment implements Serializable{

    private int commentId;
    private int pictureId;
    private String commentText;
    private Date createdDate;
    private int commentAuthorId;
    private List<Comment> subcomments;
    private Comment parent;

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

    public List<Comment> getSubcomments() {
        return subcomments;
    }

    public void setSubcomments(List<Comment> subcomments) {
        this.subcomments = subcomments;
    }

    public Comment getParent() {
        return parent;
    }

    public void setParent(Comment parent) {
        this.parent = parent;
    }

    public int getPictureId() {
        return pictureId;
    }

    public void setPictureId(int pictureId) {
        this.pictureId = pictureId;
    }
}
