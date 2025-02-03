package com.doit_well.trip_service.entity;

import com.doit_well.trip_service.entity.reservation.ChargeRequest;

public record PaymentRequest(
        Integer tripId,
        String description,
        Double amount,
        ChargeRequest.Currency currency,
        String stripeEmail,
        String stripeToken
) {
}
