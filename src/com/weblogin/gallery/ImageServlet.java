package com.weblogin.gallery;

import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/img/t.jpg")
public class ImageServlet extends HttpServlet {

  /**
   *
   */
  private static final long serialVersionUID = 4593558495041379082L;

  public void doGet(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException {


    File file;
    try {
      String id = request.getParameter("Image_id");

      Class.forName("com.mysql.jdbc.Driver");
      Connection connection = DriverManager
          .getConnection("jdbc:mysql://localhost/WebResources?user=root&password=");
      PreparedStatement statement = connection
          .prepareStatement("SELECT owner, id, img FROM images WHERE public=true;");
      ResultSet rs = statement.executeQuery();
      FileOutputStream out;

      while (rs.next()) {
        file = new File(rs.getString("owner"));
        InputStream input = rs.getBinaryStream("img");
        byte[] buffer = new byte[1024];
        out = new FileOutputStream(file);
        //response.reset();
        response.setContentType("image / jpeg");
        while (input.read(buffer) > 0) {
          out.write(buffer);
          response.getOutputStream().write(buffer);
        }
      }
      connection.close();
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("IN CATCH");
    }
  }
}