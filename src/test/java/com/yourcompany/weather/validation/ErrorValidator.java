package com.yourcompany.weather.validation;

import com.fasterxml.jackson.databind.JsonNode;
import io.qameta.allure.Allure;

import static org.junit.jupiter.api.Assertions.*;

public class ErrorValidator {

    public void validateError(JsonNode jsonNode, String expectedCode) {
        JsonNode errorNode = jsonNode.get("error");
        assertNotNull(errorNode, "Response must contain 'error' node");

        String actualCode = errorNode.get("code").asText();
        assertEquals(expectedCode, actualCode, "Error code mismatch");
    }

    public void attachErrorBody(String body) {
        Allure.addAttachment("Error response body", "application/json", body, "json");
    }
}
