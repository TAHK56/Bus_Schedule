package com.geeksforless.station.persistence.repository.ticket;

import com.geeksforless.station.persistence.entity.ticket.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    @Query(nativeQuery = true, value = """
                WITH topBorder AS (SELECT OrderRoute AS OrderTop
                                   FROM schedule
                                   WHERE StationId = :departureStationId
                                     AND RouteId = :routeId),
                     bottomBorder AS (SELECT OrderRoute AS OrderBottom
                                      FROM schedule
                                      WHERE StationId = :arrivalStationId
                                        AND RouteId = :routeId),
                     seatsBecomeFree AS (SELECT COUNT(TicketId) AS SeatsAvailable
                                         FROM ticket
                                                  INNER JOIN schedule AS s1 ON ticket.ScheduleArrivalStationId = s1.ScheduleId
                                                  INNER JOIN schedule AS s2 ON ticket.ScheduleDepartureStationId = s2.ScheduleId
                                         WHERE ticket.RouteId = :routeId
                                           AND (s2.OrderRoute < (SELECT OrderTop FROM topBorder)
                                             AND s1.OrderRoute < (SELECT OrderBottom FROM bottomBorder)
                                             ))
                SELECT RouteId, (bus.Seats - SoldSeats) + (SELECT SeatsAvailable FROM seatsBecomeFree) AS FreeSeats
                FROM bus
                         JOIN (SELECT route.BusId, route.RouteId, COUNT(TicketId) AS SoldSeats
                               FROM route
                                        LEFT JOIN ticket USING (RouteId)
                               WHERE TripDate = :tripDate
                               GROUP BY RouteId) AS t USING (BusId)
                ORDER BY RouteId
                
            """)
    int countTicketsByTripDateAndRouteAndDepartureStationAndArrivalStation(Integer routeId, Integer departureStationId,
                                                                           Integer arrivalStationId, String tripDate);
}