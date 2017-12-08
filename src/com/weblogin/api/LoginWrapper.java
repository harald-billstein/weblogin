package com.weblogin.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;

public class LoginWrapper {

  public String login(String username, String password) {

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

          session.setAttribute("username", username);
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

  private void addErrorMessages(String message) {
    FacesMessage facesMessage = new FacesMessage(message);
    FacesContext.getCurrentInstance().addMessage(null, facesMessage);
  }

}
