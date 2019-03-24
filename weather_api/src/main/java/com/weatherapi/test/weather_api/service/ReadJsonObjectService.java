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
        weatherFactory.setIcon(jsonResultObject.get("weather").get(0).get("icon").toString().replaceAll("^\"|\"$",""));
        weatherFactory.setDescription(jsonResultObject.get("weather").get(0).get("description").toString().replaceAll("^\"|\"$",""));
        weatherFactory.setHeadline(jsonResultObject.get("weather").get(0).get("main").toString().replaceAll("^\"|\"$",""));
        weatherFactory.setCurrentTemp(jsonResultObject.get("main").get("temp").toString().replaceAll("^\"|\"$",""));
        weatherFactory.setMinTemp(jsonResultObject.get("main").get("temp_min").toString().replaceAll("^\"|\"$",""));
        weatherFactory.setMaxTemp(jsonResultObject.get("main").get("temp_max").toString().replaceAll("^\"|\"$",""));
        weatherFactory.setSunrise(jsonResultObject.get("sys").get("sunrise").toString().replaceAll("^\"|\"$",""));
        weatherFactory.setSunset(jsonResultObject.get("sys").get("sunset").toString().replaceAll("^\"|\"$",""));
        weatherFactory.setCity(jsonResultObject.get("name").toString().replaceAll("^\"|\"$",""));
        weatherFactory.setUnit("Celsius");
        return weatherFactory;
    }
}
