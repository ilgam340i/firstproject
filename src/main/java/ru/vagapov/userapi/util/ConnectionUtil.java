package ru.vagapov.userapi.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import ru.vagapov.userapi.entity.UserEntity;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
    // реализуйте настройку соединения с БД
    private static final String HOST = "jdbc:postgresql://localhost:5432/DATABASE";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "admin";
    public Connection connection;

    public Connection getConnection() {
        try {
            connection = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
            System.out.println("ok");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (connection != null)
            return connection;
        else {
            try {
                connection = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }


    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "org.postgresql.Driver");
                settings.put(Environment.URL, "jdbc:postgresql://localhost:5432/DATABASE");
                settings.put(Environment.USER, "postgres");
                settings.put(Environment.PASS, "admin");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");

                settings.put(Environment.SHOW_SQL, "true");

                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                settings.put(Environment.HBM2DDL_AUTO, "create-drop");

                configuration.setProperties(settings);

                configuration.addAnnotatedClass(UserEntity.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}

