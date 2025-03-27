package com.dimasukimas.util;

import jakarta.servlet.http.Cookie;
import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Value;

import java.util.UUID;

@UtilityClass
public class CookieUtils {

    @Value("${session.timeout}")
    private int sessionTimeoutInMinutes;

    public Cookie createCookie(UUID sessionId) {
        int cookieMaxAgeInSeconds = 60 * sessionTimeoutInMinutes;

        Cookie cookie = new Cookie("userCookie", sessionId.toString());
        cookie.setHttpOnly(true);
        cookie.setMaxAge(cookieMaxAgeInSeconds);
        cookie.setSecure(true);

        return cookie;
    }
}
