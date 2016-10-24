package view;

/**
 * Created by Fare on 01.08.2016.
 */
public class MainView {

    private String headerText;
    private String userName;
    private boolean loginFormVisible;
    private boolean logoutFormVisible;
    private boolean galleryCreateFormVisible;
    private String userGalleryName;
    private String userGalleryFirstImagePath;
    private boolean userGalleryVisible;

    public boolean isLogoutFormVisible() {
        return logoutFormVisible;
    }

    public void setLogoutFormVisible(boolean logoutFormVisible) {
        this.logoutFormVisible = logoutFormVisible;
    }

    public String getHeaderText() {
        return headerText;
    }

    public void setHeaderText(String headerText) {
        this.headerText = headerText;
    }

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

    public boolean isGalleryCreateFormVisible() {
        return galleryCreateFormVisible;
    }

    public void setGalleryCreateFormVisible(boolean galleryCreateFormVisible) {
        this.galleryCreateFormVisible = galleryCreateFormVisible;
    }

    public String getUserGalleryName() {
        return userGalleryName;
    }

    public void setUserGalleryName(String userGalleryName) {
        this.userGalleryName = userGalleryName;
    }

    public boolean isUserGalleryVisible() {
        return userGalleryVisible;
    }

    public void setUserGalleryVisible(boolean userGalleryVisible) {
        this.userGalleryVisible = userGalleryVisible;
    }

    public String getUserGalleryFirstImagePath() {
        return userGalleryFirstImagePath;
    }

    public void setUserGalleryFirstImagePath(String userGalleryFirstImagePath) {
        this.userGalleryFirstImagePath = userGalleryFirstImagePath;
    }
}
