package com.geeksforless.station.service.impl;

import com.geeksforless.station.persistence.entity.geography.Location;
import com.geeksforless.station.persistence.repository.geography.LocationRepository;
import com.geeksforless.station.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;


    @Override
    public List<Location> findAllLocation() {
        return locationRepository.findAll();
    }
}
