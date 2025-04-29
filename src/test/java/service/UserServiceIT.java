package service;

import com.dimasukimas.config.util.PasswordEncodingConfig;
import com.dimasukimas.dto.request.UserAuthDto;
import com.dimasukimas.dto.request.UserRegistrationDto;
import com.dimasukimas.entity.User;
import com.dimasukimas.exception.UserAlreadyExistsException;
import com.dimasukimas.exception.UserAuthenticationFailedException;
import com.dimasukimas.exception.UserNotFoundException;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfig.class, UserService.class, SessionService.class, PasswordEncodingConfig.class})
@ActiveProfiles("test")
@Transactional
class UserServiceIT {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionRepository sessionRepository;

    private UserRegistrationDto userRegistrationDto;

    @BeforeEach
    void setUp() {
        userRegistrationDto = new UserRegistrationDto("testuser", "password123", "password123");
    }

    @Test
    void whenRegisterUser_thenUserIsCreatedInDatabase() {
        userService.registerUser(userRegistrationDto);

        Optional<User> userOptional = userRepository.findUserByLogin("testuser");
        assertThat(userOptional).isPresent();
        assertThat(userOptional.get().getLogin()).isEqualTo("testuser");
    }

    @Test
    void whenSignInUser_thenSessionIsCreated() {
        userService.registerUser(userRegistrationDto);

        UserAuthDto authDto = new UserAuthDto("testuser", "password123");
        var userInfoDto = userService.signInUser(authDto);

        assertThat(userInfoDto).isNotNull();
        assertThat(sessionRepository.findById(userInfoDto.sessionId())).isPresent();
    }

    @Test
    void whenRegisterUserWithDuplicateLogin_thenThrowException() {
        userService.registerUser(userRegistrationDto);

        UserRegistrationDto duplicateUserDto = new UserRegistrationDto("testuser", "newpassword", "newpassword");

        assertThatThrownBy(() -> userService.registerUser(duplicateUserDto))
                .isInstanceOf(UserAlreadyExistsException.class)
                .hasMessageContaining("This username is already taken");
    }

    @Test
    void whenSignInWithWrongPassword_thenThrowAuthenticationFailedException() {
        userService.registerUser(userRegistrationDto);

        UserAuthDto authDto = new UserAuthDto("testuser", "wrongpassword");

        assertThatThrownBy(() -> userService.signInUser(authDto))
                .isInstanceOf(UserAuthenticationFailedException.class)
                .hasMessageContaining("Incorrect password");
    }

    @Test
    void whenSignInNonExistingUser_thenThrowUserNotFoundException() {
        UserAuthDto authDto = new UserAuthDto("nonexistentuser", "somepassword");

        assertThatThrownBy(() -> userService.signInUser(authDto))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessageContaining("No such user");
    }
}
