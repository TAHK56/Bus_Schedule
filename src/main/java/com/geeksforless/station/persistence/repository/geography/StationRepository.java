package com.geeksforless.station.persistence.repository.geography;

import com.geeksforless.station.persistence.entity.geography.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StationRepository extends JpaRepository<Station, Integer> {
    @Query(nativeQuery = true, value = """
    SELECT StationId, station.Name, Street, NumberStreet, LocationId, RouteId FROM route
    INNER JOIN station  ON station.StationId = route.DepartureStation
    WHERE RouteId = :routeId
    UNION
    SELECT StationId, station.Name, Street, NumberStreet, LocationId, RouteId FROM route
    INNER JOIN station  ON station.StationId = route.ArrivalStation
    WHERE RouteId = :routeId
    UNION
    SELECT station.StationId, station.Name, Street, NumberStreet, LocationId, RouteId FROM station
    INNER JOIN schedule USING (StationId) WHERE RouteId = :routeId
""")
    List<Station> findByIdRoute(Integer routeId);

}