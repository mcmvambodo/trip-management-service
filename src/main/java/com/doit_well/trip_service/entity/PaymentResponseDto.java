package com.doit_well.trip_service.entity;

public record PaymentResponseDto(
        String stripeEmail,
        String stripeToken,
        Reservationstatus status
) {
}
