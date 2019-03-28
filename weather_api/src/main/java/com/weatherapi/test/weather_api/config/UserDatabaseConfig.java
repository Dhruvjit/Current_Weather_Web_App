package com.weatherapi.test.weather_api.config;

import com.weatherapi.test.weather_api.model.UserData;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.Objects;
import java.util.logging.Logger;

@org.springframework.context.annotation.Configuration
public class UserDatabaseConfig {
    private final static Logger LOGGER = Logger.getLogger(WeatherDatabaseConfig.class.getName());

    public void CreateUserInDatabase(UserData userData){
        // Create Session Factory
        SessionFactory factory = new Configuration().
                configure("hibernate.cfg.xml").
                addAnnotatedClass(UserData.class).
                buildSessionFactory();
        // Create Session
        Session session = factory.getCurrentSession();
        // Begin Transaction
        session.getTransaction().begin();
        try{
            LOGGER.info("created new UserData...");
            System.out.println(userData);
            session.save(userData);
            session.getTransaction().commit();
            LOGGER.info("new UserData created!");
        } finally {
            factory.close();
        }
    }

    public UserData readUserFromDatabase(String name){
        // Create Session Factory
        SessionFactory factory = new Configuration().
                configure("hibernate.cfg.xml").
                addAnnotatedClass(UserData.class).
                buildSessionFactory();
        // Create Session
        Session session = factory.getCurrentSession();
        // Begin Transaction
        session.getTransaction().begin();
        UserData userData;
        try{
            LOGGER.info("fetching user from database...");
            userData = (UserData) session.get(UserData.class,name);
            System.out.println(userData);
            session.getTransaction().commit();
            LOGGER.info("reading user object done!");
            return userData;
        } catch (NullPointerException e){
            LOGGER.info("No user value returned, username is invalid or is not present");
            return null;
        } finally {
            factory.close();
        }
    }
}
