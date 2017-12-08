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

public class RegisterWrapper {


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
    try {
      URL url = new URL(apiUrl);
      url.openConnection();
      connection = (HttpURLConnection) url.openConnection();
      connection.setDoOutput(true);
      connection.setRequestMethod("PUT");
      connection.setRequestProperty("Content-Type", "application/json");
      connection.setRequestProperty("Accept", "application/json");

      jsonOut = new JSONObject(user);

      writer = new OutputStreamWriter(connection.getOutputStream());
      writer.write(jsonOut.toString());
      System.out.println(jsonOut.toString());
      writer.close();
      responseCode = connection.getResponseCode();
    } catch (IOException e) {
      e.printStackTrace();
    }


    // JSON REVICED FROM API
    try {
      System.out.println("Reciveing json...");
      reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      System.out.println(reader);

      String output;
      while ((output = reader.readLine()) != null) {
        System.out.println("Response" + output);
        jsonIn = new JSONObject(output);

        System.out.println("json lenght: " + jsonIn.length());

        registrationSuccess = (boolean) jsonIn.get("registerd");
        System.out.println(registrationSuccess);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    if (responseCode == 200 && registrationSuccess) {
      // TODO set token to session
      String token = (String) jsonIn.get("token");
      System.out.println(token);

      FacesContext context = FacesContext.getCurrentInstance();
      HttpSession session = (HttpSession) context.getExternalContext().getSession(true);

      session.setAttribute("username", user.getUserName());
      session.setAttribute("token", token);

      navigationLink = "profile";
    } else {
      addErrorMessages("ERROR IN SIGNUP!");
      // TODO set message, why it faild (ex user not unique)
      navigationLink = "signup";
    }

    return navigationLink;

  }

  private void addErrorMessages(String message) {
    FacesMessage facesMessage = new FacesMessage(message);
    FacesContext.getCurrentInstance().addMessage(null, facesMessage);
  }

}
