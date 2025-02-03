package com.doit_well.trip_service.controller;

import com.doit_well.trip_service.entity.car.CarDto;
import com.doit_well.trip_service.entity.car.CreateCarRequest;
import com.doit_well.trip_service.utils.AppConstants;
import com.doit_well.trip_service.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AppConstants.BASE_PATH+"/car")
@AllArgsConstructor
public class CarController {

    private CarService service;
    @PostMapping
    public ResponseEntity<CarDto> create(@RequestBody CreateCarRequest carRequest){
        return new ResponseEntity<>(service.createCar(carRequest), HttpStatus.CREATED);
    }
}
