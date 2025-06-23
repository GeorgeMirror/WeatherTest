package com.yourcompany.weather.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ApiError {
    SUCCESS(200, 0, "Success"),
    LOCATION_NOT_FOUND(400, 1006, "No matching location found."),
    KEY_NOT_SUPPLIED(400, 1008, "API key not supplied."),
    LIMIT_REACHED(400, 2006, "API request limit reached."),
    CUSTOM(400, 9999, "Custom error for testing.");

    private final int httpStatus;
    private final int code;
    private final String message;

    ApiError(int httpStatus, int code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }

    public static ApiError fromCode(int code) {
        return Arrays.stream(values())
                .filter(e -> e.code == code)
                .findFirst()
                .orElse(CUSTOM);
    }
}
