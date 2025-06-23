package com.yourcompany.weather.mapper;

import com.yourcompany.weather.dto.CityWeather;
import io.cucumber.java.DataTableType;

import java.util.Map;

public class DataTableDefinitions {

    @DataTableType
    public CityWeather cityWeatherEntry(Map<String, String> entry) {
        CityWeather city = new CityWeather();
        city.setTempC(Double.parseDouble(entry.get("temp_c")));
        city.setHumidity(Integer.parseInt(entry.get("humidity")));
        city.setCondition(entry.get("condition"));
        return city;
    }
}

