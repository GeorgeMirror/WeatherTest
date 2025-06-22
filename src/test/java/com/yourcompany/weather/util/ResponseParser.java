package com.yourcompany.weather.util;

import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;

public class ResponseParser {

    private final JsonParser jsonParser;

    public ResponseParser(JsonParser jsonParser) {
        this.jsonParser = jsonParser;
    }

    public JsonNode parseResponseBody(Response response) {
        if (response == null || response.getBody() == null) {
            throw new IllegalArgumentException("Response or response body is null");
        }
        return jsonParser.parse(response.getBody().asString());
    }

    public JsonNode getCurrentNode(Response response) {
        JsonNode root = parseResponseBody(response);
        return root.get("current");
    }

    public JsonNode getErrorNode(Response response) {
        JsonNode root = parseResponseBody(response);
        return root.get("error");
    }
}
