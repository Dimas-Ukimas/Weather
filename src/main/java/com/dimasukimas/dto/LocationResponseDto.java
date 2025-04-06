package com.dimasukimas.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class LocationResponseDto {

    String name;
    BigDecimal lat;
    BigDecimal lon;
    String country;
    String state;
    boolean alreadyAdded;
}
