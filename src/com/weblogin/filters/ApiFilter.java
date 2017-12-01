package com.weblogin.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(filterName = "api-filter", urlPatterns = "/api/*")
public class ApiFilter implements Filter {

  @Override
  public void destroy() {
    System.out.println("ApiFilter: destroy");
    // TODO Auto-generated method stub

  }

  @Override
  public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
      throws IOException, ServletException {
    System.out.println("ApiFilter: doFilter");
    // TODO Auto-generated method stub

  }

  @Override
  public void init(FilterConfig config) throws ServletException {
    System.out.println("ApiFilter: init");
    // TODO Auto-generated method stub

  }

}
