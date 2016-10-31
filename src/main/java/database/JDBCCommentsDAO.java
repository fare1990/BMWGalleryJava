package database;

import entity.Comment;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fare on 04.08.2016.
 */
public class JDBCCommentsDAO {

    private DataBaseProvider provider;
    private final static Logger logger = Logger.getLogger(JDBCCommentsDAO.class);

    public JDBCCommentsDAO(DataBaseProvider dataBaseProvider) {
        this.provider = dataBaseProvider;
    }

    public void addComment(Comment comment, int pictureId) {
        String sqlQuery = "INSERT INTO BB_COMMENTS (picture_id,comment,comment_date,author_id) VALUES (?,?,?,?)";
        provider.executeQueryWithNoResult(sqlQuery, ps -> {
            try {
                ps.setInt(1, pictureId);
                ps.setString(2, comment.getCommentText());
                ps.setDate(3, new java.sql.Date(comment.getCreatedDate().getTime()));
                ps.setInt(4, comment.getCommentAuthorId());
            } catch (SQLException e) {
                logger.error("Something is wrong while adding comment", e);
            }
            return ps;
        });
    }

    public void addComment(Comment comment, int replyToId, int pictureId) {
        String sqlQuery = "INSERT INTO BB_COMMENTS (picture_id,comment,comment_date,author_id, reply_to) VALUES (?,?,?,?,?)";
        provider.executeQueryWithNoResult(sqlQuery, ps -> {
            try {
                ps.setInt(1, pictureId);
                ps.setString(2, comment.getCommentText());
                ps.setDate(3, new java.sql.Date(comment.getCreatedDate().getTime()));
                ps.setInt(4, comment.getCommentAuthorId());
                ps.setInt(5, replyToId);
            } catch (SQLException e) {
                logger.error("Something is wrong while adding comment", e);
            }
            return ps;
        });
    }

    public List<Comment> getComments(int imageId) {
        String sqlQuery = "SELECT * FROM BB_COMMENTS WHERE picture_id=? AND reply_to IS NULL";
        List<Comment> commentsList = provider.executeQueryWithResult(sqlQuery, ps -> {
                    try {
                        ps.setInt(1, imageId);
                    } catch (SQLException e) {
                        logger.error("Something is wrong while setting ps in getComments", e);
                    }
                    return ps;
                },
                rs -> {
                    List<Comment> comments = null;
                    if (rs != null) {
                        comments = new ArrayList<Comment>();
                        try {
                            while (rs.next()) {
                                Comment comment = new Comment();
                                comment.setCommentId(rs.getInt("id"));
                                comment.setCommentText(rs.getString("comment"));
                                comment.setCommentAuthorId(rs.getInt("author_id"));
                                comment.setCreatedDate(rs.getDate("comment_date"));

                                getSubcomments(comment);
                                comments.add(comment);
                            }
                        } catch (SQLException e) {
                            logger.error("Something is wrong while getting from rs in getComments", e);
                        }
                    }
                    return comments;
                });
        return commentsList;
    }

    private void getSubcomments(Comment comment) {
        String sqlQuery = "SELECT * FROM BB_COMMENTS WHERE reply_to=?";
        comment.setSubcomments(provider.executeQueryWithResult(sqlQuery, ps -> {
                    try {
                        ps.setInt(1, comment.getCommentId());
                    } catch (SQLException e) {
                        logger.error("Something is wrong while setting ps in getSubComments", e);
                    }
                    return ps;
                },
                rs -> {
                    ArrayList<Comment> subCommentsList = null;
                    if (rs != null) {
                        subCommentsList = new ArrayList<Comment>();
                        try {
                            while (rs.next()) {
                                Comment subcomment = new Comment();
                                subcomment.setCommentId(rs.getInt("id"));
                                subcomment.setCommentText(rs.getString("comment"));
                                subcomment.setCommentAuthorId(rs.getInt("author_id"));
                                subcomment.setCreatedDate(rs.getDate("comment_date"));
                                subCommentsList.add(subcomment);
                                //get subcomments recursively
                                getSubcomments(subcomment);
                            }
                        } catch (SQLException e) {
                            logger.error("Something is wrong while getting from rs in getSubComments", e);
                        }
                    }
                    return subCommentsList;
                }));
    }

    public void createTableComments() {
        String createTableSQL = "CREATE TABLE BB_COMMENTS ("
                + "ID SERIAL, "
                + "PICTURE_ID INT NOT NULL, "
                + "COMMENT VARCHAR(500) NOT NULL, "
                + "COMMENT_DATE DATE NOT NULL, "
                + "AUTHOR_ID INT NOT NULL, "
                + "REPLY_TO INT "
                + ")";
        provider.executeQuery(createTableSQL);
    }
}
