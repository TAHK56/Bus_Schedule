package com.geeksforless.station.persistence.repository.ticket;

import com.geeksforless.station.persistence.entity.ticket.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    @Query(nativeQuery = true, value = """
        SELECT COUNT(Status) FROM ticket 
        WHERE Status = true AND RouteId = :routeId AND TripDate = :tripDate
     """)
    int countTicketsByStatus(String routeId, String tripDate);
}