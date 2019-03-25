package com.weatherapi.test.weather_api.config;

import com.weatherapi.test.weather_api.model.Weather;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.Objects;
import java.util.logging.Logger;

@org.springframework.context.annotation.Configuration
public class WeatherDatabaseConfig {
    private final static Logger LOGGER = Logger.getLogger(WeatherDatabaseConfig.class.getName());

    public void CreateCityInDatabase(Weather weather){
        // Create Session Factory
        SessionFactory factory = new Configuration().
                configure("hibernate.cfg.xml").
                addAnnotatedClass(Weather.class).
                buildSessionFactory();
        // Create Session
        Session session = factory.getCurrentSession();
        // Begin Transaction
        session.getTransaction().begin();
        try{
            LOGGER.info("saving the weather data...");
            System.out.println(weather);
            session.saveOrUpdate(weather);
            session.getTransaction().commit();
            LOGGER.info("saving weather object done!");
        } finally {
            factory.close();
        }
    }

    public Weather readCityFromDatabase(String city){
        // Create Session Factory
        SessionFactory factory = new Configuration().
                configure("hibernate.cfg.xml").
                addAnnotatedClass(Weather.class).
                buildSessionFactory();
        // Create Session
        Session session = factory.getCurrentSession();
        // Begin Transaction
        session.getTransaction().begin();
        Weather getWeatherWithCityName;
        try{
            LOGGER.info("reading the weather data...");
            getWeatherWithCityName = (Weather)session.get(Weather.class,city);
            System.out.println(getWeatherWithCityName);
            session.getTransaction().commit();
            LOGGER.info("reading weather object done!");
            return getWeatherWithCityName;
        } catch (NullPointerException e){
            LOGGER.info("No Weather value returned, City name is invalid or is not present");
            return null;
        } finally {
            factory.close();
        }
    }

    public String deleteCityFromDatabase(Weather deleteCityFromDb){
        // Create Session Factory
        SessionFactory factory = new Configuration().
                configure("hibernate.cfg.xml").
                addAnnotatedClass(Weather.class).
                buildSessionFactory();
        // Create Session
        Session session = factory.getCurrentSession();
        // Begin Transaction
        session.getTransaction().begin();
        try{
            LOGGER.info("Deleting the weather data...");
            session.delete(deleteCityFromDb);
            session.getTransaction().commit();
            LOGGER.info("Deleting weather object! done!");
            return "City Deletion Successful";
        }catch (NullPointerException e) {
            LOGGER.info("Weather value Deletion failed! Please check/Input city name correctly");
            return null;
        }
        finally {
            factory.close();
        }
    }

    public Weather updateCityFromDatabase(Weather updatedNewWeather,String city){
        // Create Session Factory
        SessionFactory factory = new Configuration().
                configure("hibernate.cfg.xml").
                addAnnotatedClass(Weather.class).
                buildSessionFactory();
        // Create Session
        Session session = factory.getCurrentSession();
        // Begin Transaction
        session.getTransaction().begin();
        Weather getPreviousWeatherWithCityName;
        try{

            LOGGER.info("reading the weather data...");
            getPreviousWeatherWithCityName = (Weather)session.get(Weather.class,city);
            if(Objects.equals(updatedNewWeather,getPreviousWeatherWithCityName)){
                LOGGER.info("City already exists and parameters are same");
            }else{
                session.clear();
                session.merge(updatedNewWeather);
            }
            session.getTransaction().commit();
            LOGGER.info("City parameters Updated!");
            return updatedNewWeather;
        } catch (NullPointerException e){
            LOGGER.info("No Weather value returned, City name is invalid or is not present");
            return null;
        } finally {
            factory.close();
        }
    }
}
