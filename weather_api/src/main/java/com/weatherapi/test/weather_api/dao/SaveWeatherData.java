package com.weatherapi.test.weather_api.dao;

import com.weatherapi.test.weather_api.config.WeatherDatabaseConfig;
import com.weatherapi.test.weather_api.model.Weather;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SaveWeatherData {

    ApplicationContext factory = new AnnotationConfigApplicationContext(WeatherDatabaseConfig.class);
    WeatherDatabaseConfig weatherDatabaseConfig = factory.getBean(WeatherDatabaseConfig.class);

    public void saveData(Weather weather){

    }

}
