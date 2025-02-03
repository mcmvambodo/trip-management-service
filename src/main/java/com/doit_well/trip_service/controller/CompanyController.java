package com.doit_well.trip_service.controller;

import com.doit_well.trip_service.entity.company.CompanyDto;
import com.doit_well.trip_service.entity.company.CreateCompanyRequest;
import com.doit_well.trip_service.utils.AppConstants;
import com.doit_well.trip_service.service.ICompanyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AppConstants.BASE_PATH+"/company")
@AllArgsConstructor
public class CompanyController {

    private ICompanyService service;

    @PostMapping
    public ResponseEntity<CompanyDto> create(@RequestBody CreateCompanyRequest companyRequest){
        return new ResponseEntity<>(service.createCompany(companyRequest), HttpStatus.CREATED);
    }
}
