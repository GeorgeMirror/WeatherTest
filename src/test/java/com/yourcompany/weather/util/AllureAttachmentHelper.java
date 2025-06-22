package com.yourcompany.weather.util;

import io.qameta.allure.Allure;
import io.restassured.response.Response;

public class AllureAttachmentHelper {

    public static void attachJsonResponse(String title, Response response) {
        if (response != null && response.getBody() != null) {
            String body = response.getBody().asString();
            Allure.addAttachment(title, "application/json", body, "json");
        }
    }
}