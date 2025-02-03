package com.doit_well.trip_service.entity.trip;

import java.time.LocalDateTime;

public record TripDto 
    ( Integer id,
     String departure,
     String destination,
     LocalDateTime dateOfTrip,
     Double price,
     String companyName,
     Integer seats,
     String fares,
     String stops,
      String agencyName,
      String licenseNumber
    )
    {}
