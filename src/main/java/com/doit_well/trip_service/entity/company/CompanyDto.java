package com.doit_well.trip_service.entity.company;

public record CompanyDto(
        Integer id,
        String name,
        String description,
        String image,
        String country,
        String city,
        String street,
        String houseNumber,
        Integer postalCode
        // To simplify we don't fetch the list of agencies
        // We could to it here in the future or with sql request
) {
}
