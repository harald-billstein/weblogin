package com.weblogin.beans.view;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@SessionScoped
@Named
public class ProfileBean implements Serializable {
  
  private static final long serialVersionUID = -454935913884187952L;
  
  private String username;
  private String password;

  public ProfileBean() {
    System.out.println("ProfileBean: init" );
  }

  public String getUsername() {
    System.out.println("ProfileBean: getUsername" );
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
    System.out.println("ProfileBean: setUsername" + this.username);
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
  
}
