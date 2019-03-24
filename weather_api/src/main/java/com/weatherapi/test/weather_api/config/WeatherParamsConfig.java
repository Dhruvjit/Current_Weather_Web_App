package com.weatherapi.test.weather_api.config;

import com.weatherapi.test.weather_api.model.Weather;
import com.weatherapi.test.weather_api.model.WeatherQueryParams;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WeatherParamsConfig {

    @Bean
    public WeatherQueryParams getWeatherQueryParams(){
        return new WeatherQueryParams();
    }

    @Bean
    public Weather getWeatherInfo(){
        return new Weather();
    }

    @Bean
    public WeatherDatabaseConfig getWeatherDatabaseConfig(){return new WeatherDatabaseConfig();}

}
