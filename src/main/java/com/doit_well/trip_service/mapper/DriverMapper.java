package com.doit_well.trip_service.mapper;

import com.doit_well.trip_service.entity.Address;
import com.doit_well.trip_service.entity.driver.CreateDriverRequest;
import com.doit_well.trip_service.entity.driver.Driver;
import com.doit_well.trip_service.entity.driver.DriverDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface DriverMapper {
    Driver toDriver(CreateDriverRequest DriverRequest);
    Address toAddress(CreateDriverRequest DriverRequest);
    @Mapping(source = "address.country",target = "country")
    @Mapping(source = "address.city",target = "city")
    @Mapping(source = "address.street",target = "street")
    @Mapping(source = "address.houseNumber",target = "houseNumber")
    @Mapping(source = "address.postalCode",target = "postalCode")
    DriverDto toDto(Driver Driver);
    Driver toDriver(DriverDto DriverDto);
}
