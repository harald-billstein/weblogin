package com.weblogin.beans.view;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import com.weblogin.api.RegisterWrapper;

/**
 * Class handling user when viewing profile page
 * 
 * @author Harald & Stefan
 * @since 2017-12-12
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
    System.out.println("ProfileBean: signOut");
    HttpSession session =
        (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    session.invalidate();

    return "profile?faces-redirect=true";
  }

  public String deleteProfile() {
    System.out.println("ProfileBean: deleteProfile");
    RegisterWrapper registerWrapper = new RegisterWrapper();
    HttpSession session =
        (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    registerWrapper.deleteUser(username, (String) session.getAttribute("token"));

    return signOut();
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
