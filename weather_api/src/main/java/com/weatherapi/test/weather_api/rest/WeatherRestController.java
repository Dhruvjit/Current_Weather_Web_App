package com.weatherapi.test.weather_api.rest;

import com.weatherapi.test.weather_api.config.WeatherDatabaseConfig;
import com.weatherapi.test.weather_api.dao.WeatherDatabase;
import com.weatherapi.test.weather_api.model.Weather;
import com.weatherapi.test.weather_api.service.GetWeatherService;
import com.weatherapi.test.weather_api.service.ReadJsonObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.util.logging.Logger;


@RestController
public class WeatherRestController {

    private final static Logger LOGGER = Logger.getLogger(WeatherDatabaseConfig.class.getName());

    @Autowired
    private WeatherDatabase weatherDatabase;
    @Autowired
    private GetWeatherService getWeatherService;

    /*
    * home page that redirects to register
    * */
    @GetMapping("/")
    public String Home(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "register";
    }

    /*
     * get weather data from open weather map api
     * and set json result in model
     * */
    @GetMapping("/checkWeather")
    public String checkWeather(@RequestParam(name="city", required=true, defaultValue="none") String city, Model model)
            throws IOException,HttpClientErrorException.NotFound {
        Weather result = getWeatherService.getNewWeatherObject(city);
        weatherDatabase.saveData(result);
        //weatherDatabase.updateData("London");
        model.addAttribute("city", city);
        return "weather_history";
    }

}
