package com.geeksforless.station.service;


import com.geeksforless.station.persistence.entity.bus.Bus;

import java.util.List;
import java.util.Optional;

public interface BusService {

    Bus createBus(Bus bus);

    List<Bus> findAll();

    Optional<Bus> findById(Integer id);
}
