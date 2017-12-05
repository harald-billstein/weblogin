package com.weblogin.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName = "profile-filter", urlPatterns = "/profile.xhtml")
public class ProfileFilter implements Filter {



  @Override
  public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
      throws IOException, ServletException {
    System.out.println("ProfileFilter: doFilter");

    HttpServletResponse response = (HttpServletResponse) resp;
    HttpServletRequest request = (HttpServletRequest) req;

    // TODO ture:false if user has token.
    // TODO check if token is valid (Ask API)
    // TODO send user depending on token
    


    Boolean accessGranted = false;

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
