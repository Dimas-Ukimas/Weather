package com.dimasukimas.service;

import com.dimasukimas.config.util.OpenWeatherConfig;
import com.dimasukimas.dto.LocationResponseDto;
import com.dimasukimas.dto.UserLocationResponseDto;
import com.dimasukimas.entity.Location;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final WebClient webClient;
    private final OpenWeatherConfig config;

    //TODO: add exception handling for queries via VPN
    public List<LocationResponseDto> findLocationByName(String locationName) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(config.getGeoPath())
                        .queryParam("q", locationName)
                        .queryParam("limit", config.getLimit())
                        .queryParam("appid", config.getApiKey())
                        .build())
                .acceptCharset(StandardCharsets.UTF_8)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<LocationResponseDto>>() {
                })
                .block();
    }

    public List<UserLocationResponseDto> getWeatherByLocations(List<Location> userLocations) {

        List<UserLocationResponseDto> weatherLocations = new ArrayList<>();

        for (Location location : userLocations) {
            UserLocationResponseDto weatherLocation = webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path(config.getWeatherPath())
                            .queryParam("lat", String.valueOf(location.getLatitude()))
                            .queryParam("lon", String.valueOf(location.getLongitude()))
                            .queryParam("appid", config.getApiKey())
                            .queryParam("units", config.getUnits())
                            .build())
                    .acceptCharset(StandardCharsets.UTF_8)
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<UserLocationResponseDto>() {
                    })
                    .block();

            weatherLocations.add(weatherLocation);
        }

        return weatherLocations;
    }
}
