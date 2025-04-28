package com.dimasukimas.controller;

import com.dimasukimas.dto.response.WeatherDto;
import com.dimasukimas.entity.User;
import com.dimasukimas.service.LocationService;
import jakarta.servlet.http.HttpServletRequest;
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
public class HomeController {

    private final LocationService locationService;
    private final static String HOME_PAGE = "index";

    @GetMapping
    public String home(Model model,
                       @CookieValue(name = "userCookie", required = false) UUID sessionId,
                       HttpServletRequest request) {

        if (sessionId != null) {
            List<WeatherDto> userLocations = locationService.getUserLocations(sessionId);
            model.addAttribute("userLocations", userLocations);
        }
        return HOME_PAGE;
    }

}
