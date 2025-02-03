package com.doit_well.trip_service.entity.trip;


public record CreateTripRequest(
         String departure,
         String destination,
         String dateOfTrip,
         Double price,
         String fares,
         String stops,
         Integer seats,
         Integer carId,
         Integer driverId,
         Integer agencyId
) {
}
