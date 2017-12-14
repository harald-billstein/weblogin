package com.weblogin.gallery.beans;

import java.io.Serializable;

/**
 * Image POJO
 * Keeps path to image servlet and reference to image
 * also keeps description of an image and a owner reference
 *
 */
public class ImageBean implements Serializable{

  private static final long serialVersionUID = -8942074447124882507L;
  //private final String path = "http://localhost:8080/img?image=";
  private final String path = "http://localhost:8080/webLoginProject/img?image=";
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