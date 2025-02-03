package com.doit_well.trip_service.service;

import com.doit_well.trip_service.entity.agency.Agency;
import com.doit_well.trip_service.entity.agency.AgencyDto;
import com.doit_well.trip_service.entity.agency.CreateAgencyRequest;

public interface IAgencyService {
    AgencyDto createAgency(CreateAgencyRequest agencyRequest);
    Agency findAgencyByCode(String code);
    Agency findAgencyById(Integer id);
}
