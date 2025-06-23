package com.yourcompany.weather.steps;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.yourcompany.weather.client.WeatherApiClient;
import com.yourcompany.weather.config.TestContext;
import com.yourcompany.weather.dto.CityWeather;
import com.yourcompany.weather.runner.TestHooks;
import com.yourcompany.weather.stub.StubBuilder;
import com.yourcompany.weather.util.ResponseParser;
import com.yourcompany.weather.validation.WeatherValidator;
import io.restassured.response.Response;

public abstract class BaseSteps {

    protected static final String API_KEY = System.getProperty("weather.api.key", "default-key");

    protected final TestContext context;
    protected final StubBuilder stubBuilder;
    protected final WeatherApiClient apiClient;
    protected final WeatherValidator validator;
    protected final ResponseParser responseParser;

    protected Response response;
    protected CityWeather expectedCityWeather;

    public BaseSteps() {
        // Здесь нужно получить WireMockServer, например, из TestHooks
        WireMockServer wireMockServer = TestHooks.server.getWireMock();

        this.context = new TestContext(wireMockServer);

        this.stubBuilder = context.getStubBuilder();
        this.apiClient = context.getApiClient();
        this.validator = context.getValidator();
        this.responseParser = context.getResponseParser();
    }
}
