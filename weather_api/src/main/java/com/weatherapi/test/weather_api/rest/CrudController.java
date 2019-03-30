package com.weatherapi.test.weather_api.rest;

import com.weatherapi.test.weather_api.config.WeatherDatabaseConfig;
import com.weatherapi.test.weather_api.model.Weather;
import com.weatherapi.test.weather_api.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

@Controller
public class CrudController {
    Logger LOGGER = Logger.getLogger(WeatherDatabaseConfig.class.getName());

    @Autowired
    private CrudService crudService;

    @PostMapping("/checkWeather")
    public String doActions(@ModelAttribute Weather weather,
                            BindingResult result, @RequestParam String action,
                            Map<String,Object> map, Model model) throws IOException {
        if(action.equals("add")){
            crudService.add(weather);
        }else if (action.equals("edit")){
            crudService.edit(weather,weather.getCity());
        }else if (action.equals("delete")){
            crudService.delete(weather);
        }else if (action.equals("update")){
            crudService.update(weather);
        }
        return "redirect:checkWeather?city="+weather.getCity();
    }
}
