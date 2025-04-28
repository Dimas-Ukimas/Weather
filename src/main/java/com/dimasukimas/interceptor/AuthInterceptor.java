package com.dimasukimas.interceptor;

import com.dimasukimas.entity.User;
import com.dimasukimas.entity.UserSession;
import com.dimasukimas.service.SessionService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.WebUtils;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    private final SessionService sessionService;
    private final static String USER_COOKIE_NAME = "userCookie";

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {

        Cookie cookie = WebUtils.getCookie(request, USER_COOKIE_NAME);

        if (cookie != null) {
            UUID sessionId = UUID.fromString(cookie.getValue());

            User user = sessionService.getSessionById(sessionId).getUser();
            request.setAttribute("userLogin", user.getLogin());
        }

        return true;
    }

}
