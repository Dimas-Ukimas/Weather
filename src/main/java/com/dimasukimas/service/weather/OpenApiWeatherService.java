package com.dimasukimas.service.weather;

import com.dimasukimas.config.util.WeatherApiConfig;
import com.dimasukimas.dto.response.LocationDto;
import com.dimasukimas.dto.response.RawWeatherDto;
import com.dimasukimas.dto.response.WeatherDto;
import com.dimasukimas.entity.Location;
import com.dimasukimas.mapper.WeatherMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Profile("prod")
public class OpenApiWeatherService implements WeatherService{

    private final WebClient webClient;
    private final WeatherApiConfig config;
    private final WeatherMapper mapper;

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
                .bodyToMono(new ParameterizedTypeReference<List<LocationDto>>() {
                })
                .block();
    }

    public List<WeatherDto> getWeatherByLocations(List<Location> locations) {

        List<WeatherDto> weatherLocations = new ArrayList<>();

        for (Location location : locations) {
            RawWeatherDto weatherLocation = webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path(config.getWeatherPath())
                            .queryParam("lat", String.valueOf(location.getLatitude()))
                            .queryParam("lon", String.valueOf(location.getLongitude()))
                            .queryParam("appid", config.getApiKey())
                            .queryParam("units", config.getUnits())
                            .build())
                    .acceptCharset(StandardCharsets.UTF_8)
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<RawWeatherDto>() {
                    })
                    .block();


            weatherLocations.add(mapToWeatherDto(weatherLocation, location));
        }

        return weatherLocations;
    }

    private WeatherDto mapToWeatherDto(RawWeatherDto weatherLocation, Location location) {
        BigDecimal lat = location.getLatitude();
        BigDecimal lon = location.getLongitude();
        String name = location.getName();

        return mapper.toWeatherDto(weatherLocation, lat, lon, name);
    }
}
