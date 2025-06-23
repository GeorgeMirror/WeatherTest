package com.yourcompany.weather.config;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.yourcompany.weather.client.WeatherApiClient;
import com.yourcompany.weather.stub.StubBuilder;
import com.yourcompany.weather.util.ResponseParser;
import com.yourcompany.weather.validation.WeatherValidator;
import lombok.Getter;

@Getter
public class TestContext {

    private final StubBuilder stubBuilder;
    private final WeatherApiClient apiClient;
    private final WeatherValidator validator;
    private final ResponseParser responseParser;

    public TestContext(WireMockServer wireMockServer) {
        this.stubBuilder = new StubBuilder(wireMockServer);
        this.apiClient = new WeatherApiClient();
        this.validator = new WeatherValidator();
        this.responseParser = new ResponseParser();
    }

}
