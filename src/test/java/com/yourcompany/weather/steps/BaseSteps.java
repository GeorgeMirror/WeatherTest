package com.yourcompany.weather.steps;

import com.yourcompany.weather.client.WeatherApiClient;
import com.yourcompany.weather.config.TestConfig;
import com.yourcompany.weather.dto.CityWeather;
import com.yourcompany.weather.stub.StubBuilder;
import com.yourcompany.weather.util.ResponseParser;
import com.yourcompany.weather.validation.WeatherValidator;
import com.yourcompany.weather.runner.TestHooks;
import io.restassured.response.Response;

public abstract class BaseSteps {

    protected static final String API_KEY = TestConfig.getApiKey();;

    protected final StubBuilder stubBuilder = new StubBuilder(TestHooks.server.getWireMock());
    protected final WeatherApiClient apiClient = new WeatherApiClient();
    protected final WeatherValidator validator = new WeatherValidator();
    protected final ResponseParser responseParser = new ResponseParser();

    protected Response response;
    protected CityWeather expectedCityWeather;
}
