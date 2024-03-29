package com.geeksforless.station.web.controller;

import com.geeksforless.station.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final LocationService locationService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("locations", locationService.findAllLocation());
        return "index";
    }
}
