package com.geeksforless.station.service;

import com.geeksforless.station.persistence.entity.schedule.Route;

import java.util.List;
import java.util.Optional;

public interface RouteService {

    List<Route> findAll();

    List<Route> findRoutesByDepartureStationAndArrivalStation(String departure, String arrival, String date);

    Route createRoute(Route route);

    Optional<Route> findById(Integer id);
}
