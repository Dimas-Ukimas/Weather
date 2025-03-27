package com.dimasukimas.dto;

import jakarta.servlet.http.Cookie;

public record UserResponseDto(String login,
                              Cookie cookie
) {
}
