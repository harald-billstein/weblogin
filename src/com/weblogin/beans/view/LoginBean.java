package com.weblogin.beans.view;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import com.weblogin.api.LoginWrapper;

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
    LoginWrapper loginWrapper = new LoginWrapper();
    return loginWrapper.login(username, password);
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
