package com.doit_well.trip_service.client;

import com.doit_well.trip_service.entity.customer.CreateCustomerRequest;
import com.doit_well.trip_service.entity.customer.CustomerDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange
public interface CustomerClient {


    @PostExchange("/api/v1/customer")
    public CustomerDto createCustomer(@RequestBody CreateCustomerRequest request);
}
