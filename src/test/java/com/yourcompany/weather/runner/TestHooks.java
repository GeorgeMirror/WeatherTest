package com.yourcompany.weather.runner;

import com.yourcompany.weather.config.WireMockProperties;
import com.yourcompany.weather.mock.WireMockServerManager;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;

public class TestHooks {

    public static final WireMockServerManager server = new WireMockServerManager(new WireMockProperties());

    @BeforeAll
    public static void init() {
        server.start();
    }

    @AfterAll
    public static void cleanup() {
        server.stop();
    }
}
