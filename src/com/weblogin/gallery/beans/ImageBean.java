package com.weblogin.gallery.beans;

import java.io.Serializable;

public class ImageBean implements Serializable{

  //private final String path = "http://localhost:8080/img?image=";
  private final String path = "http://pheerin.broke-it.net:8888/WebPictureServlet/img?image=";
  private String owner;
  private String reference;
  private String description;

  public ImageBean(String owner, String reference, String description) {
    this.owner = owner;
    this.reference = reference;
    this.description = description;
  }

  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }

  public String getReference() {
    return path+reference;
  }

  public void setReference(String reference) {
    this.reference = reference;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}