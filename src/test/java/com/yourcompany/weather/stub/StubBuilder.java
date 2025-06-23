package com.yourcompany.weather.stub;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.yourcompany.weather.dto.ApiErrorResponse;
import com.yourcompany.weather.dto.WeatherResponse;
import com.yourcompany.weather.enums.ApiError;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StubBuilder {

    private final WireMock wireMock;
    private final ObjectMapper mapper = new ObjectMapper();

    public StubBuilder(WireMockServer server) {
        if (server == null) {
            throw new IllegalArgumentException("WireMockServer must not be null");
        }
        if (!server.isRunning()) {
            throw new IllegalStateException("WireMockServer must be started before creating StubBuilder");
        }
        this.wireMock = new WireMock(server.port());
    }

    @SneakyThrows
    public void stubSuccess(String city, WeatherResponse response) {
        String json = mapper.writeValueAsString(response);
        log.debug("Registering stub success for city '{}': {}", city, json);

        wireMock.register(
                WireMock.get(WireMock.urlPathEqualTo("/v1/current.json"))
                        .withQueryParam("q", WireMock.equalTo(city))
                        .willReturn(WireMock.aResponse()
                                .withStatus(ApiError.SUCCESS.getHttpStatus())
                                .withHeader("Content-Type", "application/json")
                                .withBody(json))
        );
    }

    @SneakyThrows
    public void stubError(String city, ApiError apiError) {
        ApiErrorResponse errorResponse = new ApiErrorResponse(
                new ApiErrorResponse.ErrorBody(apiError.getCode(), apiError.getMessage())
        );
        String json = mapper.writeValueAsString(errorResponse);

        log.debug("Registering stub error for city '{}': {}", city, json);

        wireMock.register(
                WireMock.get(WireMock.urlPathEqualTo("/v1/current.json"))
                        .withQueryParam("q", WireMock.equalTo(city))
                        .willReturn(WireMock.aResponse()
                                .withStatus(apiError.getHttpStatus())
                                .withHeader("Content-Type", "application/json")
                                .withBody(json))
        );
    }
}
