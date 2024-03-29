package com.geeksforless.station.web.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;



public record TicketDto(String firstName, String lastName,Integer routeId, Integer departureStation, Integer arrivalStation,
                        BigDecimal price, LocalDate tripDate) implements Serializable {
}