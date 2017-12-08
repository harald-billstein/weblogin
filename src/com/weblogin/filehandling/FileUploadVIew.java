package com.weblogin.filehandling;

import org.primefaces.model.UploadedFile;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@RequestScoped
public class FileUploadVIew implements Serializable {

  private static final long serialVersionUID = -4549343884187952L;
  private UploadedFile file;
  private String t = "nutestarjag";

  public FileUploadVIew () {
    System.out.println("FileUploadView init");
  }

  public void uploadFile() {
    System.out.println(file.getFileName().toString());

    System.out.println(file);
  }

  public UploadedFile getFile() {
    return file;
  }

  public void setFile(UploadedFile file) {
    this.file = file;
  }

  public String getT() {
    return t;
  }

  public void setT(String t) {
    this.t = t;
  }
}