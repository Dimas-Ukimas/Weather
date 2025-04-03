package com.dimasukimas.service;

import com.dimasukimas.config.util.OpenWeatherConfig;
import com.dimasukimas.dto.LocationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final WebClient webClient;
    private final OpenWeatherConfig config;

//TODO: add exception handling for queries via VPN
    public List<LocationDto> findLocationByName(String locationName) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(config.getGeoPath())
                        .queryParam("q", locationName)
                        .queryParam("limit", config.getLimit())
                        .queryParam("appid", config.getApiKey())
                        .build())
                .acceptCharset(StandardCharsets.UTF_8)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<LocationDto>>() {})
                .block();
    }

}
