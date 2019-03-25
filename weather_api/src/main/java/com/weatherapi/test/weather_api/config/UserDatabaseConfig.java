package com.weatherapi.test.weather_api.config;

import com.weatherapi.test.weather_api.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.Objects;
import java.util.logging.Logger;

@org.springframework.context.annotation.Configuration
public class UserDatabaseConfig {
    private final static Logger LOGGER = Logger.getLogger(WeatherDatabaseConfig.class.getName());

    public void CreateUserInDatabase(User user){
        // Create Session Factory
        SessionFactory factory = new Configuration().
                configure("hibernate.cfg.xml").
                addAnnotatedClass(User.class).
                buildSessionFactory();
        // Create Session
        Session session = factory.getCurrentSession();
        // Begin Transaction
        session.getTransaction().begin();
        try{
            LOGGER.info("created new User...");
            System.out.println(user);
            session.saveOrUpdate(user);
            session.getTransaction().commit();
            LOGGER.info("new User created!");
        } finally {
            factory.close();
        }
    }

    public User readUserFromDatabase(String name){
        // Create Session Factory
        SessionFactory factory = new Configuration().
                configure("hibernate.cfg.xml").
                addAnnotatedClass(User.class).
                buildSessionFactory();
        // Create Session
        Session session = factory.getCurrentSession();
        // Begin Transaction
        session.getTransaction().begin();
        User user;
        try{
            LOGGER.info("fetching user from database...");
            user = (User) session.get(User.class,name);
            System.out.println(user);
            session.getTransaction().commit();
            LOGGER.info("reading weather object done!");
            return user;
        } catch (NullPointerException e){
            LOGGER.info("No Weather value returned, City name is invalid or is not present");
            return null;
        } finally {
            factory.close();
        }
    }

    public User updateUserInDatabase(User updatedNewUser, String user){
        // Create Session Factory
        SessionFactory factory = new Configuration().
                configure("hibernate.cfg.xml").
                addAnnotatedClass(User.class).
                buildSessionFactory();
        // Create Session
        Session session = factory.getCurrentSession();
        // Begin Transaction
        session.getTransaction().begin();
        User previousUser;
        try{
            LOGGER.info("reading the user data...");
            previousUser = (User) session.get(User.class,user);
            if(Objects.equals(updatedNewUser,previousUser)){
                LOGGER.info("user already exists and parameters are same");
            }else{
                session.clear();
                session.merge(updatedNewUser);
            }
            session.getTransaction().commit();
            LOGGER.info("current user parameters Updated!");
            return updatedNewUser;
        } catch (NullPointerException e){
            LOGGER.info("no user value returned, username is invalid or is not present");
            return null;
        } finally {
            factory.close();
        }
    }
}
