package com.weblogin.gallery;



import com.weblogin.gallery.beans.ImageBean;

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

  private List<ImageBean> images;
  private ImageBean selectedImage;

  @PostConstruct
  public void init() {
    images = new ArrayList<>();
    loadPictures();
    System.out.println("Construct ImageView..");

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
  private void loadPictures() {
    try {

      Class.forName("com.mysql.jdbc.Driver");
      Connection connection = DriverManager
          .getConnection("jdbc:mysql://172.17.0.2/webresources?user=webapp&password=password");
      PreparedStatement ps = connection
          .prepareStatement("SELECT owner, reference, description FROM images WHERE public=?;");
      ps.setBoolean(1, true);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        System.out.println("loading img");
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
