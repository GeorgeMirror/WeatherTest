package com.yourcompany.weather.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class WeatherApiClient {

    private final ObjectMapper mapper = new ObjectMapper();

    public Response getCurrentWeather(String city, String apiKey) {
        return RestAssured
                .given()
                .queryParam("key", apiKey)
                .queryParam("q", city)
                .when()
                .get("/v1/current.json")
                .then()
                .extract()
                .response();
    }

    public JsonNode parseJson(String body) throws Exception {
        return mapper.readTree(body);
    }
}
