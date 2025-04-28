package com.dimasukimas.dto.request;

import java.math.BigDecimal;

public record LocationDeleteDto(
        BigDecimal longitude,
        BigDecimal latitude
        ) {
}
