package com.doit_well.trip_service.controller;

import com.doit_well.trip_service.entity.driver.CreateDriverRequest;
import com.doit_well.trip_service.entity.driver.DriverDto;
import com.doit_well.trip_service.utils.AppConstants;
import com.doit_well.trip_service.service.DriverService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AppConstants.BASE_PATH+"/driver")
@AllArgsConstructor
public class DriverController {

    private DriverService service;

    @PostMapping
    public ResponseEntity<DriverDto> create(@RequestBody CreateDriverRequest driverRequest){
        return new ResponseEntity<>(service.createDriver(driverRequest), HttpStatus.CREATED);
    }
}
