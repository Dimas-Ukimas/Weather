package com.dimasukimas.repository;

import com.dimasukimas.entity.UserSession;
import org.springframework.stereotype.Repository;

@Repository
public class SessionRepository extends AbstractJpaRepository<UserSession, Integer> {

    protected SessionRepository() {
        super(UserSession.class);
    }
}
