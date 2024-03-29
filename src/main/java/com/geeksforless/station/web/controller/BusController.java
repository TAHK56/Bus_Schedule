package com.geeksforless.station.web.controller;

import com.geeksforless.station.persistence.entity.bus.Bus;
import com.geeksforless.station.service.BusService;
import com.geeksforless.station.web.dto.BusDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/buses")
public class BusController {

    private final BusService busService;

    @GetMapping("list")
    public String getAllBuses(Model model) {
        model.addAttribute("buses", busService.findAll());
        return "buses/list";
    }

    @GetMapping("create")
    public String getNewBusPage() {
        return "buses/new_bus";
    }

    @PostMapping("create")
    public String createBus(BusDto busDto) {
        Bus bus = new Bus();
        bus.setModel(busDto.model());
        bus.setBusNumber(busDto.busNumber());
        bus.setSeats(busDto.seats());
        busService.createBus(bus);
        return "redirect:/buses/%d".formatted(bus.getId());
    }

    @GetMapping("{busId:\\d+}")
    public String getBus(@PathVariable("busId")  int busId, Model model) {
        Bus bus = busService.findById(busId).orElse(null);
        model.addAttribute("bus", bus);
        return "buses/bus";
    }
}
