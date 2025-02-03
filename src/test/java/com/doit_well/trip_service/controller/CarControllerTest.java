package com.doit_well.trip_service.controller;

import com.doit_well.trip_service.db.Database;
import com.doit_well.trip_service.entity.car.CarDto;
import com.doit_well.trip_service.entity.car.CreateCarRequest;
import com.doit_well.trip_service.utils.AppConstants;
import com.doit_well.trip_service.service.CarService;
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

@WebMvcTest(CarController.class)
@ActiveProfiles("test")
public class CarControllerTest {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;
    private ObjectMapper mapper;
    @MockBean
    private CarService service;

    @BeforeEach
    void setup(){
        mapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
    }

    @Test
    void givenCreateCarRequest_whenCreateCar_thenReturnCarCreated() throws Exception {

        // given
        CreateCarRequest carRequest = Database.createCar;
        CarDto carDto = Database.carDto;

        Map<String,String> headersMap = new HashMap<>();
        headersMap.put("Authorization","");
        HttpHeaders headers = new HttpHeaders();
        headers.setAll(headersMap);

        // when
        when(service.createCar(carRequest)).thenReturn(carDto);

        // then
        mockMvc.perform(post(AppConstants.BASE_PATH+"/car")
                        .content(mapper.writeValueAsString(carRequest))
                        .headers(headers)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.brand",is("Toyota")));

        verify(service,times(1)).createCar(carRequest);
    }
}
