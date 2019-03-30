package com.weatherapi.test.weather_api.dao;

import com.weatherapi.test.weather_api.config.WeatherDatabaseConfig;
import com.weatherapi.test.weather_api.model.Weather;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class CrudDaoImplement implements CrudDao{

    ApplicationContext beanFactory = new AnnotationConfigApplicationContext(WeatherDatabaseConfig.class);
    WeatherDatabaseConfig weatherDatabaseConfig = beanFactory.getBean(WeatherDatabaseConfig.class);

    @Override
    public void add(Weather weather){
        weatherDatabaseConfig.CreateCityInDatabase(weather);
    }

    @Override
    public void edit(Weather weather,String city){
        weatherDatabaseConfig.editCityFromDatabase(weather,city);
    }

    @Override
    public void update(Weather weather){
        weatherDatabaseConfig.updateCityFromDatabase(weather);
    }

    @Override
    public void delete(Weather weather){
        weatherDatabaseConfig.deleteCityFromDatabase(weather);
    }

    @Override
    public List getAllWeatherList(){
        return weatherDatabaseConfig.getAllCitiesFromDatabase();
    }
}
