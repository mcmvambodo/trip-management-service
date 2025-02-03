package com.doit_well.trip_service.entity.car;


public record CreateCarRequest(
        String brand,
        String model,
        CarType type,
        Integer numberOfSeat,
        String licenseNumber,
        Integer mileAge,
        Integer manufacturingYear,
        String agencyCode
) {
}
