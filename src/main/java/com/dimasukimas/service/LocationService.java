package com.dimasukimas.service;

import com.dimasukimas.dto.LocationDto;
import com.dimasukimas.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;
    private final WeatherService weatherService;


//    public List<LocationDto> findLocationByName(String locationName) {
//      re  weatherService
//    }

}
