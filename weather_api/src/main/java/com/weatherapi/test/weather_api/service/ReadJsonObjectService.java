package com.weatherapi.test.weather_api.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.weatherapi.test.weather_api.config.WeatherParamsConfig;
import com.weatherapi.test.weather_api.model.Weather;
import com.weatherapi.test.weather_api.model.WeatherQueryParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ReadJsonObjectService {

    ApplicationContext factory = new AnnotationConfigApplicationContext(WeatherParamsConfig.class);
    WeatherQueryParams weatherQueryFactory = factory.getBean(WeatherQueryParams.class);
    Weather weatherFactory = factory.getBean(Weather.class);

    @Autowired
    private JsonParserService jsonParserService;

    public Weather setWeatherModel(String result)
            throws IOException{
        weatherQueryFactory.setResult(jsonParserService.parseJsonData(result));
        JsonNode jsonResultObject = weatherQueryFactory.getResult();

        // Extract string from json result
        weatherFactory.setIcon(jsonResultObject.get("weather").get(0).get("icon").toString());
        weatherFactory.setDescription(jsonResultObject.get("weather").get(0).get("description").toString());
        weatherFactory.setHeadline(jsonResultObject.get("weather").get(0).get("main").toString());
        weatherFactory.setCurrentTemp(jsonResultObject.get("main").get("temp").toString());
        weatherFactory.setMinTemp(jsonResultObject.get("main").get("temp_min").toString());
        weatherFactory.setMaxTemp(jsonResultObject.get("main").get("temp_max").toString());
        weatherFactory.setSunrise(jsonResultObject.get("sys").get("sunrise").toString());
        weatherFactory.setSunset(jsonResultObject.get("sys").get("sunset").toString());
        weatherFactory.setCity(jsonResultObject.get("name").toString());
        weatherFactory.setUnit("Celsius");
        return weatherFactory;
    }
}
