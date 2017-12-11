package com.weblogin.beans.view;

public class Item {

  private String name;
  private String url;
  private Boolean isloggedinAccessable;

  public Item(String name, String url, Boolean isloggedinAccessable) {
    this.name = name;
    this.url = url;
    this.isloggedinAccessable = isloggedinAccessable;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Boolean isIsloggedinAccessable() {
    return isloggedinAccessable;
  }

  public void setIsloggedinAccessable(Boolean isloggedinAccessable) {
    this.isloggedinAccessable = isloggedinAccessable;
  }
  
  
}
