package com.yourcompany.weather.mapper;

import com.yourcompany.weather.dto.CityWeather;
import io.cucumber.datatable.DataTable;

import java.util.Map;

public class TableMapper {

    public static CityWeather toCityWeather(String city, DataTable table) {
        Map<String, String> row = table.asMaps().get(0);
        double temp = Double.parseDouble(row.get("temp_c"));
        int humidity = Integer.parseInt(row.get("humidity"));
        String condition = row.get("condition");

        return new CityWeather(city, temp, humidity, condition);
    }

    public static CityWeather toCityWeather(DataTable table) {
        Map<String, String> row = table.asMaps().get(0);
        double temp = Double.parseDouble(row.get("temp_c"));
        int humidity = Integer.parseInt(row.get("humidity"));
        String condition = row.get("condition");

        return new CityWeather(null, temp, humidity, condition);
    }
}
