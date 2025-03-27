package com.dimasukimas.controller;

import com.dimasukimas.dto.UserRequestDto;
import com.dimasukimas.dto.UserResponseDto;
import com.dimasukimas.service.RegistrationService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
@RequestMapping("/sign-up")
public class RegistrationController {

    private final RegistrationService registrationService;
    private final static String HOME_PAGE_REDIRECT = "redirect:/";


    @GetMapping
    public String signUp() {
        return "sign-up";
    }

    @PostMapping
    public String signUp(Model model,
                         HttpServletResponse response,
                         HttpSession session,
                         @Valid UserRequestDto userRequestDto) {

        UserResponseDto userInfo = registrationService.registerUser(userRequestDto);
        response.addCookie(userInfo.cookie());
        session.setAttribute("user", userInfo.login());

        return HOME_PAGE_REDIRECT;
    }

}
