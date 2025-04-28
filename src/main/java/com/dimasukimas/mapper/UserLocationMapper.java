package com.dimasukimas.mapper;

import com.dimasukimas.dto.response.LocationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.dimasukimas.dto.response.UserLocationDto;

@Mapper(componentModel = "spring")
public interface UserLocationMapper {

    @Mapping(target = "alreadyAdded", source = "isAlreadyAdded")
    UserLocationDto toDto(LocationDto dto, boolean isAlreadyAdded);

}
