package com.doit_well.trip_service.service;

import com.doit_well.trip_service.entity.Address;
import com.doit_well.trip_service.entity.agency.Agency;
import com.doit_well.trip_service.entity.agency.AgencyDto;
import com.doit_well.trip_service.entity.agency.CreateAgencyRequest;
import com.doit_well.trip_service.entity.company.Company;
import com.doit_well.trip_service.exception.AgencyException;
import com.doit_well.trip_service.exception.CarException;
import com.doit_well.trip_service.mapper.AgencyMapper;
import com.doit_well.trip_service.repository.AgencyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AgencyService implements IAgencyService {

    private AgencyRepository repository;
    private AgencyMapper mapper;
    private ICompanyService companyService;

    @Override
    public AgencyDto createAgency(CreateAgencyRequest agencyRequest) {
        if (agencyRequest == null) {
            throw new AgencyException("Agency request is null");
        }
        Company company = companyService.findCompanyById(agencyRequest.companyId());

        Address address = mapper.toAddress(agencyRequest);
        Agency agency = mapper.toAgency(agencyRequest);
        agency.setAddress(address);
        agency.setCompany(company);

        return mapper.toDto(repository.save(agency));
    }

    @Override
    public Agency findAgencyByCode(String code) {
        return repository.findByCode(code).orElseThrow(() -> new AgencyException(String.format("No Agency with code %s", code)));
    }

    @Override
    public Agency findAgencyById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new CarException(String.format("No Agency with id %s", id)));
    }
}
