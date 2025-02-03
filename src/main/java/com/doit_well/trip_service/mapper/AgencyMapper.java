package com.doit_well.trip_service.mapper;

import com.doit_well.trip_service.entity.Address;
import com.doit_well.trip_service.entity.agency.Agency;
import com.doit_well.trip_service.entity.agency.AgencyDto;
import com.doit_well.trip_service.entity.agency.CreateAgencyRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface AgencyMapper {

    Agency toAgency(CreateAgencyRequest AgencyRequest);
    Address toAddress(CreateAgencyRequest AgencyRequest);
    @Mapping(source = "address.country",target = "country")
    @Mapping(source = "address.city",target = "city")
    @Mapping(source = "address.street",target = "street")
    @Mapping(source = "address.houseNumber",target = "houseNumber")
    @Mapping(source = "address.postalCode",target = "postalCode")
    @Mapping(source = "company.name",target = "companyName")
    AgencyDto toDto(Agency Agency);
    Agency toAgency(AgencyDto AgencyDto);
}
