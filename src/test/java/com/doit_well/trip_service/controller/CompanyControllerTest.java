package com.doit_well.trip_service.controller;

import com.doit_well.trip_service.db.Database;
import com.doit_well.trip_service.entity.company.CompanyDto;
import com.doit_well.trip_service.entity.company.CreateCompanyRequest;
import com.doit_well.trip_service.utils.AppConstants;
import com.doit_well.trip_service.service.CompanyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import static org.hamcrest.Matchers.is;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ActiveProfiles("test")
@WebMvcTest(CompanyController.class)
public class CompanyControllerTest {

    @Autowired
    private WebApplicationContext context;
    @MockBean
    private CompanyService service;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    @Test
    void givenCreateCompanyRequest_whenCreateCompany_thenReturnCompanyCreated() throws Exception {

        CreateCompanyRequest company = Database.createCompanyRequest;
        CompanyDto companyDto = Database.companyDto;

        when(service.createCompany(company)).thenReturn(companyDto);

        Map<String,String> headersMap = new HashMap<>();
        headersMap.put("Authorization","");
        HttpHeaders headers = new HttpHeaders();
        headers.setAll(headersMap);

        mockMvc.perform(post(AppConstants.BASE_PATH+"/company")
                .content(mapper.writeValueAsString(company))
                .headers(headers)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.name",is("Buca Voyage")));
    }
}
