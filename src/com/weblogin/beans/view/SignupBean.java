package com.weblogin.beans.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;
import com.weblogin.beans.UserSignupBean;

@RequestScoped
@Named
public class SignupBean {

  private String userName;
  private String firstName;
  private String lastName;
  private String password;
  private String confirmPassword;
  private String email;

  public SignupBean() {
    System.out.println("SignupBean: init");
  }

  public String signup() {

    String navigationLink;
    String apiUrl = "http://localhost:8080/WebRegisterAPI/api/register/new/user";
    HttpURLConnection connection = null;
    Integer responseCode = null;
    OutputStreamWriter writer;
    BufferedReader reader;
    boolean registrationSuccess = false;

    UserSignupBean user = null;
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
      user = createUser();
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

  private UserSignupBean createUser() {
    UserSignupBean user = new UserSignupBean();
    user.setUserName(userName);
    user.setFirstName(firstName);
    user.setLastName(lastName);
    user.setPassword(password);
    user.setEmail(email);
    return user;
  }

  public void validateEmail(FacesContext context, UIComponent component, Object value)
      throws ValidatorException {
    String input = value.toString();

    if (!(input.contains("@")) || !(input.contains("."))) {
      FacesMessage message = new FacesMessage("Not a valied email!");
      throw new ValidatorException(message);
    }
  }

  public void validateName(FacesContext context, UIComponent component, Object value)
      throws ValidatorException {
    String input = value.toString();

    for (char c : input.toCharArray()) {

      if (Character.isWhitespace(c)) {
        FacesMessage message = new FacesMessage("White spaces not allowed in name");
        throw new ValidatorException(message);
      }

    }

    if (input.length() < 2) {
      FacesMessage message = new FacesMessage("Name is to short!");
      throw new ValidatorException(message);
    }

  }

  public void validatePassword(FacesContext context, UIComponent component, Object value)
      throws ValidatorException {
    String input = value.toString();

    for (char c : input.toCharArray()) {

      if (Character.isWhitespace(c)) {
        FacesMessage message = new FacesMessage("White spaces not allowed in name");
        throw new ValidatorException(message);
      }

    }

    if (input.length() < 6) {
      FacesMessage message = new FacesMessage("Name is to short!");
      throw new ValidatorException(message);
    }

  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPassword() {
    System.out.println("get password");
    return password;
  }

  public void setPassword(String password) {
    System.out.println("set password");
    this.password = password;
  }

  public String getConfirmPassword() {
    System.out.println("get confirmedpassword");
    return confirmPassword;
  }

  public void setConfirmPassword(String repeatPassword) {
    System.out.println("set confirmed password");
    this.confirmPassword = repeatPassword;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String toString() {
    return "SignupBean [userName=" + userName + ", firstName=" + firstName + ", lastName="
        + lastName + ", password=" + password + ", repeatPassword=" + confirmPassword + ", email="
        + email + "]";
  }
}
