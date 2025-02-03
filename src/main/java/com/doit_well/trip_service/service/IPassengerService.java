package com.doit_well.trip_service.service;

import com.doit_well.trip_service.entity.customer.CreateCustomerRequest;
import com.doit_well.trip_service.entity.customer.Passenger;

public interface IPassengerService {
    Passenger createPassenger(CreateCustomerRequest request);
    Passenger findPassengerById(Integer id);
}
