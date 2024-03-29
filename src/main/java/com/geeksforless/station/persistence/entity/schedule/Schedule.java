package com.geeksforless.station.persistence.entity.schedule;

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
@Table(name = "schedule")
@NoArgsConstructor
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ScheduleId", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "RouteId", nullable = false)
    private Route route;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "StationId", nullable = false)
    private Station station;

    @Column(name = "DepartureTime", nullable = false)
    private LocalTime departureTime;

    @Column(name = "ArrivalTime", nullable = false)
    private LocalTime arrivalTime;

    @OneToMany(mappedBy = "scheduleDepartureStation")
    private Set<Ticket> ticketsDeparture = new LinkedHashSet<>();

    @OneToMany(mappedBy = "scheduleArrivalStation")
    private Set<Ticket> ticketsArrival = new LinkedHashSet<>();

}