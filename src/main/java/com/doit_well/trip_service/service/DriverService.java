package com.doit_well.trip_service.service;

import com.doit_well.trip_service.entity.Address;
import com.doit_well.trip_service.entity.driver.CreateDriverRequest;
import com.doit_well.trip_service.entity.driver.Driver;
import com.doit_well.trip_service.entity.driver.DriverDto;
import com.doit_well.trip_service.exception.DriverException;
import com.doit_well.trip_service.mapper.DriverMapper;
import com.doit_well.trip_service.repository.DriverRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DriverService implements IDriverService {
    private DriverRepository repository;
    private DriverMapper mapper;

    @Override
    public DriverDto createDriver(CreateDriverRequest driverRequest) {
        if (driverRequest == null) throw new DriverException("Driver is null");

        Address address = mapper.toAddress(driverRequest);
        Driver driver = mapper.toDriver(driverRequest);
        driver.setAddress(address);

        return mapper.toDto(repository.save(driver));
    }

    @Override
    public Driver findDriverById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new DriverException(String.format("No Driver with id %s", id)));
    }
}
