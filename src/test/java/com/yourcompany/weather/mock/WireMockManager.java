package com.yourcompany.weather.mock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.yourcompany.weather.config.WireMockProperties;
import lombok.Getter;

@Getter
public class WireMockManager {

    private final WireMockServer wireMockServer;

    public WireMockManager() {
        WireMockProperties props = new WireMockProperties();
        wireMockServer = new WireMockServer(
                WireMockConfiguration.options()
                        .port(props.getPort())
                        .bindAddress(props.getHost())
        );
    }

    public void start() {
        if (!wireMockServer.isRunning()) {
            wireMockServer.start();
        }
    }

    public void stop() {
        if (wireMockServer.isRunning()) {
            wireMockServer.stop();
        }
    }

    public boolean isRunning() {
        return wireMockServer.isRunning();
    }

    public String getBaseUrl() {
        return "http://" + wireMockServer.getOptions().bindAddress() + ":" + wireMockServer.port();
    }
}
