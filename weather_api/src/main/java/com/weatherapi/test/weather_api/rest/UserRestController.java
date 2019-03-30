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

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.logging.Logger;

@Controller
public class UserRestController {
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
    public String login(@ModelAttribute(name="userData") UserData userData,
                        Model model) {
        if(userService.userExist(userData)){
            // default weather on login
            return "redirect:/checkWeather?city=Munich";
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
        userService.save(userData);
        return "redirect:/login";
    }

    /*
     * logout page api
     * */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "logout";
    }
}
