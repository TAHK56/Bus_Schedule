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
                                                  INNER JOIN schedule AS s1 ON ticket.ScheduleArrivalStationId =\s
                                                  s1.ScheduleId
                                                  INNER JOIN schedule AS s2 ON ticket.ScheduleDepartureStationId =\s
                                                  s2.ScheduleId
                                         WHERE ticket.RouteId = :routeId
                                           AND (s2.OrderRoute < (SELECT OrderTop FROM topBorder)
                                             AND s1.OrderRoute < (SELECT OrderBottom FROM bottomBorder)
                                             )),
                     allSalesTickets AS (
                SELECT RouteId, (bus.Seats - SoldSeats) + (SELECT SeatsAvailable FROM seatsBecomeFree) AS FreeSeats,
                        TripDate, bus.Seats
                FROM bus
                         JOIN (SELECT BusId, RouteId, COUNT(TicketId) AS SoldSeats, TripDate
                               FROM route
                                        LEFT JOIN ticket USING (RouteId)
                               WHERE RouteId = :routeId
                               GROUP BY RouteId, TripDate, BusId) AS t USING (BusId)
                ORDER BY RouteId)
                SELECT IF(TripDate = :tripDate, FreeSeats, Seats) AS FreeSeats FROM allSalesTickets
                ORDER BY FreeSeats
                LIMIT 1;
            """)
    int countTicketsByTripDateAndRouteAndDepartureStationAndArrivalStation(Integer routeId, Integer departureStationId,
                                                                           Integer arrivalStationId, String tripDate);
}