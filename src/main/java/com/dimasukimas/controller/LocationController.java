package com.dimasukimas.controller;


import com.dimasukimas.dto.request.LocationAddDto;
import com.dimasukimas.dto.request.LocationDeleteDto;
import com.dimasukimas.dto.response.BaseLocationDto;
import com.dimasukimas.service.LocationService;
import com.dimasukimas.util.UserUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/locations")
@RequiredArgsConstructor
public class LocationController {

    private final static String HOME_PAGE_REDIRECT = "redirect:/";
    private final static String SEARCH_RESULTS = "search-results";
    private final LocationService locationService;

    @GetMapping("/search")
    public String searchLocation(Model model,
                                 @RequestParam("location") @NotBlank String location,
                                 @CookieValue(name = "userCookie", required = false) UUID sessionId,
                                 HttpServletRequest request
    ) {

        List<? extends BaseLocationDto> locations =
                sessionId != null
                        ? locationService.findLocation(location, sessionId)
                        : locationService.findLocation(location);

        UserUtils.extractUserLogin(request)
                .ifPresent(login -> model.addAttribute("userLogin", login));

        model.addAttribute("locations", locations);

        return SEARCH_RESULTS;
    }

    @PostMapping("/add")
    public String addLocation(@CookieValue("userCookie") UUID sessionId,
                              RedirectAttributes redirectAttributes,
                              HttpServletRequest request,
                              LocationAddDto dto) {
        locationService.addLocation(sessionId, dto);

        return redirectHomeWithUserLogin(request, redirectAttributes);
    }

    @DeleteMapping("/delete")
    public String deleteLocation(@CookieValue("userCookie") UUID sessionId,
                                 RedirectAttributes redirectAttributes,
                                 HttpServletRequest request,
                                 LocationDeleteDto dto) {

        locationService.deleteLocation(sessionId, dto);

        return redirectHomeWithUserLogin(request, redirectAttributes);
    }

    private String redirectHomeWithUserLogin(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        addUserLoginToRedirect(request, redirectAttributes);

        return HOME_PAGE_REDIRECT;
    }

    private void addUserLoginToRedirect(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        UserUtils.extractUserLogin(request)
                .ifPresent(login -> redirectAttributes.addFlashAttribute("userLogin", login));
    }
}
