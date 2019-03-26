package com.weatherapi.test.weather_api.rest;

import com.weatherapi.test.weather_api.config.WeatherDatabaseConfig;
import com.weatherapi.test.weather_api.dao.UserDatabase;
import com.weatherapi.test.weather_api.dao.WeatherDatabase;
import com.weatherapi.test.weather_api.model.UserData;
import com.weatherapi.test.weather_api.model.Weather;
import com.weatherapi.test.weather_api.service.GetWeatherService;
import com.weatherapi.test.weather_api.service.UserService;
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
    @Autowired
    private UserService userService;

    /*
     * home page that redirects to register
     * */
    @GetMapping("/login")
    public String getLoginForm() {
        return "login";
    }

    /*
     * store user registered value in database and redirect to login
     * */
    @PostMapping("/login")
    public String login(@ModelAttribute(name="userData") UserData userData, Model model) {
        if(userService.userExist(userData.getUsername())){
            // if credentials are right only then go further
            if(userService.checkPassword(userData)){
                return "weather_history";
            }
            model.addAttribute("wrongPass","true");
            return "login";
        }else{
            model.addAttribute("userNonExistent","true");
            return "login";
        }
    }

    /*
    * home page that redirects to register
    * */
    @GetMapping("/register")
    public String getRegisterForm() {
        return "register";
    }

    /*
     * store user registered value in database and redirect to login
     * */
    @PostMapping("/register")
    public String register(@ModelAttribute(name="userData") UserData userData) {
        userService.validateUser(userData);
        return "login";
    }

    /*
     * get weather data from open weather map api
     * and set json result in model
     * */
    @GetMapping("/checkWeather")
    public String checkWeather(@RequestParam(name="city", required=true, defaultValue="Oops! you typed wrong url!") String city, Model model)
            throws IOException,HttpClientErrorException.NotFound {
        Weather result = getWeatherService.getNewWeatherObject(city);
        weatherDatabase.saveData(result);
        //weatherDatabase.updateData("London");
        model.addAttribute("city", city);
        return "weather_history";
    }
}
