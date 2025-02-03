package com.doit_well.trip_service.service;

import com.doit_well.trip_service.entity.reservation.ReservationDto;
import com.doit_well.trip_service.entity.reservation.ReservationRequest;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface IReservationService {

    ReservationDto processReservation(ReservationRequest request) throws JsonProcessingException;
}
