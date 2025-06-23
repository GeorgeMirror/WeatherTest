package com.yourcompany.weather.stub;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.yourcompany.weather.dto.ApiErrorResponse;
import com.yourcompany.weather.enums.ApiError;
import lombok.SneakyThrows;

public class StubBuilder {

    private final WireMock wireMock;
    private final ObjectMapper mapper = new ObjectMapper();

    public StubBuilder(WireMockServer server) {
        this.wireMock = new WireMock(server.port());
    }

    @SneakyThrows
    public void stubSuccess(String city, Object response) {
        String json = mapper.writeValueAsString(response);

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
