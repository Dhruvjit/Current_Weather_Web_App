package com.weatherapi.test.weather_api.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;

/*
* this service assists in converting JSON data into node
* */
@Service
public class JsonParserService {
    private ObjectMapper mapper;
    private JsonNode actualObj;

    public JsonNode parseJsonData(String jsonData)
            throws JsonParseException, IOException {

        mapper = new ObjectMapper();
        actualObj = mapper.readTree(jsonData);
        return actualObj;
    }
}
