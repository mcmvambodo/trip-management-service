package com.doit_well.trip_service.entity.trip;

import java.time.LocalDateTime;

public record TripSearchRequest(String departureCode, String destinationCode, LocalDateTime departureDate, LocalDateTime returnDate) {
}
