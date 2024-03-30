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
                     routes AS (SELECT *
                                FROM schedule
                                         INNER JOIN geography USING (StationId)
                                WHERE Location IN (:departure, :arrival) AND schedule.DepartureTime < :date)
                SELECT DISTINCT route.*
                FROM route
                WHERE RouteId IN (SELECT RouteId FROM routes)
                ORDER BY DepartureTime;
                  """)
        List<Route> findRoutesByDepartureStationAndArrivalStationSortedByDate(String departure, String arrival, String date);
}