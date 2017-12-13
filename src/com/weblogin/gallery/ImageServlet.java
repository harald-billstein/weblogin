package com.weblogin.gallery;

import java.sql.*;
import java.io.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Servlet that generates images from a database
 *
 * @author someone
 * @version 1.0
 * @since 2017-12-06
 */
@WebServlet("/img")
public class ImageServlet extends HttpServlet {

  private static final long serialVersionUID = 4593558495041379082L;
  private final String dbUrl = "jdbc:mysql://localhost/WebResources";
  private final String username = "root";
  private final String password = "";

  /**
   * Generates a picture from a database
   *
   * @param request  HttpServletRequest
   * @param response HttpServletResponse
   */
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) {
    String imageReference = request.getParameter("image");
    loadClassDriver();
    PreparedStatement ps = null;
    try (Connection connection = DriverManager.getConnection(dbUrl, username, password)) {
      ps = connection.prepareStatement("SELECT owner, id, img FROM images WHERE reference=?;");
      ps.setString(1, imageReference);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        InputStream input = rs.getBinaryStream("img");
        byte[] buffer = new byte[1024];
        response.setContentType("image / jpeg");
        while (input.read(buffer) > 0) {
          response.getOutputStream().write(buffer);
        }
      }
      connection.close();
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("IN CATCH");
    } finally {
      if (ps != null) {
        try {
          ps.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }
  }

  private void loadClassDriver() {
    try {
      Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }
}