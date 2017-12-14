package com.weblogin.filehandling;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import javax.inject.Named;
import java.io.Serializable;

/**
 * @author someone
 * @version 1.0
 * @since 2017-12-06
 */
@Named
@SessionScoped
public class FileUploadVIew implements Serializable {

  private static final long serialVersionUID = -4549343884187952L;
  private UploadedFile file;
  private final String dbUrl = "jdbc:mysql://localhost/WebResources";
  private final String username = "root";
  private final String password = "";

  public FileUploadVIew() {
    System.out.println("FileUploadView init");
  }

  /**
   * Uploads file to database
   *
   * @param event FileUploadEvent
   */
  public void uploadFile(FileUploadEvent event) {
    file = event.getFile();
    System.out.println("Upload event" + file.getFileName().toString());
    loadClassDriver();
    PreparedStatement ps = null;
    try (Connection connection = DriverManager.getConnection(dbUrl, username, password)) {
      connection.setAutoCommit(false);
      ps = connection.prepareStatement(
              "INSERT INTO images (owner, img, public, reference) VALUES (?, ?, ?, ?);");
      ps.setString(1, file.getFileName());
      ps.setBinaryStream(2, file.getInputstream());
      ps.setBoolean(3, true);
      ps.setString(4, UUID.randomUUID().toString().replace("-", ""));
      ps.executeUpdate();
      connection.commit();
      FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Upload success",
              file.getFileName() + " is uploaded.");
      FacesContext.getCurrentInstance().addMessage(null, msg);
    } catch (Exception e) {
      e.printStackTrace();
      // Add error message
      FacesMessage errorMsg =
              new FacesMessage(FacesMessage.SEVERITY_ERROR, "Upload error", e.getMessage());
      FacesContext.getCurrentInstance().addMessage(null, errorMsg);
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

  public UploadedFile getFile() {
    return file;
  }

  public void setFile(UploadedFile file) {
    this.file = file;
  }

  private void loadClassDriver() {
    try {
      Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

}
