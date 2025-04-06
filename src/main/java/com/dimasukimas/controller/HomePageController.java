package com.dimasukimas.controller;

import com.dimasukimas.dto.UserLocationResponseDto;
import com.dimasukimas.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomePageController {

    private final LocationService locationService;

    @GetMapping
    public String homePage(Model model,
                           @CookieValue(name = "userCookie", required = false) UUID sessionId) {
        if (sessionId != null) {

            List<UserLocationResponseDto> userLocations = locationService.getUserLocations(sessionId);
            model.addAttribute("userLocations", userLocations);

        }
        return "index";
    }

}
