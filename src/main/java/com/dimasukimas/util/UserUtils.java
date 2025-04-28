package com.dimasukimas.util;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public class UserUtils {

    public static Optional<String> extractUserLogin(HttpServletRequest request) {

        return Optional.ofNullable(request.getAttribute("userLogin"))
                .filter(attribute -> attribute instanceof String)
                .map(attribute -> (String) attribute);

    }
}
