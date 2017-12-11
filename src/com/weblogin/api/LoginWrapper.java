package com.weblogin.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;


/**
 * Class handeling calls to the login API
 * 
 * @author Harald & Stefan
 *
 */
public class LoginWrapper {

  /**
   * Check if parameters are valid
   * 
   * @param username passed too API
   * @param password passed to API
   * @return URL
   */
  public String login(String username, String password) {

    System.out.println("LoginBean: signIn()");
    FacesContext context = FacesContext.getCurrentInstance();
    HttpURLConnection connection;
    BufferedReader br;
    JSONObject jsonObj;
    String link = "http://localhost:8080/LoginApi/api/login/" + username;
    String token = null;
    String redirectLink = null;
    String output;
    HttpSession session;

    connection = getConnection(link, password);
    try {

      if (connection.getResponseCode() == 200) {
        br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        while ((output = br.readLine()) != null) {
          System.out.println("Response" + output);
          jsonObj = new JSONObject(output);
          token = (String) jsonObj.get("token");
          session = (HttpSession) context.getExternalContext().getSession(true);
          session.setAttribute("username", username);
          session.setAttribute("token", token);
        }

        redirectLink = "profile?faces-redirect=true";
      } else {
        addErrorMessages("Login failed!");
      }

    } catch (IOException e) {
      e.printStackTrace();
      addErrorMessages(e.getMessage());
      redirectLink = "login";
    }
    return redirectLink;
  }

  private HttpURLConnection getConnection(String link, String password) {
    URL url;
    HttpURLConnection connection = null;
    try {
      url = new URL(link);
      connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("GET");
      connection.setRequestProperty("password", password);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return connection;
  }

  private void addErrorMessages(String message) {
    FacesMessage facesMessage = new FacesMessage(message);
    FacesContext.getCurrentInstance().addMessage(null, facesMessage);
  }
}
