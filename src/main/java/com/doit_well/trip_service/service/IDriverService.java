package com.doit_well.trip_service.service;

import com.doit_well.trip_service.entity.driver.CreateDriverRequest;
import com.doit_well.trip_service.entity.driver.Driver;
import com.doit_well.trip_service.entity.driver.DriverDto;

public interface IDriverService {
    DriverDto createDriver(CreateDriverRequest driverRequest);
    Driver findDriverById(Integer id);
}
