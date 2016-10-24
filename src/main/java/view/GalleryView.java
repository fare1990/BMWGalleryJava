package view;

import entity.Image;

import java.util.List;

/**
 * Created by Fare on 02.08.2016.
 */
public class GalleryView {

    private String userName;
    private boolean loginFormVisible;
    private boolean logoutFormVisible;
    private String userGalleryName;
    private boolean imageAddFormVisible;
    private List<Image> imagelist;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isLoginFormVisible() {
        return loginFormVisible;
    }

    public void setLoginFormVisible(boolean loginFormVisible) {
        this.loginFormVisible = loginFormVisible;
    }

    public boolean isLogoutFormVisible() {
        return logoutFormVisible;
    }

    public void setLogoutFormVisible(boolean logoutFormVisible) {
        this.logoutFormVisible = logoutFormVisible;
    }

    public String getUserGalleryName() {
        return userGalleryName;
    }

    public void setUserGalleryName(String userGalleryName) {
        this.userGalleryName = userGalleryName;
    }

    public boolean isImageAddFormVisible() {
        return imageAddFormVisible;
    }

    public void setImageAddFormVisible(boolean imageAddFormVisible) {
        this.imageAddFormVisible = imageAddFormVisible;
    }

    public List<Image> getImagelist() {
        return imagelist;
    }

    public void setImagelist(List<Image> imagelist) {
        this.imagelist = imagelist;
    }
}
