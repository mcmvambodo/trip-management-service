package com.doit_well.trip_service.controller;

import com.doit_well.trip_service.entity.reservation.ReservationDto;
import com.doit_well.trip_service.entity.reservation.ReservationRequest;
import com.doit_well.trip_service.service.IReservationService;
import com.doit_well.trip_service.utils.AppConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AppConstants.BASE_PATH + "/reservation")
@AllArgsConstructor
public class ReservationController {

    private IReservationService service;
    @PostMapping
    public ResponseEntity<ReservationDto> bookTrip(@RequestBody ReservationRequest request) throws JsonProcessingException {
        return new ResponseEntity<>(service.processReservation(request), HttpStatus.CREATED);
    }
}
