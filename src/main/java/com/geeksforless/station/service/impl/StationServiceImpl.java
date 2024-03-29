package com.geeksforless.station.service.impl;

import com.geeksforless.station.persistence.entity.geography.Station;
import com.geeksforless.station.persistence.repository.geography.StationRepository;
import com.geeksforless.station.service.StationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StationServiceImpl implements StationService {

    private final StationRepository stationRepository;
    @Override
    public Optional<Station> findById(Integer integer) {
        return stationRepository.findById(integer);
    }

    @Override
    public List<Station> findAllRouteStation(Integer routeId) {
        return stationRepository.findByIdRoute(routeId);
    }
}
