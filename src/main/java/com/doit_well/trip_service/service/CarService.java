package com.doit_well.trip_service.service;

import com.doit_well.trip_service.entity.car.Car;
import com.doit_well.trip_service.entity.car.CarDto;
import com.doit_well.trip_service.entity.car.CreateCarRequest;
import com.doit_well.trip_service.exception.CarException;
import com.doit_well.trip_service.mapper.CarMapper;
import com.doit_well.trip_service.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CarService implements ICarService{

    private CarRepository repository;
    private CarMapper mapper;

    @Override
    public CarDto createCar(CreateCarRequest carRequest) {
        if ( carRequest == null){
            throw new CarException("Car request is null");
        }
        Car car = mapper.toCar(carRequest);
        return mapper.toDto(repository.save(car));
    }

    @Override
    public Car findCarById(Integer id) {
        return repository.findById(id).orElseThrow(()-> new CarException(String.format("No Car with id %s", id)));
    }
}
