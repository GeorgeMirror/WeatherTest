package com.yourcompany.weather.logging;

import io.qameta.allure.Allure;

public class AllureLogger {

    public void attachJson(String name, String json) {
        Allure.addAttachment(name, "application/json", json, "json");
    }
}
