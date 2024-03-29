package com.geeksforless.station.persistence.entity.schedule;

import com.geeksforless.station.persistence.entity.bus.Bus;
import com.geeksforless.station.persistence.entity.geography.Station;
import com.geeksforless.station.persistence.entity.ticket.Ticket;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "route")
@NoArgsConstructor
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RouteId", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "BusId", nullable = false)
    private Bus bus;

    @Column(name = "Name", nullable = false, length = 160, unique = true)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "DepartureStation", nullable = false)
    private Station departureStation;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ArrivalStation", nullable = false)
    private Station arrivalStation;

    @Column(name = "DepartureTime", nullable = false)
    private LocalTime departureTime;

    @Column(name = "ArrivalTime", nullable = false)
    private LocalTime arrivalTime;

    @OneToMany(mappedBy = "route")
    private Set<Schedule> schedules = new LinkedHashSet<>();

    @OneToMany(mappedBy = "route")
    private Set<Ticket> tickets = new LinkedHashSet<>();

}