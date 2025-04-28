package com.dimasukimas.repository;

import com.dimasukimas.entity.UserSession;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public class SessionRepository extends AbstractJpaRepository<UserSession, UUID> {

    protected SessionRepository() {
        super(UserSession.class);
    }

    public void deleteExpiredSessions(LocalDateTime now) {
        entityManager.createQuery("DELETE FROM UserSession s WHERE s.expiresAt < :now")
                .setParameter("now", now)
                .executeUpdate();
    }
}
