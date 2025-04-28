package com.dimasukimas.controller;

import com.dimasukimas.dto.request.UserAuthDto;
import com.dimasukimas.dto.request.UserRegistrationDto;
import com.dimasukimas.dto.response.UserInfoDto;
import com.dimasukimas.service.SessionService;
import com.dimasukimas.service.UserService;
import com.dimasukimas.util.CookieUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final static String SIGN_IN_PAGE = "sign-in";
    private final static String SIGN_UP_PAGE = "sign-up";
    private final static String HOME_PAGE_REDIRECT = "redirect:/";
    private final static String USER_COOKIE_NAME = "userCookie";
    private final UserService userService;
    private final SessionService sessionService;

    @GetMapping("/sign-in")
    public String signIn() {
        return SIGN_IN_PAGE;
    }

    @PostMapping("/sign-in")
    public String signIn(RedirectAttributes redirectAttributes,
                         HttpServletResponse response,
                         Model model,
                         @Valid UserAuthDto dto,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            addErrorsToModel(bindingResult, model);

            return SIGN_IN_PAGE;
        }

        UserInfoDto userInfo = userService.signInUser(dto);
        addCookieAndLogin(response, redirectAttributes, userInfo);

        return HOME_PAGE_REDIRECT;
    }

    @GetMapping("/sign-up")
    public String signUp() {
        return SIGN_UP_PAGE;
    }

    @PostMapping("/sign-up")
    public String signUp(RedirectAttributes redirectAttributes,
                         HttpServletResponse response,
                         Model model,
                         @Valid UserRegistrationDto dto,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            addErrorsToModel(bindingResult, model);

            return SIGN_UP_PAGE;
        }

        UserInfoDto userInfo = userService.registerUser(dto);
        addCookieAndLogin(response, redirectAttributes, userInfo);

        return HOME_PAGE_REDIRECT;
    }

    @GetMapping("/sign-out")
    public String signOut(HttpServletRequest request, HttpServletResponse response, Model model) {
        Cookie cookie = WebUtils.getCookie(request, USER_COOKIE_NAME);

        if (cookie != null) {
            UUID sessionId = UUID.fromString(cookie.getValue());

            sessionService.deleteSession(sessionId);

            Cookie expiredCookie = CookieUtils.createExpiredCookie(cookie.getName());
            response.addCookie(expiredCookie);
        }

        return HOME_PAGE_REDIRECT;
    }

    private void addCookieAndLogin(HttpServletResponse response, RedirectAttributes redirectAttributes, UserInfoDto userInfo) {
        Cookie cookie = CookieUtils.createCookie(userInfo.sessionId(), sessionService.getSessionTimeoutInMinutes());
        response.addCookie(cookie);
        redirectAttributes.addFlashAttribute("userLogin", userInfo.login());
    }

    private void addErrorsToModel(BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            Map<String, String> validationErrors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    validationErrors.put(error.getField(), error.getDefaultMessage())
            );
            model.addAttribute("validationErrors", validationErrors);
        }
    }

}

