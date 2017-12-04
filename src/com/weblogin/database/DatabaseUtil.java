package com.weblogin.database;

import javax.inject.Singleton;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseUtil {

    private Context context = null;
    private DataSource dataSource = null;
    private Connection connection = null;
    private final String jndiLookup = "java:comp/env/jdbc/authorization";
    private static DatabaseUtil instance = null;

    public Connection getConnection() {
        try {
            context = new InitialContext();
            dataSource = (DataSource) context.lookup(jndiLookup);
            connection = dataSource.getConnection();
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static DatabaseUtil getInstance() {
        if(instance == null) {
            instance = new DatabaseUtil();
        }
        return instance;
    }
}
