package com.doit_well.trip_service.service;

import com.doit_well.trip_service.utils.AppConstants;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookingService {

    private KafkaTemplate<String, String> kafkaTemplate;

    public boolean updateLoc(String loc){
        kafkaTemplate.send(AppConstants.BUS_LOCATION,loc);
        return true;
    }

}
