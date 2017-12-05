package com.weblogin.filters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

@WebFilter(filterName = "profile-filter", urlPatterns = "/profile.xhtml")
public class ProfileFilter implements Filter {



  @Override
  public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
      throws IOException, ServletException {
    System.out.println("ProfileFilter: doFilter");

    HttpServletResponse response = (HttpServletResponse) resp;
    HttpServletRequest request = (HttpServletRequest) req;

    Boolean accessGranted = false;
    String link = null;
    URL url;
    HttpURLConnection connection = null;

    String username = (String) request.getAttribute("username");
    String password = (String) request.getAttribute("password");

    if (username == null || password == null) {
      accessGranted = false;
    } else {
      link = "http://localhost:8080/LoginApi/api/login/" + username;
      url = new URL(link);
      connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("GET");
      connection.setRequestProperty("password", "password");
    }


    if (connection != null && connection.getResponseCode() == 200) {
      request.setAttribute("token", "token");

      BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

      String output;
      while ((output = br.readLine()) != null) {
        System.out.println(output);
        JSONObject jsonObj = new JSONObject(output);
        String token = (String) jsonObj.get("token");
        System.out.println(token);
        accessGranted = true;
      }
    } else {
      accessGranted = false;

    }

    if (accessGranted) {
      // TODO user access accepted
      chain.doFilter(req, resp);

    } else {
      // TODO user access denied
      response.sendRedirect("login.xhtml");
    }

  }


  @Override
  public void destroy() {
    System.out.println("ProfileFilter: destroy");
  }


  @Override
  public void init(FilterConfig config) throws ServletException {
    System.out.println("ProfileFilter: init");
  }

}
