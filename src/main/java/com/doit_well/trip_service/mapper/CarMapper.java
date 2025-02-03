package com.doit_well.trip_service.mapper;

import com.doit_well.trip_service.entity.car.Car;
import com.doit_well.trip_service.entity.car.CarDto;
import com.doit_well.trip_service.entity.car.CreateCarRequest;
import org.mapstruct.Mapper;

@Mapper
public interface CarMapper {
    Car toCar(CreateCarRequest CarRequest);
    CarDto toDto(Car Car);
    Car toCar(CarDto CarDto);
}
