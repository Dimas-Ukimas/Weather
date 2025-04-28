package com.dimasukimas.config.util;

import lombok.Getter;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Getter
public class WeatherApiConfig {

    private final int limit;
    private final String apiKey;
    private final String geoPath;
    private final String weatherPath;
    private final String units;

    public WeatherApiConfig(Environment env) {
        this.limit = Integer.parseInt(env.getProperty("weather.api.limit", "5"));
        this.apiKey = env.getProperty("WEATHER_API_KEY");
        this.geoPath = env.getProperty("weather.api.geo-path");
        this.weatherPath = env.getProperty("weather.api.weather-path");
        this.units = env.getProperty("weather.api.units");
    }


}
