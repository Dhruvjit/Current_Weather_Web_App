package com.weatherapi.test.weather_api.rest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.weatherapi.test.weather_api.model.WeatherQueryParams;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Controller
public class WeatherRestController {

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
     * */
    @GetMapping("/checkWeather")
    public String checkWeather(@RequestParam(name="city", required=true, defaultValue="Munich") String city, Model model)
        throws JsonParseException, IOException {
        String appid = "b6907d289e10d714a6e88b30761fae22";
        String weatherUrl = "https://samples.openweathermap.org/data/2.5/weather?q="+city+"&appid="+appid;
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(weatherUrl,String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(result);
        model.addAttribute("city", city);
        return "weather_history";
    }

}
