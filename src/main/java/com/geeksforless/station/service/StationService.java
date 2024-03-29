package com.geeksforless.station.service;

import com.geeksforless.station.persistence.entity.geography.Station;

import java.util.List;
import java.util.Optional;

public interface StationService {
    Optional<Station> findById(Integer integer);

    List<Station> findAllRouteStation(Integer routeId);
}
