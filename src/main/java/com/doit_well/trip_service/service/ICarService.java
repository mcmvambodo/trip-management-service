package com.doit_well.trip_service.service;

import com.doit_well.trip_service.entity.car.Car;
import com.doit_well.trip_service.entity.car.CarDto;
import com.doit_well.trip_service.entity.car.CreateCarRequest;

public interface ICarService {
    CarDto createCar(CreateCarRequest car);
    Car findCarById(Integer id);

}
