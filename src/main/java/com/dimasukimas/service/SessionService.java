package com.dimasukimas.service;

import com.dimasukimas.entity.User;
import com.dimasukimas.entity.UserSession;
import com.dimasukimas.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;

    @Value("${session.timeout}")
    private int sessionTimeoutInMinutes;


    public UUID createSession(User user) {
        UUID sessionId = UUID.randomUUID();
        LocalDateTime expiresAt = LocalDateTime.now().plusMinutes(sessionTimeoutInMinutes);

        UserSession userSession = UserSession.builder()
                .id(sessionId)
                .user(user)
                .expiresAt(expiresAt)
                .build();

        sessionRepository.persist(userSession);

        return sessionId;

    }



}
