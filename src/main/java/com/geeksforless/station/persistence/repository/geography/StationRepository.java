package com.geeksforless.station.persistence.repository.geography;

import com.geeksforless.station.persistence.entity.geography.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StationRepository extends JpaRepository<Station, Integer> {
    @Query(nativeQuery = true, value = """
    SELECT DISTINCT station.StationId, station.Name, station.Street, station.NumberStreet, station.LocationId, route.RouteId FROM station
    INNER JOIN schedule USING (StationId)
    INNER  JOIN route USING (RouteId)
    WHERE RouteId = :routeId
""")
    List<Station> findByIdRoute(Integer routeId);

}