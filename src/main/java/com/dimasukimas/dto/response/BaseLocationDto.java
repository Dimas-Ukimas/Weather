package com.dimasukimas.dto.response;

import java.math.BigDecimal;

public interface BaseLocationDto {
    String name();
    BigDecimal lat();
    BigDecimal lon();
    String country();
    String state();

    default boolean alreadyAdded() {
        return false;
    }
}
