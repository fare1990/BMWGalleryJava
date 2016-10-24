package database;

import entity.Image;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fare on 27.07.2016.
 */
public class ImageDAO {

    private DataBaseProvider provider;
    private final static Logger logger = Logger.getLogger(ImageDAO.class);

    public ImageDAO(DataBaseProvider dataBaseProvider) {
        this.provider = dataBaseProvider;
    }

    public void createTableImages() {

        String createTableSQL = "CREATE TABLE BB_IMAGES ("
                + "ID SERIAL, "
                + "USER_ID INT, "
                + "PICTURE_NAME VARCHAR(100) NOT NULL, "
                + "PATH VARCHAR(200) NOT NULL, "
                + "CREATED_DATE DATE NOT NULL, "
                + "CATEGORY VARCHAR(100) NOT NULL "
                + ")";
        provider.executeQuery(createTableSQL);
    }

    public List<Image> getImages(String rowName, int value) {
        String sqlQuery = null;
        switch (rowName){
            case "user_id": sqlQuery = "SELECT * FROM BB_IMAGES where user_id=?";
                break;
            case "id": sqlQuery = "SELECT * FROM BB_IMAGES where id=?";
                break;
        }
        return provider.executeQueryWithResult(sqlQuery, ps -> {
                    try {
                        ps.setInt(1, value);
                    } catch (SQLException e) {
                        logger.error("Error setting parameters to ps in getImages", e);
                    }
                    return ps;
                },
                rs -> {
                    List<Image> images = null;
                    if (rs != null) {
                        images = new ArrayList<>();
                        try {
                            while (rs.next()) {
                                Image image = new Image();
                                image.setImageId(rs.getInt("id"));
                                image.setUserId(rs.getInt("user_id"));
                                image.setImageName(rs.getString("picture_name"));
                                image.setImagePath(rs.getString("path"));
                                image.setDateCreated(rs.getDate("created_date"));
                                image.setCategoryName(rs.getString("category"));
                                images.add(image);
                            }
                        } catch (SQLException e) {
                            logger.error("Error adding images to list from rs", e);
                        }
                    }
                    return images;
                });
    }

    public List<Image> getImages(String rowName, String value) {
        String sqlQuery = "SELECT * FROM BB_IMAGES where " + rowName + "=?";
        return provider.executeQueryWithResult(sqlQuery, ps -> {
                    try {
                        ps.setString(1, value);
                    } catch (SQLException e) {
                        logger.error("Error setting parameters to ps in getImages", e);
                    }
                    return ps;
                },
                rs -> {
                    List<Image> images = null;
                    if (rs != null) {
                        images = new ArrayList<>();
                        try {
                            while (rs.next()) {
                                Image image = new Image();
                                image.setImageId(rs.getInt("id"));
                                image.setUserId(rs.getInt("user_id"));
                                image.setImageName(rs.getString("picture_name"));
                                image.setImagePath(rs.getString("path"));
                                image.setDateCreated(rs.getDate("created_date"));
                                image.setCategoryName(rs.getString("category"));
                                images.add(image);
                            }
                        } catch (SQLException e) {
                            logger.error("Error adding images to list from rs", e);
                        }
                    }
                    return images;
                });
    }

    public void addImage(Image img) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //todo: maybe unnecessary format, need discussion
        String sqlQuery = "INSERT INTO BB_IMAGES (user_id,picture_name,path,created_date,category) VALUES (?,?,?,?,?)";
        provider.executeQueryWithNoResult(sqlQuery, ps -> {
            try {
                ps.setInt(1, img.getUserId());
                ps.setString(2, img.getImageName());
                ps.setString(3, img.getImagePath());
                ps.setDate(4, java.sql.Date.valueOf(sdf.format(img.getDateCreated())));
                ps.setString(5, img.getCategoryName());
            } catch (SQLException e) {
                logger.error("Error adding image", e);
            }
            return ps;
        });
    }



    /*public void addImage (Image img){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String sqlQuery = "INSERT INTO BB_IMAGES (user_id,picture_name,path,created_date,category) VALUES ('"
                + img.getUserId() +"', '" + img.getImageName() +"', '"
                + img.getImagePath() +"', '" + sdf.format(img.getDateCreated()) +"', '"
                + img.getCategoryName()
                + "')";
        provider.executeQuery(sqlQuery);
    }*/
}
