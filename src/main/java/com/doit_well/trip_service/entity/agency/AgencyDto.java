package com.doit_well.trip_service.entity.agency;

public record AgencyDto(
        Integer id,
        String code,
        String name,
        String description,
        String image,
        String country,
        String city,
        String street,
        String houseNumber,
        Integer postalCode,
        String companyName
) {
}
