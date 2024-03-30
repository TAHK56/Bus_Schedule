package com.geeksforless.station.web.controller;

import com.geeksforless.station.persistence.entity.ticket.Ticket;
import com.geeksforless.station.persistence.entity.user.Customer;
import com.geeksforless.station.service.CustomerService;
import com.geeksforless.station.service.RouteService;
import com.geeksforless.station.service.ScheduleService;
import com.geeksforless.station.service.StationService;
import com.geeksforless.station.service.TicketService;
import com.geeksforless.station.web.dto.TicketDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Instant;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;
    private final RouteService routeService;
    private final ScheduleService scheduleService;
    private final StationService stationService;
    private final CustomerService customerService;

    @GetMapping("list")
    public String getAllTickets(Model model) {
        model.addAttribute("tickets", ticketService.findAll());
        return "tickets/list";
    }

    @PostMapping("fromRoute/create/new_ticket")
    public String getNewTicketPage(@RequestParam("routeId") int routeId, Model model) {
        model.addAttribute("routeId", routeId);
        model.addAttribute("schedules", scheduleService.findAllRouteStopsInOrder(routeId));
        return "tickets/new_ticket";
    }

    @PostMapping("create")
    public String createTicket(TicketDto ticketDto) {
        Ticket ticket = new Ticket();
        Customer customer = new Customer();
        customer.setFirstName(ticketDto.firstName());
        customer.setLastName(ticketDto.lastName());

        ticket.setRoute(routeService.findById(ticketDto.routeId()).orElse(null));
        ticket.setScheduleDepartureStation(scheduleService.findByIdStationAndRoute(ticketDto.routeId(),
                ticketDto.departureStation()).orElse(null));
        ticket.setScheduleArrivalStation(scheduleService.findByIdStationAndRoute(ticketDto.routeId(),
                ticketDto.arrivalStation()).orElse(null));
        ticket.setSalesTime(Instant.now());
        ticket.setPrice(ticketDto.price());
        ticket.setTripDate(ticketDto.tripDate());
        ticket.setStatus(Boolean.TRUE);
        ticket.setUser(customerService.createCustomer(customer));
        ticketService.createTicket(ticket);
        return "redirect:/tickets/%d".formatted(ticket.getId());
    }

    @GetMapping("{ticketId:\\d+}")
    public String getTicket(@PathVariable("ticketId")  int ticketId, Model model) {
        Ticket ticket = ticketService.findById(ticketId).orElse(null);
        model.addAttribute("ticket", ticket);
        return "tickets/ticket";
    }
}
