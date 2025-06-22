package com.yourcompany.weather.enums;

import lombok.Getter;

@Getter
public enum HttpStatus {
    OK(200),
    BAD_REQUEST(400),
    NOT_FOUND(404),
    INTERNAL_SERVER_ERROR(500);
    // Можно добавить другие по мере необходимости

    private final int code;

    HttpStatus(int code) {
        this.code = code;
    }

}
