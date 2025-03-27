package com.dimasukimas.mapper;

import com.dimasukimas.dto.UserRequestDto;
import com.dimasukimas.dto.UserResponseDto;
import com.dimasukimas.entity.User;
import jakarta.servlet.http.Cookie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "password", source = "password", qualifiedByName = "encodePassword")
    User toEntity(UserRequestDto dto);

    UserResponseDto toDto(User user, Cookie cookie);

    @Named("encodePassword")
    default String encodePassword(String rawPassword){
        return new BCryptPasswordEncoder().encode(rawPassword);
    }

}
