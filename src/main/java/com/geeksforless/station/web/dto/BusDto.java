package com.geeksforless.station.web.dto;

import java.io.Serializable;

public record BusDto(String model, String busNumber, Integer seats) implements Serializable {
}