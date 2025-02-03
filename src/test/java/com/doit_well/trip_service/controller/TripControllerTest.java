package com.doit_well.trip_service.controller;

import com.doit_well.trip_service.db.Database;
import com.doit_well.trip_service.entity.trip.CreateTripRequest;
import com.doit_well.trip_service.entity.trip.TripDto;
import com.doit_well.trip_service.utils.AppConstants;
import com.doit_well.trip_service.service.TripService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@WebMvcTest(TripController.class)
public class TripControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private TripService service;

    @Test
    void givenCreateTripRequest_whenCreateTrip_theReturnTripDtoCreated() throws Exception {

        // given
        CreateTripRequest tripRequest = Database.createTripRequest;
        TripDto tripDto = Database.tripDto;

        Map<String,String> headersMap = new HashMap<>();
        headersMap.put("Authorization","");
        HttpHeaders headers = new HttpHeaders();
        headers.setAll(headersMap);

        // when
        when(service.createTrip(tripRequest)).thenReturn(tripDto);

        // then
        mockMvc.perform(post(AppConstants.BASE_PATH + "/trip")
                .content(mapper.writeValueAsString(tripRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers)
        ) .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.departure",is("Yaounde")))
                .andExpect(jsonPath("$.companyName",is("Buca Voyage")));

        verify(service,times(1)).createTrip(tripRequest);

    }
}
