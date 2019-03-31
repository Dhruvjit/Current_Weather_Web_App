package com.weatherapi.test.weather_api.rest;

import com.weatherapi.test.weather_api.config.WeatherDatabaseConfig;
import com.weatherapi.test.weather_api.model.Weather;
import com.weatherapi.test.weather_api.service.MillisecondsToTimeService;
import com.weatherapi.test.weather_api.service.CrudService;
import com.weatherapi.test.weather_api.service.GetWeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

/*
 * This Controller gets invoked when user gets redirected after
 * successful login. It shows the initial page with weather readings
 * and history tables whose entries are fetched from database
 * */
@Controller
public class WeatherRestController {

    private final static Logger LOGGER = Logger.getLogger(WeatherDatabaseConfig.class.getName());

    @Autowired
    private GetWeatherService getWeatherService;
    @Autowired
    private CrudService crudService;
    @Autowired
    private MillisecondsToTimeService millisecondsToTimeService;

    /*
     * get weather data from open weather map api
     * and set json result in model
     * */
    @GetMapping("/checkWeather")
    public String checkWeather(@RequestParam(name="city", required=true, defaultValue="Oops! you typed wrong url!")
                                           String city, Map<String,Object> map, Model model)
            throws IOException,HttpClientErrorException.NotFound {
        try{
            Weather result = getWeatherService.getNewWeatherObject(city);
            result.setSunrise(millisecondsToTimeService.convertToTime(Long.parseLong(result.getSunrise())));
            result.setSunset(millisecondsToTimeService.convertToTime(Long.parseLong(result.getSunset())));
            map.put("weatherList",crudService.getAllWeatherList());
            model.addAttribute("weather",result);
            model.addAttribute("weatherMap",map);
            model.addAttribute("city",city.substring(0,1).toUpperCase()+city.substring(1));
        }catch (HttpClientErrorException e){
            LOGGER.info("Typed City cannot be found, please type name correctly! Aborting program..");
            return "notfound";
        }
        return "weather-history";
    }
}
