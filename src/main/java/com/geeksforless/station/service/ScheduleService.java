package com.geeksforless.station.service;

import com.geeksforless.station.persistence.entity.schedule.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleService {
    Optional<Schedule> findById(Integer integer);

    List<Schedule> findAll();

    Optional<Schedule> findByIdStationAndRoute(Integer routeId, Integer stationId);

    List<Schedule> findAllRouteStopsInOrder(Integer routeId);
}
