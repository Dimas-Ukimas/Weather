package com.dimasukimas.service;

import com.dimasukimas.dto.request.LocationAddDto;
import com.dimasukimas.dto.request.LocationDeleteDto;
import com.dimasukimas.dto.response.LocationDto;
import com.dimasukimas.dto.response.UserLocationDto;
import com.dimasukimas.dto.response.WeatherDto;
import com.dimasukimas.entity.Location;
import com.dimasukimas.entity.User;
import com.dimasukimas.entity.UserSession;
import com.dimasukimas.exception.DataNotFoundException;
import com.dimasukimas.mapper.UserLocationMapper;
import com.dimasukimas.repository.LocationRepository;
import com.dimasukimas.service.weather.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;
    private final SessionService sessionService;
    private final WeatherService weatherService;
    private final UserLocationMapper mapper;

    @Transactional
    public void addLocation(UUID sessionId, LocationAddDto dto) {

        UserSession session = sessionService.getSessionById(sessionId);
        User user = session.getUser();

        Location location = Location.builder()
                .user(user)
                .name(dto.name())
                .latitude(dto.latitude())
                .longitude(dto.longitude())
                .build();

        locationRepository.persist(location);

    }

    public List<WeatherDto> getUserLocations(UUID sessionId) {
        List<Location> userLocations = getUserLocationsBySession(sessionId);

        return weatherService.getWeatherByLocations(userLocations);
    }

    public List<UserLocationDto> findLocation(String locationName, UUID sessionId) {
        UserSession session = sessionService.getSessionById(sessionId);
        User user = session.getUser();

        List<Location> userLocations = locationRepository.findAllByUser(user);
        List<LocationDto> locations = weatherService.findLocationByName(locationName);

        return checkAlreadyAddedLocations(userLocations, locations);
    }

    public List<LocationDto> findLocation(String locationName) {

        return weatherService.findLocationByName(locationName);
    }

    @Transactional
    public void deleteLocation(UUID sessionId, LocationDeleteDto dto) {
        User user = sessionService.getSessionById(sessionId).getUser();

        Location location = locationRepository.findByCoordAndUser(dto.longitude(), dto.latitude(), user).orElseThrow(() -> new DataNotFoundException("Location was not found"));
        locationRepository.delete(location);
    }

    private List<Location> getUserLocationsBySession(UUID sessionId) {
        User user = sessionService.getSessionById(sessionId).getUser();

        return locationRepository.findAllByUser(user);
    }


    private List<UserLocationDto> checkAlreadyAddedLocations(List<Location> userLocations, List<LocationDto> locations) {
        List<UserLocationDto> checkedUserLocations = new ArrayList<>();

        Set<String> userLocationsCoordinates = userLocations.stream()
                .map(location -> location.getLongitude() + "," + location.getLatitude())
                .collect(Collectors.toSet());

        locations.forEach(location -> {
                    String coordinates = location.lon() + "," + location.lat();
                    if (userLocationsCoordinates.contains(coordinates)) {
                        checkedUserLocations.add(mapper.toDto(location, true));
                    } else {
                        checkedUserLocations.add(mapper.toDto(location, false));
                    }
                }
        );
        return checkedUserLocations;
    }
}
