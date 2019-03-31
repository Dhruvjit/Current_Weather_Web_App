package com.weatherapi.test.weather_api.service;

import com.weatherapi.test.weather_api.dao.CrudDao;
import com.weatherapi.test.weather_api.model.Weather;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CrudServiceImplementTest extends AbstractTestNGSpringContextTests {

    private Weather weather;

    @Mock
    private GetWeatherService getWeatherService;

    @Mock
    private CrudDao crudDao;

    @InjectMocks
    private CrudServiceImplementTest crudServiceImplement;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void verifyAdd() throws Exception {
        Mockito.when(getWeatherService.getNewWeatherObject("Munich")).thenReturn(new Weather());
    }
}
