package com.geeksforless.station.service;

import com.geeksforless.station.persistence.entity.geography.Location;

import java.util.List;

public interface LocationService {

    List<Location> findAllLocation();
}
