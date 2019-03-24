package com.weatherapi.test.weather_api.dao;

import com.weatherapi.test.weather_api.model.Weather;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SaveWeatherDataToDatabase {

    public void saveWeatherInfo(Weather weather){
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
            System.out.println("saving the weather data...");
            session.save(weather);
            session.getTransaction().commit();
            System.out.println("saving weather object done!");
        } finally {
            factory.close();
        }

    }

}
