package com.geeksforless.station.persistence.entity.geography;

import com.geeksforless.station.persistence.entity.schedule.Route;
import com.geeksforless.station.persistence.entity.schedule.Schedule;
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
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "station")
@NoArgsConstructor
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "StationId", nullable = false)
    private Integer id;

    @Column(name = "Name", nullable = false, length = 160)
    private String name;

    @Column(name = "Street", nullable = false, length = 160)
    private String street;

    @Column(name = "NumberStreet", nullable = false, length = 160)
    private String numberStreet;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "LocationId", nullable = false)
    private Location location;

    @OneToMany(mappedBy = "arrivalStation")
    private Set<Route> routesArrival = new LinkedHashSet<>();

    @OneToMany(mappedBy = "station")
    private Set<Schedule> schedules = new LinkedHashSet<>();

    @OneToMany(mappedBy = "departureStation")
    private Set<Route> routesDeparture = new LinkedHashSet<>();

}