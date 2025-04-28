package com.dimasukimas.dto.response;

import java.math.BigDecimal;

public record WeatherDto(
        String name,
        String country,
        BigDecimal latitude,
        BigDecimal longitude,
        BigDecimal temp,
        BigDecimal feelsLike,
        int humidity,
        String description,
        String icon
) {
}

