package com.doit_well.trip_service.controller;

import com.doit_well.trip_service.entity.reservation.ReservationDto;
import com.doit_well.trip_service.entity.trip.CreateTripRequest;
import com.doit_well.trip_service.entity.trip.TripDto;
import com.doit_well.trip_service.utils.AppConstants;
import com.doit_well.trip_service.service.TripService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.apache.kafka.common.requests.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AppConstants.BASE_PATH+"/trip")
@AllArgsConstructor
@Tag(name = "Trip", description = "Search for a trip and proceed to reservation")
public class TripController {

        private TripService service;

        @GetMapping
        @Operation(summary = "search for a trip",
                description = "search for a trip",
                responses = {
                        @ApiResponse(responseCode = "200", description = "The list of available trips",
                                content = @io.swagger.v3.oas.annotations.media.Content(
                                        mediaType = "application/json",
                                        array = @io.swagger.v3.oas.annotations.media.ArraySchema(
                                                schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = TripDto.class)
                                        )
                                )
                        ),
                        @ApiResponse(responseCode = "500", description = "Internal Server Error",
                                content = @io.swagger.v3.oas.annotations.media.Content(
                                        schema = @io.swagger.v3.oas.annotations.media.Schema(
                                                implementation = ApiError.class
                                        )
                                )
                        )
                }
        )
        public ResponseEntity<List<TripDto>> lookForTrips(
                @RequestParam String departure,
                @RequestParam String destination,
                @RequestParam String departureDate
        ){
                return ResponseEntity.ok(service.searchTrip(departure,destination,departureDate));
        }
        @PostMapping
        @Operation(summary = "Book a trip",
                description = "Book a trip",
                responses = {
                        @ApiResponse(responseCode = "201", description = "Reservation succeeded",
                                content = @io.swagger.v3.oas.annotations.media.Content(
                                        mediaType = "application/json",
                                        schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ReservationDto.class)
                                )),
                        @ApiResponse(responseCode = "500", description = "Internal Server Error",
                                content = @io.swagger.v3.oas.annotations.media.Content(
                                        schema = @io.swagger.v3.oas.annotations.media.Schema(
                                                implementation = ApiError.class
                                        )
                                )
                        )
                },
                requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                        required = true,
                        content = @io.swagger.v3.oas.annotations.media.Content(
                                mediaType = "application/json",
                                schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = CreateTripRequest.class)
                        )
                )
        )
        public ResponseEntity<TripDto> processBooking(@RequestBody CreateTripRequest tripRequest){
            return new ResponseEntity<>(service.createTrip(tripRequest), HttpStatus.CREATED);
        }
}
