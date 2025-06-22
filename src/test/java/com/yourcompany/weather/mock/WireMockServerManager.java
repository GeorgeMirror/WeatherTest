package com.yourcompany.weather.mock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.yourcompany.weather.config.WireMockProperties;
import lombok.Getter;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class WireMockServerManager {

    private final WireMockProperties properties;
    @Getter
    private WireMockServer wireMock;

    public WireMockServerManager(WireMockProperties properties) {
        this.properties = properties;
    }

    public void start() {
        wireMock = new WireMockServer(WireMockConfiguration.options().port(properties.getPort()));
        wireMock.start();
        configureFor(properties.getHost(), properties.getPort());
    }

    public void stop() {
        if (wireMock != null && wireMock.isRunning()) {
            wireMock.stop();
        }
    }

    public String getBaseUrl() {
        return properties.getBaseUrl();
    }
}

