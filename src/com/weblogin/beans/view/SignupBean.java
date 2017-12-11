package com.weblogin.beans.view;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;
import javax.wsdl.Input;
import com.weblogin.api.RegisterWrapper;
import com.weblogin.beans.UserSignupBean;


/**
 * Class handling all information needed to create a new user
 * 
 * @author Harald & Stefan
 *
 */
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


  /**
   * processes the signup request
   * 
   * @return URL
   */
  public String signup() {
    RegisterWrapper registerWrapper = new RegisterWrapper();
    UserSignupBean user = createUser();

    return registerWrapper.signup(user);
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

  /**
   * Validates email
   * 
   * @param context
   * @param component
   * @param value
   * @throws ValidatorException
   */
  public void validateEmail(FacesContext context, UIComponent component, Object value)
      throws ValidatorException {
    String input = value.toString();

    if (!(input.contains("@")) || !(input.contains("."))) {
      FacesMessage message = new FacesMessage("Not a valied email!");
      throw new ValidatorException(message);
    }
  }

  /**
   * Validates name
   * 
   * @param context
   * @param component
   * @param value
   * @throws ValidatorException
   */
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

  /**
   * Validates password
   * 
   * @param context
   * @param component
   * @param value
   * @throws ValidatorException
   */
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
      FacesMessage message = new FacesMessage("Password is to short!");
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
