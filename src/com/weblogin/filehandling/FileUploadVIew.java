package com.weblogin.filehandling;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.model.UploadedFile;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@RequestScoped
public class FileUploadVIew implements Serializable {

  private static final long serialVersionUID = -4549343884187952L;
  private UploadedFile file;

  public FileUploadVIew () {
    System.out.println("FileUploadView init");
  }

  public void uploadFile() {
    System.out.println(file.getFileName().toString());

      try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/WebResources?user=root&password=");
        connection.setAutoCommit(false);
        PreparedStatement statement = connection.prepareStatement("INSERT INTO images (owner, img, public) VALUES (?, ?, ?);");
        statement.setString(1, file.getFileName());
        statement.setBinaryStream(2, file.getInputstream());
        statement.setBoolean(3,true);
        statement.executeUpdate();

        connection.commit();
        connection.close();

        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Upload success", file.getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);

      } catch (Exception e) {
        e.printStackTrace();

        // Add error message
        FacesMessage errorMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Upload error", e.getMessage());
        FacesContext.getCurrentInstance().addMessage(null, errorMsg);
      }


      System.out.println(file);
  }

  public UploadedFile getFile() {
    return file;
  }

  public void setFile(UploadedFile file) {
    this.file = file;
  }

}