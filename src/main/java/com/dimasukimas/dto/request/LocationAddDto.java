package com.dimasukimas.dto.request;

import java.math.BigDecimal;

public record LocationAddDto(
        String name,
        BigDecimal latitude,
        BigDecimal longitude
) {

}
