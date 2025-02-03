package com.doit_well.trip_service.entity.customer;

import lombok.Builder;

@Builder
public record CreateCustomerRequest(
         String firstname,
         String lastname,
         String email,
         String password,
         String tel,
         String address,
         String country ,
         String city,
         String cniNumber,
         Boolean createAccount
) {
}
