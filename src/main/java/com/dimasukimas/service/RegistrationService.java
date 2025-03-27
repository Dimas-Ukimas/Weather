package com.dimasukimas.service;

import com.dimasukimas.dto.UserRequestDto;
import com.dimasukimas.dto.UserResponseDto;
import com.dimasukimas.entity.User;
import com.dimasukimas.mapper.UserMapper;
import com.dimasukimas.repository.UserRepository;
import com.dimasukimas.util.CookieUtils;
import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class RegistrationService {

    private final UserRepository userRepository;
    private final SessionService sessionService;
    private final UserMapper mapper;


    public UserResponseDto registerUser(UserRequestDto dto) {
        User user = mapper.toEntity(dto);
        userRepository.persist(user);

        UUID sessionId = sessionService.createSession(user);
        Cookie cookie = CookieUtils.createCookie(sessionId);

        return mapper.toDto(user, cookie);
    }



}
