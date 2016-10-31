package database;


import entity.User;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Fare on 21.07.2016.
 */
public class JDBCUserDAO {

    private DataBaseProvider provider;

    public JDBCUserDAO(DataBaseProvider dataBaseProvider) {
        this.provider = dataBaseProvider;
    }


     /*public void updateUser(User user) {
        String sqlQuery = "UPDATE BB_USERS SET (username,email,password,gallery_name) = ('"
                + user.getUsername() + "', '"
                + user.getEmail() + "', '"
                + user.getPassword() + "', '"
                + user.getGalleryName()
                + "') WHERE user_id = '"
                + user.getUserid()
                + "'";

        provider.executeQuery(sqlQuery);
    }*/

    public void addUser(User user) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(date);
        String sqlQuery = "INSERT INTO BB_USERS (username,email,password,gallery_name,created_date) VALUES (?,?,?,?,?)";

        provider.executeQueryWithNoResult(sqlQuery, s -> {
            try {
                s.setString(1, user.getUsername());
                s.setString(2, user.getEmail());
                s.setString(3, user.getPassword());
                s.setString(4, user.getGalleryName());
                s.setDate(5, java.sql.Date.valueOf(currentDate));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return s;
        });

    }

    public User getUser(String userEmail) {
        String sqlQuery = "SELECT * FROM BB_USERS where email=?";
        User user = provider.executeQueryWithResult(sqlQuery,
                ps -> {
                    try {
                        ps.setString(1, userEmail);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    return ps;
                },
                rs -> {
                    User usr = new User();
                    if (rs != null) {
                        try {
                            rs.next();
                            usr.setUserId(rs.getInt("user_id"));
                            usr.setUsername(rs.getString("username"));
                            usr.setPassword(rs.getString("password"));
                            usr.setEmail(rs.getString("email"));
                            usr.setGalleryName(rs.getString("gallery_name"));
                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    return usr;
                });
        return user;
    }

    public void updateUser(User user) {
        String sqlQuery = "UPDATE BB_USERS SET (username,email,password,gallery_name) = (?,?,?,?) WHERE user_id =?";
        provider.executeQueryWithNoResult(sqlQuery, ps->{
            try {
                ps.setString(1, user.getUsername());
                ps.setString(2, user.getEmail());
                ps.setString(3, user.getPassword());
                ps.setString(4, user.getGalleryName());
                ps.setInt(5, user.getUserId());
            }
            catch (SQLException e){
                e.printStackTrace();
            }
            return ps;
        });
    }

    public void createTableUsers() {
        String createTableSQL = "CREATE TABLE BB_USERS ("
                + "USER_ID SERIAL, "
                + "USERNAME VARCHAR(50) NOT NULL, "
                + "EMAIL VARCHAR(100) NOT NULL, "
                + "PASSWORD VARCHAR(100) NOT NULL, "
                + "GALLERY_NAME VARCHAR(100) NOT NULL, "
                + "CREATED_DATE DATE NOT NULL "
                + ")";
        provider.executeQuery(createTableSQL);
    }

}
