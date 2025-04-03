package com.dimasukimas.controller;

import com.dimasukimas.dto.LocationDto;
import com.dimasukimas.service.WeatherService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/search")
@RequiredArgsConstructor
public class LocationSearchController {

    private final WeatherService weatherService;

    @GetMapping
    public String search(Model model, @RequestParam("location") @NotBlank String location) {

        List<LocationDto> locations = weatherService.findLocationByName(location);
        model.addAttribute("locations", locations);

        return "search-results";
    }

    @PostMapping
    public String add(Model model, @NotBlank String location){

    }

}
