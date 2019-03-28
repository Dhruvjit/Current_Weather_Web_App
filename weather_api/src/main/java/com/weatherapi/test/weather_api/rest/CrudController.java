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

import java.util.Map;
import java.util.logging.Logger;

@Controller
public class CrudController {
    Logger LOGGER = Logger.getLogger(WeatherDatabaseConfig.class.getName());

    @Autowired
    private CrudService crudService;

    @RequestMapping("/index")
    public String crudForm(Map<String,Object> map){
        Weather weather = new Weather();
        map.put("weather",weather);
        map.put("weatherList",crudService.getAllWeatherList());
        return "weather-history";
    }

    @PostMapping("/weatherAction")
    public String doActions(@ModelAttribute Weather weather,
                            BindingResult result, @RequestParam String action,
                            Map<String,Object> map, Model model){
        Weather weatherResult = new Weather();
        if(action.equals("add")){
            crudService.add(weather);
            weatherResult = weather;
        }else if (action.equals("edit")){
            crudService.edit(weather);
            weatherResult = weather;
        }else if (action.equals("delete")){
            crudService.delete(weather.getCity());
            weatherResult = new Weather();
        }else if (action.equals("search")){
            Weather foundWeatherForCity = crudService.getWeather(weather.getCity());
            weatherResult = foundWeatherForCity!=null ? foundWeatherForCity : new Weather();
        }
        map.put("weather",weatherResult);
        map.put("weatherList",crudService.getAllWeatherList());
        model.addAttribute("weatherMap",map);
        return "weather-history";
    }

}
