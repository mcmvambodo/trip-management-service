package com.doit_well.trip_service.service;

import com.doit_well.trip_service.entity.trip.CreateTripRequest;
import com.doit_well.trip_service.entity.trip.Trip;
import com.doit_well.trip_service.entity.trip.TripDto;

import java.util.List;

public interface ITripService {
    List<TripDto> searchTrip(String departureCode, String destinationCode, String departureDate);
    TripDto createTrip(CreateTripRequest request);
    Trip findTripById(Integer id);
    void updateSeats(Integer tripId, Integer bookedPlaces);
}
