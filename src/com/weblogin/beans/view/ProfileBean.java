package com.weblogin.beans.view;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 * Class handling user when viewing profile page
 * 
 * @author Harald & Stefan
 *
 */
@SessionScoped
@Named
public class ProfileBean implements Serializable {

  private static final long serialVersionUID = -454935913884187952L;

  private String username;
  private String password;

  public ProfileBean() {
    System.out.println("ProfileBean: init");
  }

  /**
   * Signs out user by destroying the session
   * 
   * @return
   */
  public String signOut() {
    System.out.println("Signout.....");

    HttpSession session =
        (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    session.invalidate();

    return "profile?faces-redirect=true";
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
}
