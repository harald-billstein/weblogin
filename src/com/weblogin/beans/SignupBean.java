package com.weblogin.beans;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

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
    System.out.println(toString());
    return "profile";
  }

  public void validateEmail(FacesContext context, UIComponent component, Object value)
      throws ValidatorException {
    String input = value.toString();

    if (!(input.contains("@")) || !(input.contains("."))) {
      FacesMessage message = new FacesMessage("Not a valied email!");
      throw new ValidatorException(message);
    }
  }

//  public void validatePassword(FacesContext context, UIComponent component, Object value)
//      throws ValidatorException {
//    System.out.println("SignupBean: validatePassword()");
//    FacesMessage message;
//    
//    System.out.println("password: " + this.password + " Confirm password: " + this.confirmPassword);
//    
//    if (this.confirmPassword == null) {
//      message = new FacesMessage("Fill out a password!");
//      throw new ValidatorException(message);
//    }
//
//    if (!this.password.equals(this.confirmPassword)) {
//      message = new FacesMessage("Password did not match!");
//      throw new ValidatorException(message);
//    }
//  }

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
