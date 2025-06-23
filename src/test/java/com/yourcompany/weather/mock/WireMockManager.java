package com.yourcompany.weather.mock;

import com.yourcompany.weather.config.WireMockProperties;
import com.github.tomakehurst.wiremock.WireMockServer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WireMockManager {

    private final WireMockServer wireMockServer;
    private final WireMockProperties properties;

    public WireMockManager() {
        this.properties = new WireMockProperties();
        this.wireMockServer = new WireMockServer(properties.getPort());
    }

    /**
     * Запускает WireMockServer, если он еще не запущен.
     */
    public synchronized void start() {
        if (!wireMockServer.isRunning()) {
            log.info("Starting WireMockServer on port {}", properties.getPort());
            wireMockServer.start();
            log.info("WireMockServer started.");
        } else {
            log.info("WireMockServer already running.");
        }
    }

    /**
     * Останавливает WireMockServer, если он запущен.
     */
    public synchronized void stop() {
        if (wireMockServer.isRunning()) {
            log.info("Stopping WireMockServer.");
            wireMockServer.stop();
            log.info("WireMockServer stopped.");
        } else {
            log.info("WireMockServer is not running.");
        }
    }

    /**
     * Возвращает инстанс WireMockServer. Гарантирует, что сервер запущен.
     * Если сервер не запущен, запускает его автоматически.
     */
    public synchronized WireMockServer getWireMock() {
        if (!wireMockServer.isRunning()) {
            log.warn("WireMockServer not running, starting it now.");
            start();
        }
        return wireMockServer;
    }

    /**
     * Возвращает базовый URL сервера, например http://localhost:8089
     */
    public String getBaseUrl() {
        return properties.getBaseUrl();
    }
}
