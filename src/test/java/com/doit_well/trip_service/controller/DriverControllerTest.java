package com.doit_well.trip_service.controller;

import com.doit_well.trip_service.db.Database;
import com.doit_well.trip_service.entity.driver.CreateDriverRequest;
import com.doit_well.trip_service.entity.driver.DriverDto;
import com.doit_well.trip_service.utils.AppConstants;
import com.doit_well.trip_service.service.DriverService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DriverController.class)
public class DriverControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private DriverService service;

    @Test
    void givenDriverRequest_whenCreateDriver_thenReturnDriverCreated() throws Exception {

        // given
        CreateDriverRequest driverRequest = Database.createDriverRequest;
        DriverDto driverDto = Database.driverDto;

        Map<String,String> headersMap = new HashMap<>();
        headersMap.put("Authorization","");
        HttpHeaders headers = new HttpHeaders();
        headers.setAll(headersMap);

        // when
        when(service.createDriver(driverRequest)).thenReturn(driverDto);

        // then
        mockMvc.perform(post(AppConstants.BASE_PATH + "/driver")
                .content(mapper.writeValueAsString(driverRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers)
        ).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.firstname",is("Romeo")));
    }
}
