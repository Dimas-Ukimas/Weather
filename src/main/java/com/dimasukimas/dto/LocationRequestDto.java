package com.dimasukimas.dto;

import java.math.BigDecimal;

public record LocationRequestDto(
        String name,
        BigDecimal latitude,
        BigDecimal longitude
) {

}
