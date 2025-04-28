package com.dimasukimas.dto.response;

import java.math.BigDecimal;
import java.util.List;

public record RawWeatherDto(
        String name,
        Sys sys,
        Main main,
        List<Weather> weather
) {
    public record Sys(String country) {}

    public record Main(
            BigDecimal temp,
            BigDecimal feels_like,
            int humidity
    ) {}

    public record Weather(
            String description,
            String icon
    ) {}
}
