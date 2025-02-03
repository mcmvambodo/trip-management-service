package com.doit_well.trip_service.mapper;

import com.doit_well.trip_service.entity.customer.CreateCustomerRequest;
import com.doit_well.trip_service.entity.customer.Passenger;
import org.mapstruct.Mapper;

@Mapper
public interface PassengerMapper {
    Passenger toPassenger(CreateCustomerRequest request);
}
