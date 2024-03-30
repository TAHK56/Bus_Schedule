package com.geeksforless.station.persistence.repository.schedule;

import com.geeksforless.station.persistence.entity.schedule.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

    @Query(nativeQuery = true, value = """
    SELECT * FROM schedule
    WHERE StationId = :stationId AND RouteId = :routeId
""")
    Optional<Schedule> findByRouteIdAndStationId(Integer routeId, Integer stationId);

    @Query(nativeQuery = true, value = """
    SELECT * FROM schedule
    WHERE RouteId = :routeId
    ORDER BY OrderRoute
""")
    List<Schedule> findSchedulesByRouteIdAndOrderByOrderRoute(Integer routeId);

}