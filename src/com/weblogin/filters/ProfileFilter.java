package com.weblogin.filters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.inject.Inject;
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
import com.weblogin.beans.view.ProfileBean;


/**
 * Class filtering request heading for profile page
 * 
 * @author Harald & Stefan
 * @since 2017-12-12
 */
@WebFilter(filterName = "profile-filter", urlPatterns = "/profile.xhtml")
public class ProfileFilter implements Filter {

  @Inject
  private ProfileBean profileBean;
  private String username;

  
  /**
   * checks if request is valid. If not filter directs request to proper destination
   */
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

    username = (String) request.getSession().getAttribute("username");
    String token = (String) request.getSession().getAttribute("token");

    if (username == null || token == null) {
      accessGranted = false;
    } else {
      link = "http://localhost:8080/LoginApi/api/validate/" + username + "/" + token;
      url = new URL(link);
      connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("GET");

      if (connection.getResponseCode() == 200) {

        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String output;
        while ((output = br.readLine()) != null) {
          System.out.println("Response" + output);
          JSONObject jsonObj = new JSONObject(output);
          accessGranted = (Boolean) jsonObj.get("userTokenValid");
        }
        
      } else {
        System.out.println("Session expired!");
        accessGranted = false;
      }
    }

    if (accessGranted) {
      System.out.println("Username set to profileBean: " + username);
      profileBean.setUsername(username);
      chain.doFilter(req, resp);
    } else {
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
