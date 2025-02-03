package com.doit_well.trip_service.mapper;

import com.doit_well.trip_service.entity.Address;
import com.doit_well.trip_service.entity.company.Company;
import com.doit_well.trip_service.entity.company.CompanyDto;
import com.doit_well.trip_service.entity.company.CreateCompanyRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CompanyMapper {
        Company toCompany(CreateCompanyRequest companyRequest);
        Address toAddress(CreateCompanyRequest companyRequest);

       @Mapping(source = "address.country",target = "country")
       @Mapping(source = "address.city",target = "city")
       @Mapping(source = "address.street",target = "street")
       @Mapping(source = "address.houseNumber",target = "houseNumber")
       @Mapping(source = "address.postalCode",target = "postalCode")
        CompanyDto toDto(Company company);
        CompanyDto toDto(Address address);
        Company toCompany(CompanyDto companyDto);
}
