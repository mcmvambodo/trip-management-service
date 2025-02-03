package com.doit_well.trip_service.entity.company;

public record CreateCompanyRequest(
        String name,
        String description,
        String image,
        String country,
        String city,
        String street,
        String houseNumber,
        Integer postalCode
) {
}
