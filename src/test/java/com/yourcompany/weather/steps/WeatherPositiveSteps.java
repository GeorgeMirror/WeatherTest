package com.yourcompany.weather.steps;

import com.fasterxml.jackson.databind.JsonNode;
import com.yourcompany.weather.dto.CityWeather;
import com.yourcompany.weather.enums.ApiError;
import com.yourcompany.weather.util.AllureAttachmentHelper;
import com.yourcompany.weather.util.WeatherMapper;
import io.cucumber.java.en.*;

import static org.junit.jupiter.api.Assertions.*;

public class WeatherPositiveSteps extends BaseSteps {

    @When("I request current weather for city {string} with expected data:")
    public void iRequestCurrentWeatherWithExpectedData(String city, CityWeather cityWeather) {
        this.expectedCityWeather = cityWeather;

        var stubResponse = WeatherMapper.toWeatherResponse(cityWeather);
        stubBuilder.stubSuccess(city, stubResponse); // теперь из context

        response = apiClient.getCurrentWeather(city, API_KEY); // тоже из context
    }

    @Then("the response contains weather data close to standard city:")
    public void theResponseContainsExpectedWeatherData(CityWeather etalonCity) {
        AllureAttachmentHelper.attachJsonResponse("Response body", response);

        assertEquals(ApiError.SUCCESS.getHttpStatus(), response.statusCode(), "Expected HTTP status 200");

        JsonNode current = responseParser.getCurrentNode(response); // тоже из context
        validator.validateWeather(current, etalonCity.getTempC(), etalonCity.getHumidity(), etalonCity.getCondition()); // тоже из context
    }
}
