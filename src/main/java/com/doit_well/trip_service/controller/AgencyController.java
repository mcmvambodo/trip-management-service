package com.doit_well.trip_service.controller;

import com.doit_well.trip_service.entity.agency.AgencyDto;
import com.doit_well.trip_service.entity.agency.CreateAgencyRequest;
import com.doit_well.trip_service.utils.AppConstants;
import com.doit_well.trip_service.service.IAgencyService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AppConstants.BASE_PATH+"/agency")
@AllArgsConstructor
public class AgencyController {

    @Autowired
    private IAgencyService service;

    @PostMapping
    public ResponseEntity<AgencyDto> createAgency(@RequestBody CreateAgencyRequest agencyRequest){
        return new ResponseEntity<>(service.createAgency(agencyRequest), HttpStatus.CREATED);
    }

}
