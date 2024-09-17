package ru.vagapov.userapi.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    // реализуйте настройку соединения с БД
    private static final String HOST ="jdbc:postgresql://localhost:5432/DATABASE";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "admin";
    public Connection connection;

    public  Connection getConnection()  {
        try {
            connection= DriverManager.getConnection(HOST,USERNAME,PASSWORD);
            System.out.println("ok");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (connection!=null)
        return connection;
        else {
            try {
                connection= DriverManager.getConnection(HOST,USERNAME,PASSWORD);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
          return connection;
    }
}


