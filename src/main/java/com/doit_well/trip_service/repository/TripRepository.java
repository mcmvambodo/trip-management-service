package com.doit_well.trip_service.repository;

import com.doit_well.trip_service.entity.trip.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TripRepository extends JpaRepository<Trip, Integer> {
    @Query("SELECT t FROM Trip t where t.stops like %?1% and t.stops like %?2% and t.dateOfTrip >= ?3")
    List<Trip> findByStopsLikeAndStopsLikeAndDateOfTripGreaterThanEqual(String departure, String destination, LocalDateTime dateOfTrip);
}
