package com.weblogin.gallery;

import java.sql.*;
import java.io.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/img")
public class ImageServlet extends HttpServlet {

  /**
   *
   */
  private static final long serialVersionUID = 4593558495041379082L;

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) {
    String imageReference = request.getParameter("image");

    try {
      Class.forName("com.mysql.jdbc.Driver");
      Connection connection = DriverManager
          .getConnection("jdbc:mysql://172.17.0.2/webresources?user=webapp&password=password");
      PreparedStatement ps = connection
          .prepareStatement("SELECT owner, id, img FROM images WHERE reference=?;");
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
      //TODO close connection
      connection.close();
    } catch (Exception e) {
      e.printStackTrace();
      try {
        response.getWriter().write("fail");
      } catch (IOException e1) {
        e1.printStackTrace();
      }
      System.out.println("IN CATCH");
    }
  }
}