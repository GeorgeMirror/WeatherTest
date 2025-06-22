package com.yourcompany.weather.steps;

import com.yourcompany.weather.client.WeatherApiClient;
import com.yourcompany.weather.dto.CityWeather;
import com.yourcompany.weather.stub.StubBuilder;
import com.yourcompany.weather.util.JsonParser;
import com.yourcompany.weather.util.ResponseParser;
import com.yourcompany.weather.validation.WeatherValidator;
import com.yourcompany.weather.runner.TestHooks;
import io.restassured.response.Response;

public abstract class BaseSteps {

    protected static final String API_KEY = "dummy-api-key";

    protected final StubBuilder stubBuilder = new StubBuilder(TestHooks.server.getWireMock());
    protected final WeatherApiClient apiClient = new WeatherApiClient();
    protected final WeatherValidator validator = new WeatherValidator();
    protected final ResponseParser responseParser = new ResponseParser(new JsonParser());

    protected Response response;
    protected CityWeather expectedCityWeather;
}
