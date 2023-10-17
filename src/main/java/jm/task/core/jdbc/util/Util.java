package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class Util {
    private static SessionFactory sessionFactory;
    private static final String URLFIXED =
            "jdbc:mysql://localhost:3306/ppbd?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123";

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = getConfiguration();
                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                System.out.println("session factory created"); //
            } catch (Exception e) {
                System.out.println("Problem creating session factory"); //
                throw new RuntimeException(e);
            }
        }
        return sessionFactory;
    }

    private static Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        Properties settings = new Properties();

        settings.put(Environment.URL, URLFIXED);
        settings.put(Environment.USER, USERNAME);
        settings.put(Environment.PASS, PASSWORD);
        settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
        settings.put(Environment.SHOW_SQL, "true");
        settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        settings.put(Environment.HBM2DDL_AUTO, "");

        configuration.setProperties(settings);
        return configuration;
    }
}

