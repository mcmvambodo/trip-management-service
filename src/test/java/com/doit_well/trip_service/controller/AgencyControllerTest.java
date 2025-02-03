package com.doit_well.trip_service.controller;

import com.doit_well.trip_service.db.Database;
import com.doit_well.trip_service.entity.agency.AgencyDto;
import com.doit_well.trip_service.entity.agency.CreateAgencyRequest;
import com.doit_well.trip_service.utils.AppConstants;
import com.doit_well.trip_service.service.AgencyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AgencyController.class)
@ActiveProfiles("test")
public class AgencyControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;
    private ObjectMapper mapper;

    @MockBean
    private AgencyService service;

    @BeforeEach
    void setup(){
        mapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
    }

    @Test
    void givenAgencyRequest_whenCreateAgency_thenReturnAgencyOK() throws Exception {

        // given
        CreateAgencyRequest agency = Database.createAgency;
        AgencyDto agencyDto = Database.agencyDto;


        // wwhen
        when(service.createAgency(agency)).thenReturn(agencyDto);

        // then
        Map<String,String> headersMap = new HashMap<>();
        headersMap.put("Authorization","");
        HttpHeaders headers = new HttpHeaders();
        headers.setAll(headersMap);

        mockMvc.perform(post(AppConstants.BASE_PATH+"/agency")
                        .content(mapper.writeValueAsString(agency))
                        .headers(headers)
                        .characterEncoding("utf-8")
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.name",is("Aeroport International de Yaounde Nsimalen")));

        verify(service,times(1)).createAgency(agency);
    }
}
