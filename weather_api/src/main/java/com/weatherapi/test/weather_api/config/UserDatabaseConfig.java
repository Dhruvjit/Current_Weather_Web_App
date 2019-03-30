package com.weatherapi.test.weather_api.config;

import com.weatherapi.test.weather_api.model.UserData;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.List;
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

    public List<UserData> readUserFromDatabase(UserData userData){
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
            LOGGER.info("fetching user from database...");
            System.out.println(userData);
            Query query = session.createQuery("from UserData u where u.name = :name");
            query.setParameter("name",userData.getName());
            List<UserData> retrievedUserData = query.list();
            session.getTransaction().commit();
            LOGGER.info("reading user object done!");
            return retrievedUserData;
        } catch (NullPointerException e){
            LOGGER.info("No user value returned, name is invalid or is not present");
            return null;
        } finally {
            factory.close();
        }
    }
}
