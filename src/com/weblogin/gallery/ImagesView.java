package com.weblogin.gallery;



import com.weblogin.gallery.beans.ImageBean;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;


import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

@Named
@ConversationScoped
public class ImagesView implements Serializable{

  private final String path = "http://localhost:8080/img/t.jpg";
  private List<ImageBean> images;
  private ImageBean selectedImage;

  @PostConstruct
  public void init() {
    images = new ArrayList<>();
    getPictures();
    System.out.println("Construct ImageHandler");

  }

  public List<ImageBean> getImages() {
    return images;
  }

  public ImageBean getSelectedImage() {
    return selectedImage;
  }

  public void setSelectedImage(ImageBean selectedImage) {
    this.selectedImage = selectedImage;
  }
  private void getPictures() {
    try {

      Class.forName("com.mysql.jdbc.Driver");
      Connection connection = DriverManager
          .getConnection("jdbc:mysql://localhost/WebResources?user=root&password=");
      PreparedStatement ps = connection
          .prepareStatement("SELECT owner, reference, description FROM images WHERE public=?;");
      ps.setBoolean(1, true);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        images.add(new ImageBean(rs.getString("owner"),
            rs.getString("reference"), rs.getString("description")));
      }
      connection.close();
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("IN CATCH");
    }
  }
}