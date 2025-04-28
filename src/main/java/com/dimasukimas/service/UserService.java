package com.dimasukimas.service;

import com.dimasukimas.dto.request.UserAuthDto;
import com.dimasukimas.dto.request.UserRegistrationDto;
import com.dimasukimas.dto.response.UserInfoDto;
import com.dimasukimas.entity.User;
import com.dimasukimas.exception.DataNotFoundException;
import com.dimasukimas.exception.UserAuthenticationFailedException;
import com.dimasukimas.exception.UserNotFoundException;
import com.dimasukimas.mapper.UserMapper;
import com.dimasukimas.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final SessionService sessionService;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public UserInfoDto registerUser(UserRegistrationDto dto) {
        User user = mapper.toEntity(dto);

        String rawPassword = user.getPassword();
        String hashedPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(hashedPassword);

        userRepository.persist(user);

       return authorizeUser(user);
    }

    public UserInfoDto signInUser(UserAuthDto dto) {
        User user = authenticateUser(dto);

        return authorizeUser(user);
    }


    private User authenticateUser(UserAuthDto dto) {
        User user = userRepository.findUserByLogin(dto.login()).orElseThrow(()-> new UserNotFoundException("No such user"));

        if (!passwordEncoder.matches(dto.password(), user.getPassword())) {
            throw new UserAuthenticationFailedException("Incorrect password");
        }

        return user;
    }

    private UserInfoDto authorizeUser(User user) {
        UUID sessionId = sessionService.createSession(user);

        return mapper.toDto(user, sessionId);

    }

}
