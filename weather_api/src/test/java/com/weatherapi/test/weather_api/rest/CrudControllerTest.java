//package com.weatherapi.test.weather_api.rest;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.weatherapi.test.weather_api.model.Weather;
//import com.weatherapi.test.weather_api.service.CrudService;
//import com.weatherapi.test.weather_api.service.GetWeatherService;
//import com.weatherapi.test.weather_api.service.MillisecondsToTimeService;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.util.ArrayList;
//
//import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
//
//@AutoConfigureMockMvc
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest
//public class CrudControllerTest extends AbstractTestNGSpringContextTests {
//
//    @Mock
//    private CrudService crudService;
//
//    @Autowired
//    private MockMvc mockMVC;
//
//    @InjectMocks
//    private CrudController crudController;
//
//    @Before
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//        mockMVC = MockMvcBuilders
//                .standaloneSetup(crudController)
//                .build();
//    }
//
//    @Test
//    public void testCheckWeather() throws Exception {
//        ObjectMapper mapper = new ObjectMapper();
//        Weather wt = new Weather();
//        Mockito.when(crudService.getAllWeatherList()).thenReturn(new ArrayList());
//        MvcResult mvcResult = mockMVC.perform(post("/checkWeather")
//                .content(mapper.writeValueAsString(wt)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andReturn();
//    }
//}
