package com.yourcompany.weather.runner;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.yourcompany.weather.mock.WireMockManager;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;

public class TestHooks {

    public static final WireMockManager server = new WireMockManager();

    @BeforeAll
    public static void beforeAll() {
        server.start();
    }

    @AfterAll
    public static void afterAll() {
        server.stop();
    }

    // Если нужно, предоставляем метод получения WireMockServer
    public static WireMockServer getWireMockServer() {
        return server.getWireMockServer();
    }

    // Также для удобства возвращаем baseUrl
    public static String getBaseUrl() {
        return server.getBaseUrl();
    }
}
