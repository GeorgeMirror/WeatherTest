//package com.yourcompany.weather.config;
//
//import com.yourcompany.weather.client.WeatherApiClient;
//import com.yourcompany.weather.stub.StubBuilder;
//import com.yourcompany.weather.util.ResponseParser;
//import com.yourcompany.weather.validation.WeatherValidator;
//import com.yourcompany.weather.runner.TestHooks;
//import lombok.Getter;
//
//import javax.inject.Inject;
//
//@Getter
//public class TestContext {
//
//    private final StubBuilder stubBuilder;
//    private final WeatherApiClient apiClient;
//    private final WeatherValidator validator;
//    private final ResponseParser responseParser;
//
//    @Inject
//    public TestContext() {
//        // Передаём WireMockServer в StubBuilder
//        this.stubBuilder = new StubBuilder(TestHooks.server.getWireMock());
//        this.apiClient = new WeatherApiClient();
//        this.validator = new WeatherValidator();
//        this.responseParser = new ResponseParser();
//    }
//}
