package com.geeksforless.station.persistence.repository.schedule;

import com.geeksforless.station.persistence.entity.schedule.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route, Integer> {

        @Query(nativeQuery = true, value = """
                WITH geography AS (SELECT LocationId,
                                            StationId,
                                            Location.Name AS Location
                                     FROM Location
                                              INNER JOIN Station USING (LocationId)),
                routes AS (
                    SELECT  Route.*,
                    g1.Location AS Begin,
                    g3.Location AS Bus_Stop,
                    g2.Location AS Finish,
                    s1.DepartureTime AS DepartureTime_Stop
                    FROM Route
                    INNER JOIN geography AS g1 ON g1.StationId = Route.DepartureStation
                    INNER JOIN geography AS g2 ON g2.StationId = Route.ArrivalStation
                    INNER JOIN Schedule AS s1 ON s1.RouteId = Route.RouteId
                    LEFT JOIN geography AS g3 ON s1.StationId = g3.StationId)
                SELECT DISTINCT RouteId, BusId, Name, DepartureStation, ArrivalStation, DepartureTime, ArrivalTime FROM routes
                    WHERE ((Begin = :departure OR Bus_Stop = :departure) AND (Finish = :arrival OR Bus_Stop = :arrival))
                    AND  CURRENT_TIMESTAMP < :date
                    ORDER BY  DepartureTime
                  """)
        List<Route> findRoutesByDepartureStationAndArrivalStationSortedByDate(String departure, String arrival, String date);
}