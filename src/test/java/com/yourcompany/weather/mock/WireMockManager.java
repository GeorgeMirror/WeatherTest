package com.yourcompany.weather.mock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.yourcompany.weather.config.WireMockProperties;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@Slf4j
public class WireMockManager {

    private final WireMockProperties properties;

    @Getter
    private WireMockServer wireMockServer;

    public WireMockManager(WireMockProperties properties) {
        this.properties = properties;
    }

    public void startServer() {
        log.info("Starting WireMock server on port {}", properties.getPort());
        wireMockServer = new WireMockServer(createConfiguration());
        wireMockServer.start();
        configureFor(properties.getHost(), properties.getPort());
        log.info("WireMock server started at {}", getBaseUrl());
    }

    public void stopServer() {
        if (wireMockServer != null && wireMockServer.isRunning()) {
            log.info("Stopping WireMock server...");
            wireMockServer.stop();
            log.info("WireMock server stopped");
        }
    }

    public boolean isRunning() {
        return wireMockServer != null && wireMockServer.isRunning();
    }

    public String getBaseUrl() {
        return properties.getBaseUrl();
    }

    private WireMockConfiguration createConfiguration() {
        return WireMockConfiguration.options().port(properties.getPort());
    }
}
