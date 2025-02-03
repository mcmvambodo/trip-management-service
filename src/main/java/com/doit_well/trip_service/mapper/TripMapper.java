package com.doit_well.trip_service.mapper;

import com.doit_well.trip_service.entity.trip.CreateTripRequest;
import com.doit_well.trip_service.entity.trip.Trip;
import com.doit_well.trip_service.entity.trip.TripDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface TripMapper {
    @Mapping(source = "car.licenseNumber",target = "licenseNumber")
    @Mapping(source = "agency.name",target = "agencyName")
    @Mapping(source = "agency.company.name",target = "companyName")
    @Mapping(source = "departure",target = "departure")
    @Mapping(source = "destination",target = "destination")
    TripDto toDto(Trip trip);
    Trip toTrip(TripDto dto);
    Trip toTrip(CreateTripRequest request);

}
