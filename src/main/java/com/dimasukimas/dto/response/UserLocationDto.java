package com.dimasukimas.dto.response;

import java.math.BigDecimal;

public record UserLocationDto(
        String name,
        BigDecimal lat,
        BigDecimal lon,
        String country,
        String state,
        boolean alreadyAdded
) implements BaseLocationDto {

    @Override
    public boolean alreadyAdded() {
        return alreadyAdded;
    }
}
