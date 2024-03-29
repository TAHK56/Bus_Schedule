package com.geeksforless.station.persistence.repository.geography;

import com.geeksforless.station.persistence.entity.geography.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
}