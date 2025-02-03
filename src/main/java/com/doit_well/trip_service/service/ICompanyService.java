package com.doit_well.trip_service.service;

import com.doit_well.trip_service.entity.company.Company;
import com.doit_well.trip_service.entity.company.CompanyDto;
import com.doit_well.trip_service.entity.company.CreateCompanyRequest;

public interface ICompanyService {
    CompanyDto createCompany(CreateCompanyRequest company);
    CompanyDto findCompany(Integer id);
    Company findCompanyById(Integer id);
}
