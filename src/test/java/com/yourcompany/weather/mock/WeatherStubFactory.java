package com.yourcompany.weather.mock;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yourcompany.weather.dto.ApiErrorResponse;
import com.yourcompany.weather.enums.ApiError;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.yourcompany.weather.enums.HttpStatus;
import io.qameta.allure.Step;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class WeatherStubFactory {

    private final WireMockServer wireMock;
    private static final ObjectMapper mapper = new ObjectMapper();

    public WeatherStubFactory(WireMockServer wireMock) {
        this.wireMock = wireMock;
    }

    public void stubSuccess(String city, String responseBody) {
        wireMock.stubFor(get(urlPathEqualTo("/v1/current.json"))
                .withQueryParam("key", matching(".*"))
                .withQueryParam("q", equalTo(city))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.getCode())
                        .withHeader("Content-Type", "application/json")
                        .withBody(responseBody)));
    }

    public void stubError(String city, ApiError apiError) {
        try {
            ApiErrorResponse dto = new ApiErrorResponse(
                    new ApiErrorResponse.ErrorBody(apiError.getCode(), apiError.getMessage()));
            String body = mapper.writeValueAsString(dto);

            wireMock.stubFor(get(urlPathEqualTo("/v1/current.json"))
                    .withQueryParam("key", matching(".*"))
                    .withQueryParam("q", equalTo(city))
                    .willReturn(aResponse()
                            .withStatus(HttpStatus.BAD_REQUEST.getCode())
                            .withHeader("Content-Type", "application/json")
                            .withBody(body)));
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize error response JSON", e);
        }
    }
}
