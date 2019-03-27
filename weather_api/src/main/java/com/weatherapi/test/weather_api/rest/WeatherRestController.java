package com.weatherapi.test.weather_api.rest;

import com.weatherapi.test.weather_api.config.WeatherDatabaseConfig;
import com.weatherapi.test.weather_api.dao.UserDatabase;
import com.weatherapi.test.weather_api.dao.WeatherDatabase;
import com.weatherapi.test.weather_api.model.Weather;
import com.weatherapi.test.weather_api.service.GetWeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.util.logging.Logger;


@Controller
public class WeatherRestController {

    private final static Logger LOGGER = Logger.getLogger(WeatherDatabaseConfig.class.getName());

    @Autowired
    private UserDatabase userDatabase;
    @Autowired
    private WeatherDatabase weatherDatabase;
    @Autowired
    private GetWeatherService getWeatherService;

    /*
     * get weather data from open weather map api
     * and set json result in model
     * */
    @GetMapping("/checkWeather")
    public String checkWeather(@RequestParam(name="city", required=true, defaultValue="Oops! you typed wrong url!") String city, Model model)
            throws IOException,HttpClientErrorException.NotFound {
        Weather result = getWeatherService.getNewWeatherObject(city);
        //weatherDatabase.saveData(result);
        //weatherDatabase.updateData("London");
        city=city+"'s "+"weather";
        //String icon = "http://openweathermap.org/img/w/" + result.getIcon() + ".png";

        model.addAttribute("city",city);
        model.addAttribute("headline", result.getHeadline());
        model.addAttribute("description", result.getDescription());
        model.addAttribute("icon", result.getIcon());
        model.addAttribute("currentTemp", result.getCurrentTemp()+"°c");
        model.addAttribute("minTemp", result.getMinTemp());
        model.addAttribute("maxTemp", result.getMaxTemp());
        model.addAttribute("sunrise", result.getSunrise());
        model.addAttribute("sunset", result.getSunset());
        model.addAttribute("unit", result.getUnit());
        return "weather-history";
    }
}
