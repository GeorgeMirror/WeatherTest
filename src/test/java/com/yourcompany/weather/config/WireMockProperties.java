package com.yourcompany.weather.config;

import lombok.Getter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Getter
public class WireMockProperties {

    private final int port;
    private final String host;
    private final String baseUrl;

    public WireMockProperties() {
        Properties props = new Properties();
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("config/wiremock.properties")) {
            if (in == null) {
                throw new RuntimeException("wiremock.properties not found in /config");
            }
            props.load(in);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load wiremock.properties", e);
        }

        this.port = Integer.parseInt(props.getProperty("wiremock.port", "8089"));
        this.host = props.getProperty("wiremock.host", "localhost");
        this.baseUrl = String.format("http://%s:%d", host, port);
    }
}
