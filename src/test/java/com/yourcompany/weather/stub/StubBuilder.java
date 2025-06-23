package com.yourcompany.weather.stub;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.yourcompany.weather.dto.WeatherResponse;
import com.yourcompany.weather.enums.HttpStatus;
import lombok.SneakyThrows;

public class StubBuilder {

    private final WireMock wireMock;
    private final ObjectMapper mapper = new ObjectMapper();

    public StubBuilder(WireMockServer server) {
        this.wireMock = new WireMock(server.port()); // создаём клиента по порту WireMock-сервера
    }

    @SneakyThrows
    public void stubSuccess(String city, WeatherResponse response) {
        String json = mapper.writeValueAsString(response);

        wireMock.register(
                WireMock.get(WireMock.urlPathEqualTo("/v1/current.json"))
                        .withQueryParam("q", WireMock.equalTo(city))
                        .willReturn(WireMock.aResponse()
                                .withStatus(HttpStatus.OK.getCode())
                                .withHeader("Content-Type", "application/json")
                                .withBody(json))
        );
    }

    public void stubError(String city, int code, String message) {
        wireMock.register(
                WireMock.get(WireMock.urlPathEqualTo("/v1/current.json"))
                        .withQueryParam("q", WireMock.equalTo(city))
                        .willReturn(WireMock.aResponse()
                                .withStatus(HttpStatus.BAD_REQUEST.getCode())
                                .withHeader("Content-Type", "application/json")
                                .withBody("{ \"error\": { \"code\": " + code + ", \"message\": \"" + message + "\" } }"))
        );
    }
}
