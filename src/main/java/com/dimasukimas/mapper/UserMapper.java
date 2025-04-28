package com.dimasukimas.mapper;

import com.dimasukimas.dto.request.UserRegistrationDto;
import com.dimasukimas.dto.response.UserInfoDto;
import com.dimasukimas.entity.User;
import jakarta.servlet.http.Cookie;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserRegistrationDto dto);

    UserInfoDto toDto(User user, UUID sessionId);
}
