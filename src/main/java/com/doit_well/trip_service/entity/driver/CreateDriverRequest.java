package com.doit_well.trip_service.entity.driver;

public record CreateDriverRequest(
        String firstname,
        String lastname,
        String email,
        String phone,
        String country,
        String city,
        String street,
        String houseNumber,
        Integer postalCode,
        String identificationCardNumber,
        String drivingLicenceNumber,
        String companyName
) {
}
