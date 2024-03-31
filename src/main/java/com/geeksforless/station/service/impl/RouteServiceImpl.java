package com.geeksforless.station.service.impl;

import com.geeksforless.station.persistence.entity.schedule.Route;
import com.geeksforless.station.persistence.repository.schedule.RouteRepository;
import com.geeksforless.station.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;

    @Override
    public List<Route> findAll() {
        return Collections.unmodifiableList(routeRepository.findAll());
    }

    @Override
    public List<Route> findRoutesByDepartureStationAndArrivalStation(Integer departure, Integer arrival, String date) {
        return routeRepository.findRoutesByDepartureStationAndArrivalStationSortedByDate(departure, arrival, date);
    }

    @Override
    public Route createRoute(Route route) {
        return routeRepository.save(route);
    }

    @Override
    public Optional<Route> findById(Integer id) {
        return routeRepository.findById(id);
    }
}
