package com.doit_well.trip_service.service;

import com.doit_well.trip_service.entity.Address;
import com.doit_well.trip_service.entity.company.Company;
import com.doit_well.trip_service.entity.company.CompanyDto;
import com.doit_well.trip_service.entity.company.CreateCompanyRequest;
import com.doit_well.trip_service.exception.CompanyException;
import com.doit_well.trip_service.mapper.CompanyMapper;
import com.doit_well.trip_service.repository.CompanyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CompanyService implements ICompanyService {

    private CompanyRepository companyRepository;
    private CompanyMapper mapper;


    public CompanyDto createCompany(CreateCompanyRequest companyRequest) {
        if (companyRequest == null) {
            throw new CompanyException("Company is null");
        }
        Address address = mapper.toAddress(companyRequest);
        Company company = mapper.toCompany(companyRequest);
        company.setAddress(address);
        return mapper.toDto(companyRepository.save(company));
    }

    @Override
    public CompanyDto findCompany(Integer id) {
        var company = findCompanyById(id);
        return mapper.toDto(company);
    }

    @Override
    public Company findCompanyById(Integer id) {
        return companyRepository.findById(id).orElseThrow(
                () -> new CompanyException(String.format("Company with id %s not found", id))
        );
    }
}
