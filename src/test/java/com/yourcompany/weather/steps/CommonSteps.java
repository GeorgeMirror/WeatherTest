package com.yourcompany.weather.steps;

import com.yourcompany.weather.runner.TestHooks;
import io.cucumber.java.en.Given;
import io.restassured.RestAssured;

public class CommonSteps {

    @Given("WireMock stub is running")
    public void wiremockStubIsRunning() {
        RestAssured.baseURI = TestHooks.server.getBaseUrl();
    }
}
