package com.geeksforless.station.service.impl;

import com.geeksforless.station.persistence.entity.schedule.Schedule;
import com.geeksforless.station.persistence.repository.schedule.ScheduleRepository;
import com.geeksforless.station.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository repository;

    @Override
    public Optional<Schedule> findById(Integer integer) {
        return repository.findById(integer);
    }

    @Override
    public List<Schedule> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Schedule> findByIdStationAndRoute(Integer routeId, Integer stationId) {
        return repository.findByRouteIdAndStationId(routeId, stationId);
    }
}
