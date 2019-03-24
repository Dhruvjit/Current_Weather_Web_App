package com.weatherapi.test.weather_api.config;

import com.weatherapi.test.weather_api.dao.SaveWeatherData;
import com.weatherapi.test.weather_api.model.Weather;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Bean;


@org.springframework.context.annotation.Configuration
public class WeatherDatabaseConfig {

    public void weatherDatabaseConfig(Weather weather){
        // Create Session Factory
        SessionFactory factory = new Configuration().
                configure("hibernate.cfg.xml").
                addAnnotatedClass(Weather.class).
                buildSessionFactory();
        // Create Session
        Session session = factory.getCurrentSession();
        SaveWeatherData saveWeatherData;
        // Begin Transaction
        session.getTransaction().begin();
        try{
            System.out.println("saving the weather data...");
            System.out.println(weather);
            session.save(weather);
            session.getTransaction().commit();
            System.out.println("saving weather object done!");
        } finally {
            factory.close();
        }
    }

}
