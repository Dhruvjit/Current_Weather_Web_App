package com.weatherapi.test.weather_api.rest;

import com.weatherapi.test.weather_api.config.WeatherDatabaseConfig;
import com.weatherapi.test.weather_api.model.UserData;
import com.weatherapi.test.weather_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.logging.Logger;

@Controller
public class UserAuthenticationRestController {
    Logger LOGGER = Logger.getLogger(WeatherDatabaseConfig.class.getName());

    @Autowired
    private UserService userService;


    /*
     * home page that redirects to login
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
                return "redirect:weather-history";
            }
            model.addAttribute("wrongPass","true");
            return "login";
        }else{
            LOGGER.info("user does not exists, register yourself!");
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
        return "redirect:login";
    }

    /*
     * after correct login page will redirect to weather-history
     * */
    @GetMapping("/weather-history")
    public String weatherHistory() {
        LOGGER.info("user successfully logged in weather-history page");
        return "weather-history";
    }
}
