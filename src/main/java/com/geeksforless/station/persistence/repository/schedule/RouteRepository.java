package com.geeksforless.station.persistence.repository.schedule;

import com.geeksforless.station.persistence.entity.schedule.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route, Integer> {

        @Query(nativeQuery = true, value = """
                WITH departureArrive AS (SELECT s1.RouteId,
                                                s2.StationId AS DepartureStation,
                                                s1.StationId AS ArrivalStation,
                                                s2.DepartureTime,
                                                s1.ArrivalTime
                                         FROM schedule AS s1
                                                  INNER JOIN schedule AS s2 ON s1.RouteId = s2.RouteId
                                         WHERE s2.StationId = :departure
                                           AND s1.StationId = :arrival),
                     concreteRoute AS (SELECT route.RouteId,
                                              BusId,
                                              Name,
                                              departureArrive.DepartureStation,
                                              departureArrive.ArrivalStation,
                                              departureArrive.DepartureTime,
                                              departureArrive.ArrivalTime
                                       FROM route
                                                INNER JOIN departureArrive USING (RouteId))
                SELECT RouteId, BusId, Name, DepartureStation, ArrivalStation, DepartureTime, ArrivalTime,\s
                TIMESTAMPDIFF(SECOND, DepartureTime, CURRENT_TIMESTAMP) AS diff
                FROM concreteRoute
                ORDER BY IF(CURRENT_DATE = :date, diff, DepartureTime);
                  """)
        List<Route> findRoutesByDepartureStationAndArrivalStationSortedByDate(Integer departure, Integer arrival, String date);
}