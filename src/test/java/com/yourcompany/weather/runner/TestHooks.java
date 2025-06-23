package com.yourcompany.weather.runner;

import com.yourcompany.weather.config.WireMockProperties;
import com.yourcompany.weather.mapper.DataTableDefinitions;
import com.yourcompany.weather.mock.WireMockServerManager;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;

public class TestHooks {

    public static final WireMockServerManager server = new WireMockServerManager(new WireMockProperties());

    @BeforeAll
    public static void init() {
        server.start();
    }

    @Before
    public void registerDataTableTypes() {
        io.cucumber.core.options.Constants.PLUGIN_PUBLISH_QUIET_PROPERTY_NAME.equals("true"); // workaround to load class
        new DataTableDefinitions(); // Force-load definitions (Cucumber магия)
    }


    @AfterAll
    public static void cleanup() {
        server.stop();
    }
}
