package com.weblogin.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;


/**
 * Class handeling calls to the login API
 * 
 * @author Harald & Stefan
 * @since 2017-12-12
 */
public class LoginWrapper extends AbstractApiConnection {

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

    //connection = getConnection(link, password);
    //
    List<RequestPropertyPair> pairs = new ArrayList<RequestPropertyPair>();
    RequestPropertyPair pair = new RequestPropertyPair();
    pair.setKey("password");
    pair.setValue(password);
    pairs.add(pair);
    connection = getConnection(link, "GET", pairs);
    
    //
    
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

//  private HttpURLConnection getConnection(String link, String password) {
//    URL url;
//    HttpURLConnection connection = null;
//    try {
//      url = new URL(link);
//      connection = (HttpURLConnection) url.openConnection();
//      connection.setRequestMethod("GET");
//      connection.setRequestProperty("password", password);
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//    return connection;
//  }

  private void addErrorMessages(String message) {
    FacesMessage facesMessage = new FacesMessage(message);
    FacesContext.getCurrentInstance().addMessage(null, facesMessage);
  }

  public HttpURLConnection validateUser(String username, String token) {
    
  String link = "http://localhost:8080/LoginApi/api/validate/" + username + "/" + token;
  URL url;
  HttpURLConnection connection = null;
  try {
    url = new URL(link);
    connection = (HttpURLConnection) url.openConnection();
    connection.setRequestMethod("GET");
  } catch (IOException e) {
    e.printStackTrace();
  } 
    return connection; 
  }
}



// package com.weblogin.api;
//
// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.net.HttpURLConnection;
// import java.net.MalformedURLException;
// import java.net.URL;
// import java.util.ArrayList;
// import java.util.List;
// import javax.faces.application.FacesMessage;
// import javax.faces.context.FacesContext;
// import javax.servlet.http.HttpSession;
// import org.apache.coyote.http2.Setting;
// import org.json.JSONException;
// import org.json.JSONObject;
//
//
/// **
// * Class handeling calls to the login API
// *
// * @author Harald & Stefan
// * @since 2017-12-12
// */
// public class LoginWrapper extends AbstractApiConnection {
//
// private HttpURLConnection connection;
// private BufferedReader reader;
// private JSONObject jsonIn;
//
// /**
// * Validate if user has access to restricted resources
// *
// * @param user
// * @param token
// * @return true if validation is a success
// */
//// public boolean validateUser(String user, String token) {
//// System.out.println("LoginWrapper: validateUser");
////
//// String apiUrl = "http://localhost:8080/LoginApi/api/validate/" + user + "/" + token;
//// boolean accessGranted = false;
//// connection = getConnection(apiUrl, "GET", null);
////
//// try {
//// if (connection.getResponseCode() == 200) {
//// System.out.println("connection 200!");
//// BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//// String output;
////
//// while ((output = br.readLine()) != null) {
//// System.out.println("Response" + output);
//// JSONObject jsonObj = new JSONObject(output);
//// accessGranted = (Boolean) jsonObj.get("userTokenValid");
//// }
////
//// } else {
//// System.out.println("Session expired!");
//// accessGranted = false;
//// }
//// } catch (JSONException | IOException e) {
//// e.printStackTrace();
//// }
////
//// return accessGranted;
////
////
//// }
//
// /**
// * Check if parameters are valid
// *
// * @param username passed too API
// * @param password passed to API
// * @return URL
// */
// public String login(String username, String password) {
//
// System.out.println("LoginWrapper: signIn()");
// FacesContext context = FacesContext.getCurrentInstance();
// String link = "http://localhost:8080/LoginApi/api/login/" + username;
// String token;
// String redirectLink = null;
// HttpSession session;
// boolean loginSuccess;
//
// List<RequestPropertyPair> pairs = new ArrayList<>();
// RequestPropertyPair pair = new RequestPropertyPair();
// pair.setKey("password");
// pair.setValue(password);
// pairs.add(pair);
// connection = getConnection(link, "GET", pairs);
//
// loginSuccess = sendJsonSuccess(connection, "token");
//
//
// try {
// System.out.println("Loginwrapper: login " + connection.getResponseCode());
//
// if (connection.getResponseCode() == 200) {
// reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
// System.out.println("reader:" + reader);
//
// String output = reader.readLine();
// System.out.println(output);
//
// while (output.length() > 0) {
// System.out.println("Response" + output);
// jsonIn = new JSONObject(output);
// token = (String) jsonIn.get("token");
// session = (HttpSession) context.getExternalContext().getSession(true);
// System.out.println("Setting username and password to session " + username + " " + token);
// session.setAttribute("username", username);
// session.setAttribute("token", token);
// }
// System.out.println("LoginWrapper- sending to profile");
// redirectLink = "profile?faces-redirect=true";
// } else {
// System.out.println("Error");
// addErrorMessages("Login failed!");
// }
//
// } catch (IOException e) {
// e.printStackTrace();
// addErrorMessages(e.getMessage());
// redirectLink = "login";
// }
// return redirectLink;
// }
//
// private boolean sendJsonSuccess(HttpURLConnection connection, String key) {
// boolean registrationSuccess = false;
// String output;
//
// try {
// reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
// while ((output = reader.readLine()) != null) {
// jsonIn = new JSONObject(output);
// jsonIn.get(key);
// registrationSuccess = true;
// }
// } catch (IOException e) {
// e.printStackTrace();
// }
// return registrationSuccess;
// }
//
// private void addErrorMessages(String message) {
// FacesMessage facesMessage = new FacesMessage(message);
// FacesContext.getCurrentInstance().addMessage(null, facesMessage);
// }
// }
