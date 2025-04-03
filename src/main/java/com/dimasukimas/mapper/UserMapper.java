package com.dimasukimas.mapper;

import com.dimasukimas.dto.UserRequestDto;
import com.dimasukimas.dto.UserResponseDto;
import com.dimasukimas.entity.User;
import jakarta.servlet.http.Cookie;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserRequestDto dto);

    UserResponseDto toDto(User user, Cookie cookie);



}
