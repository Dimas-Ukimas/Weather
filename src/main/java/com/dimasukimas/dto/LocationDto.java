package com.dimasukimas.dto;

import java.util.Optional;

public record LocationDto(
        String name,
        double lat,
        double lon,
        String country,
        Optional<String> state) {

    public String getState() {
        return state.orElse("");
    }
}
