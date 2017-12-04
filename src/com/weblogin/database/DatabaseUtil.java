package com.weblogin.database;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


public class DatabaseUtil {

    Context context = null;
    DataSource dataSource = null;
    Connection connection = null;

    public Connection getConnection() {
        try {
            context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:comp/env/jdbc/authorization");
            connection = dataSource.getConnection();

        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
