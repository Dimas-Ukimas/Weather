package com.dimasukimas.config.util;

import lombok.Getter;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Getter
public class OpenWeatherConfig {

    private final int limit;
    private final String apiKey;
    private final String geoPath;
    private final String weatherPath;
    private final String units;

    public OpenWeatherConfig(Environment env) {
        this.limit = Integer.parseInt(env.getProperty("open-weather.api.limit", "5"));
        this.apiKey = env.getProperty("OPEN_WEATHER_API_KEY");
        this.geoPath = env.getProperty("open-weather.api.geo-path");
        this.weatherPath = env.getProperty("open-weather.api.weather-path");
        this.units = env.getProperty("open-weather.api.units");
    }


}
