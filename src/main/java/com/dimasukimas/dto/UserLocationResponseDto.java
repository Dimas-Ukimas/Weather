package com.dimasukimas.dto;

import java.math.BigDecimal;
import java.util.List;

public record UserLocationResponseDto(
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
