package com.doit_well.trip_service.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public record Address
    (String country,
    String city,
    String street,
    String houseNumber,
    Integer postalCode)
    {}
