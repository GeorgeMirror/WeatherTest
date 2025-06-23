package com.yourcompany.weather.runner;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.yourcompany.weather.mock.WireMockManager;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;

public class TestHooks {

    public static WireMockManager server;

    @BeforeAll
    public static void setUp() {
        server = new WireMockManager();
        server.start();
        System.out.println("WireMock server started at: " + server.getBaseUrl());
    }

    @AfterAll
    public static void tearDown() {
        if (server != null) {
            server.stop();
            System.out.println("WireMock server stopped.");
        }
    }

    public static WireMockServer getWireMockServer() {
        return server.getWireMock();  // правильный вызов метода из WireMockManager
    }

    public static String getBaseUrl() {
        return server.getBaseUrl();
    }
}
