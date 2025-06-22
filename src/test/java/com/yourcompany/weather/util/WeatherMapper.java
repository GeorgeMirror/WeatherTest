package com.yourcompany.weather.util;

import com.yourcompany.weather.dto.CityWeather;
import com.yourcompany.weather.dto.WeatherResponse;

public class WeatherMapper {

    public static WeatherResponse toWeatherResponse(CityWeather cityWeather) {
        return new WeatherResponse(
                new WeatherResponse.Location(cityWeather.getLocation()),
                new WeatherResponse.Current(
                        cityWeather.getTempC(),
                        cityWeather.getHumidity(),
                        new WeatherResponse.Condition(cityWeather.getCondition())
                )
        );
    }
}
