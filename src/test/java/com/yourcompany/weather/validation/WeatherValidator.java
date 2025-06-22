package com.yourcompany.weather.validation;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class WeatherValidator {

    public void validateWeather(JsonNode actual, double expectedTemp, int expectedHumidity, String expectedCondition) {
        double actualTemp = actual.get("temp_c").asDouble();
        int actualHum = actual.get("humidity").asInt();
        String actualCond = actual.get("condition").get("text").asText();

        log.info("Temperature (C): {}", actualTemp);
        log.info("Humidity: {}", actualHum);
        log.info("Condition: {}", actualCond);

        if (Math.abs(actualTemp - expectedTemp) > 0.001) {
            log.warn("Температура отличается: ожидается {}, получено {}", expectedTemp, actualTemp);
        }
        if (actualHum != expectedHumidity) {
            log.warn("Влажность отличается: ожидается {}, получено {}", expectedHumidity, actualHum);
        }
        if (!actualCond.equalsIgnoreCase(expectedCondition)) {
            log.warn("Состояние отличается: ожидается '{}', получено '{}'", expectedCondition, actualCond);
        }

        assertEquals(expectedTemp, actualTemp, 0.001, "Unexpected temperature");
        assertEquals(expectedHumidity, actualHum, "Unexpected humidity");
        assertEquals(expectedCondition.toLowerCase(), actualCond.toLowerCase(), "Unexpected condition");
    }
}
