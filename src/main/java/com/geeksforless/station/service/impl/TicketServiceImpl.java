package com.geeksforless.station.service.impl;

import com.geeksforless.station.persistence.entity.ticket.Ticket;
import com.geeksforless.station.persistence.repository.ticket.TicketRepository;
import com.geeksforless.station.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;


    @Override
    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }


    @Override
    public Optional<Ticket> findById(int ticketId) {
        return ticketRepository.findById(ticketId);
    }

    @Override
    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public int findFreeSeats(Integer routeId, Integer departureStationId, Integer arrivalStationId, String tripDate) {
        return ticketRepository.countTicketsByTripDateAndRouteAndDepartureStationAndArrivalStation(routeId,
                departureStationId, arrivalStationId, tripDate);
    }
}
