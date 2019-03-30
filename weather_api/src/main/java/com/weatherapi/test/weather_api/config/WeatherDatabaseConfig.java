package com.weatherapi.test.weather_api.config;

import com.weatherapi.test.weather_api.model.Weather;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.List;
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
            session.save(weather);
            session.getTransaction().commit();
            LOGGER.info("saving weather object done!");
        } finally {
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
        Weather getWeatherWithCityName;
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
                Weather wt = (Weather) session.get(Weather.class,retrievedWeatherData.get(0).getId());
                session.delete(wt);
                session.getTransaction().commit();
                LOGGER.info("Deleting weather object! done!");
                return true;
            }else{
                LOGGER.info("Deletion Failed! Database not Found! Please check/Input city name correctly");
                return false;
            }

        }catch (NullPointerException e) {
            LOGGER.info("Weather value returned null");
            return null;
        }
        finally {
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
                    Weather wt = (Weather) session.get(Weather.class,retrievedWeatherData.get(0).getId());
                    wt.setHeadline(weatherToUpdate.getHeadline());
                    wt.setDescription(weatherToUpdate.getDescription());
                    wt.setCurrentTemp(weatherToUpdate.getCurrentTemp());
                    wt.setMinTemp(weatherToUpdate.getMinTemp());
                    wt.setMaxTemp(weatherToUpdate.getMaxTemp());
                    wt.setSunrise(weatherToUpdate.getSunrise());
                    wt.setSunset(weatherToUpdate.getSunset());
                    session.saveOrUpdate(wt);
                    session.getTransaction().commit();
                    LOGGER.info("City parameters Updated!");
                    return true;
                }else{
                    LOGGER.info("No Weather value returned, City name is invalid or is not present");
                    return false;
            }
            } catch (NullPointerException e){
                LOGGER.info("Weather value returned null");
                return false;
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
                Weather recent = (Weather) session.get(Weather.class,retrievedWeatherData.get(0).getId());
                recent.setHeadline(latestWeather.getHeadline());
                recent.setDescription(latestWeather.getDescription());
                recent.setCurrentTemp(latestWeather.getCurrentTemp());
                recent.setMinTemp(latestWeather.getMinTemp());
                recent.setMaxTemp(latestWeather.getMaxTemp());
                recent.setSunrise(latestWeather.getSunrise());
                recent.setSunset(latestWeather.getSunset());
                session.merge(recent);
                session.getTransaction().commit();
                LOGGER.info("City parameters Updated!");
                return true;
            }else{
                LOGGER.info("No Weather value returned, City name is invalid or is not present");
                return false;
            }
        } catch (NullPointerException e){
            LOGGER.info("Weather value returned null");
            return false;
        } finally {
            factory.close();
        }
    }
}
