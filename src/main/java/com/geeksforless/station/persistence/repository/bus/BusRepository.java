package com.geeksforless.station.persistence.repository.bus;

import com.geeksforless.station.persistence.entity.bus.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusRepository extends JpaRepository<Bus, Integer> {

}