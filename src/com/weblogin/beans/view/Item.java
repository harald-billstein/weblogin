package com.weblogin.beans.view;

/**
 * Class containing one menu option
 * 
 * @author Harald & Stefan
 *
 */
public class Item {

  private String name;
  private String url;

  public Item(String name, String url) {
    this.name = name;
    this.url = url;
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
}
