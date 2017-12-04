package com.weblogin.filters;

import com.weblogin.database.DatabaseUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    DatabaseUtil db = new DatabaseUtil();
    Connection connection = db.getConnection();
    try {
      PreparedStatement ps = connection.prepareStatement("SELECT * FROM user;");
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        System.out.println(rs.getString(1));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    // TODO Auto-generated method stub

  }

  @Override
  public void init(FilterConfig config) throws ServletException {
    System.out.println("ApiFilter: init");
    // TODO Auto-generated method stub

  }

}
