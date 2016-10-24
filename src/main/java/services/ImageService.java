package services;

import database.ImageDAO;
import entity.Image;

import java.util.Date;
import java.util.List;


/**
 * Created by Fare on 29.07.2016.
 */
public class ImageService {

    private ImageDAO imageDAO;

    public ImageService(ImageDAO imageDAO){
        this.imageDAO = imageDAO;
    }

    public String getFirstUserImagePath (int userId){

        List<Image> images = imageDAO.getImages("user_id", userId);
        if (!images.isEmpty()) {
            return images.get(0).getImagePath();
        }
        else
            return imageDAO.getImages("category","noimages").get(0).getImagePath();
    }

    public List<Image> getGallery (String category, int userId){

        if (category.equals("user"))
            return imageDAO.getImages("user_id", userId);
        else
            return imageDAO.getImages("category", category);
    }

    public void addImage (int user_id, String imageName, String path){
        Image image = new Image();
        Date date = new Date();
        image.setCategoryName("user");
        image.setImageName(imageName);
        image.setImagePath(path);
        image.setDateCreated(date);
        image.setUserId(user_id);

        imageDAO.addImage(image);
    }

    public String getImagePathById (int imageId){
        List<Image> images = imageDAO.getImages("id", imageId);
        if (!images.isEmpty()) {
            return images.get(0).getImagePath();
        }
        else
            return null;
    }
}
