package com.weatherapi.test.weather_api.rest;

import com.weatherapi.test.weather_api.model.Weather;
import com.weatherapi.test.weather_api.service.CrudService;
import com.weatherapi.test.weather_api.service.GetWeatherService;
import com.weatherapi.test.weather_api.service.MillisecondsToTimeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class WeatherRestControllerTest extends AbstractTestNGSpringContextTests {

    private Weather weather;

    @Mock
    private GetWeatherService getWeatherService;
    @Mock
    private CrudService crudService;
    @Mock
    private MillisecondsToTimeService millisecondsToTimeService;

    @Autowired
    private MockMvc mockMVC;

    @InjectMocks
    private WeatherRestController weatherRestController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void testCheckWeather() throws Exception {
        Mockito.when(getWeatherService.getNewWeatherObject("Munich")).thenReturn(new Weather());
        Mockito.when(crudService.getAllWeatherList()).thenReturn(null);
        Mockito.when(millisecondsToTimeService.convertToTime(1553942844)).thenReturn(null);
        mockMVC.perform(MockMvcRequestBuilders.get("/checkWeather?city=Munich")).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andExpect(MockMvcResultMatchers.view().name("weather-history"));
    }
}
