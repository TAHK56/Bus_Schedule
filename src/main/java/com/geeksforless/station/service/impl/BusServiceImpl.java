package com.geeksforless.station.service.impl;

import com.geeksforless.station.persistence.entity.bus.Bus;
import com.geeksforless.station.persistence.repository.bus.BusRepository;
import com.geeksforless.station.service.BusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BusServiceImpl implements BusService {

    private final BusRepository busRepository;

    @Override
    public Bus createBus(Bus bus) {
        return busRepository.save(bus);
    }

    @Override
    public List<Bus> findAll() {
        return busRepository.findAll();
    }

    @Override
    public Optional<Bus> findById(Integer id) {
        return busRepository.findById(id);
    }
}
