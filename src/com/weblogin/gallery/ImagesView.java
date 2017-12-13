package com.weblogin.gallery;

import com.weblogin.gallery.beans.ImageBean;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;


/**
 * @author someone
 * @version 1.0
 * @since 2017-12-06
 */
@Named
@ConversationScoped
public class ImagesView implements Serializable {

  private List<ImageBean> images;
  private ImageBean selectedImage;
  private final String dbUrl = "jdbc:mysql://localhost/WebResources";
  private final String username = "root";
  private final String password = "";

  /**
   * Initialize
   */
  @PostConstruct
  public void init() {
    images = new ArrayList<>();
    loadPictures();
    System.out.println("Initialize ImageView..");
  }

  public List<ImageBean> getImages() {
    return images;
  }

  public ImageBean getSelectedImage() {
    return selectedImage;
  }

  /**
   * Sets a bean to selected image
   *
   * @param selectedImage ImageBean
   */
  public void setSelectedImage(ImageBean selectedImage) {
    this.selectedImage = selectedImage;
  }

  /**
   * Loads image reference from database to an array of strings
   */
  private void loadPictures() {
    loadClassDriver();
    PreparedStatement ps = null;
    try (Connection connection = DriverManager.getConnection(dbUrl, username, password)) {
      ps = connection.prepareStatement("SELECT owner, reference, description FROM images WHERE public=?;");
      ps.setBoolean(1, true);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        images.add(new ImageBean(rs.getString("owner"),
                rs.getString("reference"), rs.getString("description")));
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("IN CATCH");
    } finally {
      if (ps != null) {
        try {
          ps.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }
  }

  private void loadClassDriver() {
    try {
      Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }
}