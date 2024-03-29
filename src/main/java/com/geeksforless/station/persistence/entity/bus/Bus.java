package com.geeksforless.station.persistence.entity.bus;

import com.geeksforless.station.persistence.entity.schedule.Route;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "bus")
@NoArgsConstructor
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BusId", nullable = false)
    private Integer id;

    @Column(name = "Model", nullable = false, length = 28)
    private String model;

    @Column(name = "BusNumber", nullable = false, length = 28)
    private String busNumber;

    @Column(name = "Seats", nullable = false)
    private Integer seats;

    @OneToMany(mappedBy = "bus")
    private Set<Route> routes = new LinkedHashSet<>();

}