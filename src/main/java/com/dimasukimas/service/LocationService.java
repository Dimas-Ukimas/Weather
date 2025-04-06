package com.dimasukimas.service;

import com.dimasukimas.dto.LocationRequestDto;
import com.dimasukimas.dto.LocationResponseDto;
import com.dimasukimas.dto.UserLocationResponseDto;
import com.dimasukimas.entity.Location;
import com.dimasukimas.entity.User;
import com.dimasukimas.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class LocationService {

    private final LocationRepository locationRepository;
    private final SessionService sessionService;
    private final WeatherService weatherService;

    public void addLocation(UUID sessionId, LocationRequestDto dto) {
        User user = sessionService.getUserBySessionId(sessionId);

        Location location = Location.builder()
                .user(user)
                .name(dto.name())
                .latitude(dto.latitude())
                .longitude(dto.longitude())
                .build();

        locationRepository.persist(location);

    }

    public List<UserLocationResponseDto> getUserLocations(UUID sessionId) {
        List<Location> userLocations = getUserLocationsBySession(sessionId);

        return weatherService.getWeatherByLocations(userLocations);
    }

    public List<LocationResponseDto> findLocationByName(String locationName, UUID sessionId) {
        User user = sessionService.getUserBySessionId(sessionId);
        List<Location> userLocations = locationRepository.findAllByUser(user);
        List<LocationResponseDto> locations = weatherService.findLocationByName(locationName);

        Set<String> userLocationsCoordinates = userLocations.stream()
                .map(loc -> loc.getLongitude() + "," + loc.getLatitude())
                .collect(Collectors.toSet());

        locations.forEach(loc -> {
                    String coordinates = loc.getLon() + "," + loc.getLat();
                    if (userLocationsCoordinates.contains(coordinates)) {
                        loc.setAlreadyAdded(true);
                    }
                }
        );

        return locations;
    }

    public List<LocationResponseDto> findLocationByName(String locationName) {
        return  weatherService.findLocationByName(locationName);
    }

    private List<Location> getUserLocationsBySession(UUID sessionId) {
        User user = sessionService.getUserBySessionId(sessionId);

        return locationRepository.findAllByUser(user);
    }
}
