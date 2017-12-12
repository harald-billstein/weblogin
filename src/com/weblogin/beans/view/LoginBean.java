package com.weblogin.beans.view;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import com.weblogin.api.LoginWrapper;

/**
 * Class handling the login information
 * 
 * @author Harald & Stefan
 * @since 2017-12-12
 */

@RequestScoped
@Named
public class LoginBean {

  private String username;
  private String password;

  public LoginBean() {
    System.out.println("LoginBean: init");
  }


  /**
   * processes the login request
   * 
   * @return URL
   */
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
