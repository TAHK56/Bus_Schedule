package com.geeksforless.station.web.dto;

import java.io.Serializable;


public record RouteDto(String busNumber, String routeName, String departureStation,
                       String  departureTime, String arrivalTime) implements Serializable {
}
