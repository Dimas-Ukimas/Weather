package com.dimasukimas.service;

import com.dimasukimas.entity.User;
import com.dimasukimas.entity.UserSession;
import com.dimasukimas.exception.DataNotFoundException;
import com.dimasukimas.repository.SessionRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;

    @Value("${session.timeout}")
    @Getter
    private int sessionTimeoutInMinutes;

    @Transactional
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

    @Transactional
    public void deleteSession(UUID sessionId) {
        UserSession session = sessionRepository.findById(sessionId).orElseThrow(() -> new DataNotFoundException("Session was not found"));
        sessionRepository.delete(session);
    }

    @Transactional
    @Scheduled(cron = "0 0/5 * * * ?") // каждые 5 минут
    public void removeExpiredSessions() {
        LocalDateTime now = LocalDateTime.now();
        sessionRepository.deleteExpiredSessions(now);
    }

    public UserSession getSessionById(UUID sessionId) {

        return sessionRepository.findById(sessionId).orElseThrow(() -> new DataNotFoundException("Session was not found"));
    }


}
