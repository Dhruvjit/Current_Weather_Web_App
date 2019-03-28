package com.weatherapi.test.weather_api.dao;

import com.weatherapi.test.weather_api.config.WeatherDatabaseConfig;
import com.weatherapi.test.weather_api.model.Weather;
import com.weatherapi.test.weather_api.service.GetWeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class WeatherDatabase {

    @Autowired
    GetWeatherService getWeatherService;

    ApplicationContext factory = new AnnotationConfigApplicationContext(WeatherDatabaseConfig.class);
    WeatherDatabaseConfig weatherDatabaseConfig = factory.getBean(WeatherDatabaseConfig.class);

    public void saveData(Weather weather){
        weatherDatabaseConfig.CreateCityInDatabase(weather);
    }

    public void updateData(String city) throws IOException {
        Weather weather = getWeatherService.getNewWeatherObject(city);
        weatherDatabaseConfig.updateCityFromDatabase(weather,city);
    }

    public void deleteData(Weather weather){
        weatherDatabaseConfig.deleteCityFromDatabase(weather);
    }

}
