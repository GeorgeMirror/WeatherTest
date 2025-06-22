package com.yourcompany.weather.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherResponse {
    private Location location;
    private Current current;

    public WeatherResponse() { }

    public WeatherResponse(Location location, Current current) {
        this.location = location;
        this.current = current;
    }

    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }

    public Current getCurrent() {
        return current;
    }
    public void setCurrent(Current current) {
        this.current = current;
    }

    public static class Location {
        private String name;

        public Location() { }
        public Location(String name) { this.name = name; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
    }

    public static class Current {
        @JsonProperty("temp_c")
        private double tempC;
        private int humidity;
        private Condition condition;

        public Current() { }
        public Current(double tempC, int humidity, Condition condition) {
            this.tempC = tempC;
            this.humidity = humidity;
            this.condition = condition;
        }

        public double getTempC() { return tempC; }
        public void setTempC(double tempC) { this.tempC = tempC; }

        public int getHumidity() { return humidity; }
        public void setHumidity(int humidity) { this.humidity = humidity; }

        public Condition getCondition() { return condition; }
        public void setCondition(Condition condition) { this.condition = condition; }
    }

    public static class Condition {
        private String text;

        public Condition() { }
        public Condition(String text) { this.text = text; }

        public String getText() { return text; }
        public void setText(String text) { this.text = text; }
    }
}
