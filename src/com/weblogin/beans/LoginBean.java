package com.weblogin.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
@Named
public class LoginBean {
  
  private String username;
  private String password;
  
  @Inject
  ProfileBean profileBean;
  
  public LoginBean() {
    System.out.println("LoginBean: init");
  }
  
  public String signIn() {
    System.out.println("LoginBean: signIn()");
    
//    profileBean.setUsername(username);
//    profileBean.setPassword(password);


    
    // TODO send to profile, let filter handle if user is not logged in and redirect back to login
    return "profile";
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return "LoginBean [userName=" + username + ", password=" + password + "]";
  }
}
