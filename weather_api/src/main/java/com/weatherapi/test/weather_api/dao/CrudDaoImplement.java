package com.weatherapi.test.weather_api.dao;

import com.weatherapi.test.weather_api.config.WeatherDatabaseConfig;
import com.weatherapi.test.weather_api.model.UserData;
import com.weatherapi.test.weather_api.model.Weather;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class CrudDaoImplement implements CrudDao{

    // Create Session Factory
    SessionFactory factory = new Configuration().
            configure("hibernate.cfg.xml").
            addAnnotatedClass(UserData.class).
            buildSessionFactory();
    // Create Session
    Session session = factory.getCurrentSession();

    ApplicationContext beanFactory = new AnnotationConfigApplicationContext(WeatherDatabaseConfig.class);
    WeatherDatabaseConfig weatherDatabaseConfig = beanFactory.getBean(WeatherDatabaseConfig.class);

    @Override
    public void add(Weather weather){
        weatherDatabaseConfig.CreateCityInDatabase(weather);
    }

    @Override
    public void edit(Weather weather){
        session.getTransaction().begin();
        session.update(weather);
    }

    @Override
    public void delete(String city){
        session.getTransaction().begin();
        session.delete(city);
    }

    @Override
    public Weather getWeather(String city){
        session.getTransaction().begin();
        return (Weather) session.get(Weather.class,"city");
    }

    @Override
    public List getAllWeatherList(){
        session.getTransaction().begin();
        return session.createQuery("from Weather").list();
    }
}
