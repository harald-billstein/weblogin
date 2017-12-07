package com.weblogin.beans.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;

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
    String link = "http://localhost:8080/LoginApi/api/login/" + username;
    String token = null;
    String redirectLink = null;

    try {
      
      URL url = new URL(link);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("GET");
      connection.setRequestProperty("password", password);

      if (connection.getResponseCode() == 200) {
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String output;
        while ((output = br.readLine()) != null) {
          System.out.println("Response" + output);
          JSONObject jsonObj = new JSONObject(output);
          token = (String) jsonObj.get("token");
          FacesContext context = FacesContext.getCurrentInstance();
          HttpSession session = (HttpSession) context.getExternalContext().getSession(true);

          session.setAttribute("username", this.username);
          session.setAttribute("token", token);

        }
        redirectLink = "profile?faces-redirect=true";
      } else {
        // TODO OBS alla statuskoder! 
        addErrorMessages("Login failed!");
      }
      
    } catch (IOException e) {
      addErrorMessages(e.getMessage());
      redirectLink = "login";
      e.printStackTrace();
    }
    
    return redirectLink;
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

  private void addErrorMessages(String message) {
    FacesMessage facesMessage = new FacesMessage(message);
    FacesContext.getCurrentInstance().addMessage(null, facesMessage);
  }
}
