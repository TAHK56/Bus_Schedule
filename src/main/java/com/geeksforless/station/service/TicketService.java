package com.geeksforless.station.service;

import com.geeksforless.station.persistence.entity.ticket.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketService {

    List<Ticket> findAll();


    Optional<Ticket> findById(int ticketId);

    Ticket createTicket(Ticket ticket);

    int findFreeSeats(Integer routeId, Integer departureStationId, Integer arrivalStationId, String tripDate);
}
