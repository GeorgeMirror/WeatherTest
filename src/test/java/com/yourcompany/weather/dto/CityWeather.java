package com.yourcompany.weather.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityWeather {
    private String location;
    private double tempC;
    private int humidity;
    private String condition;
}
