package com.dimasukimas.util;

import jakarta.servlet.http.Cookie;

import java.util.UUID;


public class CookieUtils {

    public static Cookie createCookie(UUID sessionId, int sessionTimeoutInMinutes) {
        int cookieMaxAgeInSeconds = 60 * sessionTimeoutInMinutes;

        Cookie cookie = new Cookie("userCookie", sessionId.toString());
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(cookieMaxAgeInSeconds);
        cookie.setSecure(true);

        return cookie;
    }

    public static Cookie createExpiredCookie(String name) {
        Cookie cookie = new Cookie(name, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        cookie.setHttpOnly(true);

        return cookie;
    }
}
