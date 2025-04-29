package service;

import com.dimasukimas.config.util.PasswordEncodingConfig;
import com.dimasukimas.entity.User;
import com.dimasukimas.entity.UserSession;
import com.dimasukimas.exception.DataNotFoundException;
import com.dimasukimas.repository.SessionRepository;
import com.dimasukimas.repository.UserRepository;
import com.dimasukimas.service.SessionService;
import com.dimasukimas.service.UserService;
import config.TestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfig.class, SessionService.class, UserService.class, PasswordEncodingConfig.class})
@ActiveProfiles("test")
@Transactional
class SessionServiceIT {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private UserRepository userRepository;

    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = User.builder()
                .login("testuser")
                .password("hashedPassword")
                .build();
        userRepository.persist(testUser);
    }

    @Test
    void whenCreateSession_thenSessionIsSaved() {
        UUID sessionId = sessionService.createSession(testUser);

        Optional<UserSession> sessionOptional = sessionRepository.findById(sessionId);
        assertThat(sessionOptional).isPresent();
        assertThat(sessionOptional.get().getUser().getLogin()).isEqualTo("testuser");
    }

    @Test
    void whenDeleteSession_thenSessionIsRemoved() {
        UUID sessionId = sessionService.createSession(testUser);

        sessionService.deleteSession(sessionId);

        Optional<UserSession> sessionOptional = sessionRepository.findById(sessionId);
        assertThat(sessionOptional).isNotPresent();
    }

    @Test
    void whenGetNonExistingSession_thenThrowDataNotFoundException() {
        UUID nonExistentSessionId = UUID.randomUUID();

        assertThatThrownBy(() -> sessionService.getSessionById(nonExistentSessionId))
                .isInstanceOf(DataNotFoundException.class)
                .hasMessageContaining("Session was not found");
    }

}
