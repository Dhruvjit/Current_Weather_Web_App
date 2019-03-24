package com.weatherapi.test.weather_api.rest;

import com.weatherapi.test.weather_api.dao.SaveWeatherDataToDatabase;
import com.weatherapi.test.weather_api.model.Weather;
import com.weatherapi.test.weather_api.service.ReadJsonObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;


@RestController
public class WeatherRestController {

    @Autowired
    private ReadJsonObjectService readJsonObjectService;

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
    public String checkWeather(@RequestParam(name="city", required=true, defaultValue="Munich") String city, Model model)
            throws IOException {
        String appid = "47c473417a4db382820a8d058f2db194";
        String weatherUrl = "http://api.openweathermap.org/data/2.5/weather?q="+city+"&APPID="+appid+"&units=metric";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(weatherUrl,String.class);
        Weather weather = readJsonObjectService.setWeatherModel(result);

//        TestDatabaseConnection testDatabaseConnection = new TestDatabaseConnection();
//        testDatabaseConnection.testDb();

        SaveWeatherDataToDatabase saveWeatherDataToDatabase = new SaveWeatherDataToDatabase();
        saveWeatherDataToDatabase.saveWeatherInfo(weather);


        model.addAttribute("city", city);
        return "weather_history";
    }

}
