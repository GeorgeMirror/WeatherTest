package com.yourcompany.weather.steps;

import com.fasterxml.jackson.databind.JsonNode;
import com.yourcompany.weather.enums.ApiError;
import com.yourcompany.weather.util.AllureAttachmentHelper;
import io.cucumber.java.en.*;

import static org.junit.jupiter.api.Assertions.*;

public class WeatherNegativeSteps extends BaseSteps {

    @When("I request weather for city {string} with error scenario {string}")
    public void iRequestWeatherWithError(String city, String errorCode) {
        int code = Integer.parseInt(errorCode);
        ApiError apiError = ApiError.fromCode(code);

        stubBuilder.stubError(city, code, apiError.getMessage());
        response = apiClient.getCurrentWeather(city, API_KEY);
    }

    @Then("the response contains API error code {string}")
    public void theResponseContainsAPIErrorCode(String expectedCode) {
        AllureAttachmentHelper.attachJsonResponse("Error response body", response);

        assertEquals(ApiError.LOCATION_NOT_FOUND.getHttpStatus(), response.statusCode(), "Expected HTTP status 400 for errors");

        JsonNode errorNode = responseParser.getErrorNode(response);
        assertNotNull(errorNode, "Response must contain 'error' node");

        String actualCode = errorNode.get("code").asText();
        assertEquals(expectedCode, actualCode, "Error code mismatch");
    }
}
