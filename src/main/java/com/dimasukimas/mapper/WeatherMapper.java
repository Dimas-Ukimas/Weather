package com.dimasukimas.mapper;

import com.dimasukimas.dto.response.WeatherDto;
import com.dimasukimas.dto.response.RawWeatherDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public interface WeatherMapper {

    @Mapping(source = "name", target = "name")
    @Mapping(source = "lat", target = "latitude")
    @Mapping(source = "lon", target = "longitude")
    @Mapping(source = "weather.sys.country", target = "country")
    @Mapping(source = "weather.main.temp", target = "temp")
    @Mapping(source = "weather.main.feels_like", target = "feelsLike")
    @Mapping(source = "weather.main.humidity", target = "humidity")
    @Mapping(source = "weather", target = "description", qualifiedByName = "mapDescription")
    @Mapping(source = "weather", target = "icon", qualifiedByName = "mapIcon")
    WeatherDto toWeatherDto(RawWeatherDto weather, BigDecimal lat, BigDecimal lon, String name);

    @Named("mapDescription")
    default String mapDescription(RawWeatherDto weather) {
        if (weather.weather() != null && !weather.weather().isEmpty()) {
            return weather.weather().get(0).description();
        }
        return "No description";
    }

    @Named("mapIcon")
    default String mapIcon(RawWeatherDto weather) {
        if (weather.weather() != null && !weather.weather().isEmpty()) {
            return weather.weather().get(0).icon();
        }
        return "";
    }
}
