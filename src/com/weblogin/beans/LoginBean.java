package com.weblogin.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@RequestScoped
@Named
public class LoginBean {
  
  private String userName;
  private String password;
  
  public LoginBean() {
    System.out.println("LoginBean: init");
  }
  
  public String signIn() {
    System.out.println("LoginBean: signIn()");
    
    // TODO send to profile, let filter handle if user is not logged in and redirect back to login
    return "profile";
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return "LoginBean [userName=" + userName + ", password=" + password + "]";
  }
}
