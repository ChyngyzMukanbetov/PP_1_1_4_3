package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.internal.MetadataImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static com.mysql.cj.conf.PropertyKey.PASSWORD;
import static org.hibernate.cfg.AvailableSettings.*;

public class Util {
    // реализуйте настройку соеденения с БД
    //public static final String DB_DRIVER = "org.h2.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/world?integratedSecurity=true;";
    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "Madara2631!";

    public Connection getConnection() {
        Connection connection = null;
        try {
            //Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            //System.out.printf("connection is ok " + "\n");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("connection ERROR");
        } //catch (ClassNotFoundException e) {
        //e.printStackTrace();
        // }
        return connection;
    }

        private static SessionFactory sessionFactory;

        public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties settings = new Properties();

                settings.put(DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(URL, "jdbc:mysql://127.0.0.1:3306/world?useLegacyDatetimeCode=false&serverTimezone=Europe/Moscow");
                settings.put(USER, "root");
                settings.put(Environment.PASS, "Madara2631!");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
                //settings.put(Environment.SHOW_SQL, "true");
                //settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "create-drop");

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);

                sessionFactory = configuration.buildSessionFactory();

            } catch (Exception e) {
                System.out.println("Problem creating session factory");
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
