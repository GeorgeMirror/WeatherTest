package com.yourcompany.weather.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiErrorResponse {
    private ErrorBody error;

    @Data
    @AllArgsConstructor
    public static class ErrorBody {
        private int code;
        private String message;
    }
}
