package com.weatherapi.test.weather_api.config;

import com.weatherapi.test.weather_api.model.Weather;
import com.weatherapi.test.weather_api.model.WeatherQuery;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
* This Class contains all the beans from config package
* they can be injected in any service methods using this one class
* */
@Configuration
public class WeatherParamsConfig {

    @Bean
    public WeatherQuery getWeatherQueryParams(){
        return new WeatherQuery();
    }

    @Bean
    public Weather getWeatherInfo(){
        return new Weather();
    }

    @Bean
    public WeatherDatabaseConfig getWeatherDatabaseConfig(){return new WeatherDatabaseConfig();}

    @Bean
    public UserDatabaseConfig getUserDatabaseConfig(){return new UserDatabaseConfig();}
}
