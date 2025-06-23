package com.yourcompany.weather.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestConfig {

    private static final String CONFIG_PATH = "config/wiremock.properties";
    private static final Properties properties = new Properties();

    static {
        try (InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream(CONFIG_PATH)) {
            if (stream != null) {
                properties.load(stream);
            } else {
                throw new IllegalStateException("Config file not found: " + CONFIG_PATH);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config", e);
        }
    }

    public static String getApiKey() {
        return properties.getProperty("api.key", "dummy-api-key");
    }
}
