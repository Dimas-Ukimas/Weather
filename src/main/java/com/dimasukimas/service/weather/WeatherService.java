package com.dimasukimas.service.weather;

import com.dimasukimas.dto.response.LocationDto;
import com.dimasukimas.dto.response.WeatherDto;
import com.dimasukimas.entity.Location;
import java.util.List;

public interface WeatherService {

    public List<LocationDto> findLocationByName(String locationName);

    public List<WeatherDto> getWeatherByLocations(List<Location> locations);
}
