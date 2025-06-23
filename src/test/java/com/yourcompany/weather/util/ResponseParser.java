package com.yourcompany.weather.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

public class ResponseParser {

    private static final ObjectMapper mapper = new ObjectMapper();

    public JsonNode getRootNode(Response response) {
        if (response == null || response.getBody() == null) {
            throw new IllegalArgumentException("Response or response body is null");
        }

        try {
            return mapper.readTree(response.getBody().asString());
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse JSON response", e);
        }
    }

    public JsonNode getCurrentNode(Response response) {
        return getRootNode(response).get("current");
    }

    public JsonNode getErrorNode(Response response) {
        return getRootNode(response).get("error");
    }

    public void validateAgainstSchema(Response response, String schemaPath) {
        // TODO: Implement schema validation using JSON Schema (e.g. Everit or NetworkNT validator)
        throw new UnsupportedOperationException("Schema validation not yet implemented");
    }
}
