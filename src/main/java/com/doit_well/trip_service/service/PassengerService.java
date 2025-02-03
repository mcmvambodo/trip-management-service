package com.doit_well.trip_service.service;

import com.doit_well.trip_service.entity.customer.CreateCustomerRequest;
import com.doit_well.trip_service.entity.customer.Passenger;
import com.doit_well.trip_service.exception.PassengerException;
import com.doit_well.trip_service.mapper.PassengerMapper;
import com.doit_well.trip_service.repository.PassengerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PassengerService implements IPassengerService {

    private PassengerRepository repository;
    private PassengerMapper mapper;

    @Override
    public Passenger createPassenger(CreateCustomerRequest request) {
        if (request == null) throw new PassengerException("The request is null");
        Passenger passenger = mapper.toPassenger(request);
        return repository.save(passenger);
    }

    @Override
    public Passenger findPassengerById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new PassengerException(String.format("No passenger found with id %s", id)));
    }
}
