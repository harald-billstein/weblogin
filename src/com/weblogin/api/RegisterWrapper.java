package com.weblogin.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;
import com.weblogin.beans.UserSignupBean;


/**
 * Class handeling calls to the login API
 * 
 * @author Harald & Stefan
 *
 */
public class RegisterWrapper {


  public String deleteUser(String user, String token) {
    String apiUrl = "http://localhost:8080/WebRegisterAPI/v1/delete/" + user + "/" + token;
    System.out.println(apiUrl);
    HttpURLConnection connection;
    JSONObject jsonIn = null;

    connection = getConnection(apiUrl, "DELETE");


    // REVICED JSON FROM API
    try {
      BufferedReader reader =
          new BufferedReader(new InputStreamReader(connection.getInputStream()));

      String output;
      while ((output = reader.readLine()) != null) {
        jsonIn = new JSONObject(output);
        System.out.println((String) jsonIn.get("message"));
        addErrorMessages((String) jsonIn.get("message"));
      }
    } catch (Exception e) {
    }



    return "login?faces-redirect=true";
  }


  /**
   * Registers a user
   * 
   * @param user created
   * @return URL
   */
  public String signup(UserSignupBean user) {

    String navigationLink;
    String apiUrl = "http://localhost:8080/WebRegisterAPI/api/register/new/user";
    HttpURLConnection connection = null;
    Integer responseCode = null;
    OutputStreamWriter writer;
    BufferedReader reader;
    boolean registrationSuccess = false;

    JSONObject jsonOut;
    JSONObject jsonIn = null;

    // SEND JSON TO API
    connection = getConnection(apiUrl, "PUT");
    try {
      jsonOut = new JSONObject(user);
      writer = new OutputStreamWriter(connection.getOutputStream());
      writer.write(jsonOut.toString());
      writer.close();
      responseCode = connection.getResponseCode();
    } catch (IOException e) {
      e.printStackTrace();
    }

    // REVICED JSON FROM API
    try {
      reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

      String output;
      while ((output = reader.readLine()) != null) {
        jsonIn = new JSONObject(output);
        registrationSuccess = (boolean) jsonIn.get("registerd");
      }

    } catch (IOException e) {
      e.printStackTrace();
    }

    if (responseCode == 200 && registrationSuccess) {
      String token = (String) jsonIn.get("token");
      FacesContext context = FacesContext.getCurrentInstance();
      HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
      session.setAttribute("username", user.getUserName());
      session.setAttribute("token", token);
      navigationLink = "profile?faces-redirect=true";
    } else {
      addErrorMessages("ERROR IN SIGNUP!");
      navigationLink = "signup";
    }
    return navigationLink;
  }

  private HttpURLConnection getConnection(String apiUrl, String method) {
    URL url;
    HttpURLConnection connection = null;
    try {
      url = new URL(apiUrl);
      connection = (HttpURLConnection) url.openConnection();
      connection.setDoOutput(true);
      connection.setRequestMethod(method);
      connection.setRequestProperty("Content-Type", "application/json");
      connection.setRequestProperty("Accept", "application/json");
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
