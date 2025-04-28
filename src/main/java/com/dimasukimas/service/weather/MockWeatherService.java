package com.dimasukimas.service.weather;

import com.dimasukimas.dto.response.LocationDto;
import com.dimasukimas.dto.response.WeatherDto;
import com.dimasukimas.entity.Location;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Profile({"dev"})
public class MockWeatherService implements WeatherService {


    @Override
    public List<LocationDto> findLocationByName(String locationName) {
        return List.of(new LocationDto(locationName, BigDecimal.ZERO, BigDecimal.ZERO, "Gay", "Sex"));
    }

    @Override
    public List<WeatherDto> getWeatherByLocations(List<Location> locations) {
        List<WeatherDto> weatherLocations = new ArrayList<>();

        for (Location location : locations) {

            weatherLocations.add(new WeatherDto(
                    location.getName(),
                    "Gay",
                    location.getLatitude(),
                    location.getLongitude(),
                    BigDecimal.valueOf(20),
                    BigDecimal.valueOf(18),
                    75,
                    "Clear sky",
                    "01d"
            ));
        }
        return weatherLocations;
    }
}
