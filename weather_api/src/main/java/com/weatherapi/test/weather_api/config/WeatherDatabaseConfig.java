package com.weatherapi.test.weather_api.config;

import com.weatherapi.test.weather_api.model.Weather;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.dao.DuplicateKeyException;

import java.util.List;
import java.util.logging.Logger;

@org.springframework.context.annotation.Configuration
public class WeatherDatabaseConfig {
    private final static Logger LOGGER = Logger.getLogger(WeatherDatabaseConfig.class.getName());

    public boolean AddCityInDatabase(Weather weather){
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
            Query query = session.createQuery("from Weather wt where wt.city = :city");
            query.setParameter("city",weather.getCity());
            List<Weather> retrievedWeatherData = query.list();
            if(retrievedWeatherData.size()!=0){
                System.out.println(weather);
                LOGGER.info("weather for this city already exists, try saving another city");
                return false;
            }else{
                session.save(weather);
                session.getTransaction().commit();
                LOGGER.info("Saving Failed! Database not Found! Please check/Input city name correctly");
                return true;
            }
        }finally {
            factory.close();
        }
    }

    public List<Weather> getAllCitiesFromDatabase(){
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
            LOGGER.info("reading the weather data...");
            List<Weather> allWeatherList = session.createQuery("from Weather").list();
            return allWeatherList;
        } catch (NullPointerException e){
            LOGGER.info("No Weather value returned, database is empty");
            return null;
        } finally {
            factory.close();
        }
    }

    public Boolean deleteCityFromDatabase(Weather deleteCityFromDb){
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
            Query query = session.createQuery("from Weather wt where wt.city = :city");
            query.setParameter("city",deleteCityFromDb.getCity());
            List<Weather> retrievedWeatherData = query.list();
            if(retrievedWeatherData.size()!=0){
                session.delete(retrievedWeatherData.get(0));
                session.getTransaction().commit();
                LOGGER.info("Deleting weather object! done!");
                return true;
            }else{
                LOGGER.info("Deletion Failed! Database not Found! Please check/Input city name correctly");
                return false;
            }
        }finally {
            factory.close();
        }
    }

    public boolean editCityFromDatabase(Weather weatherToUpdate, String city){
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
            LOGGER.info("reading the weather data...");
            Query query = session.createQuery("from Weather wt where wt.city = :city");
            query.setParameter("city",weatherToUpdate.getCity());
            List<Weather> retrievedWeatherData = query.list();
            if(retrievedWeatherData.size()!=0){
                    retrievedWeatherData.get(0).setHeadline(weatherToUpdate.getHeadline());
                    retrievedWeatherData.get(0).setDescription(weatherToUpdate.getDescription());
                    retrievedWeatherData.get(0).setCurrentTemp(weatherToUpdate.getCurrentTemp());
                    retrievedWeatherData.get(0).setMinTemp(weatherToUpdate.getMinTemp());
                    retrievedWeatherData.get(0).setMaxTemp(weatherToUpdate.getMaxTemp());
                    retrievedWeatherData.get(0).setSunrise(weatherToUpdate.getSunrise());
                    retrievedWeatherData.get(0).setSunset(weatherToUpdate.getSunset());
                    session.saveOrUpdate(retrievedWeatherData.get(0));
                    session.getTransaction().commit();
                    LOGGER.info("City parameters Updated!");
                    return true;
                }else{
                    LOGGER.info("No Weather value returned, City name is invalid or is not present");
                    return false;
            }
            } finally {
            factory.close();
        }
    }

    public boolean updateCityFromDatabase(Weather latestWeather){
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
            LOGGER.info("updating the weather data...");
            Query query = session.createQuery("from Weather wt where wt.city = :city");
            query.setParameter("city",latestWeather.getCity());
            List<Weather> retrievedWeatherData = query.list();
            if(retrievedWeatherData.size()!=0){
                retrievedWeatherData.get(0).setHeadline(latestWeather.getHeadline());
                retrievedWeatherData.get(0).setDescription(latestWeather.getDescription());
                retrievedWeatherData.get(0).setCurrentTemp(latestWeather.getCurrentTemp());
                retrievedWeatherData.get(0).setMinTemp(latestWeather.getMinTemp());
                retrievedWeatherData.get(0).setMaxTemp(latestWeather.getMaxTemp());
                retrievedWeatherData.get(0).setSunrise(latestWeather.getSunrise());
                retrievedWeatherData.get(0).setSunset(latestWeather.getSunset());
                session.merge(retrievedWeatherData.get(0));
                session.getTransaction().commit();
                LOGGER.info("City parameters Updated!");
                return true;
            }else{
                LOGGER.info("No Weather value returned, City name is invalid or is not present");
                return false;
            }
        } finally {
            factory.close();
        }
    }
}
