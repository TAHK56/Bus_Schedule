package com.geeksforless.station.web.controller;

import com.geeksforless.station.persistence.entity.schedule.Route;
import com.geeksforless.station.service.RouteService;
import com.geeksforless.station.service.ScheduleService;
import com.geeksforless.station.web.dto.RouteDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/routes")
public class RouteController {

    private final RouteService routeService;
    private final ScheduleService scheduleService;

    @GetMapping("list")
    public String getRoutesBySearch(@RequestParam String from, @RequestParam String to, @RequestParam(required = false)
    String date, Model model) {
        model.addAttribute("from", from);
        model.addAttribute("to", to);
        model.addAttribute("date", date);
        model.addAttribute("routes", routeService.findRoutesByDepartureStationAndArrivalStation(from, to, date));
        return "routes/list";
    }

    @GetMapping
    public String getAllRoutes(Model model) {
        model.addAttribute("routes", routeService.findAll());
        return "routes/list";
    }

    @GetMapping("create")
    public String getNewRoute() {
        return "routes/new_route";
    }

    @PostMapping("create")
    public String createRoute(RouteDto routeDto) {
        return "redirect:/routes";
    }

    @GetMapping("{routeId:\\d+}")
    public String getRoute(@PathVariable("routeId") int routeId, Model model) {
        Route route = routeService.findById(routeId).orElse(null);
        model.addAttribute("route", route);
        model.addAttribute("schedules", scheduleService.findAllRouteStopsInOrder(routeId));
        return "routes/route";
    }

}
