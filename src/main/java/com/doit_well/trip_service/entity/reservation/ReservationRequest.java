package com.doit_well.trip_service.entity.reservation;

public record ReservationRequest(
        Integer tripId,
        Integer numberOfPlaces,
        String firstname,
        String lastname,
        String email,
        String tel,
        String address,
        String country ,
        String city,
        String cniNumber,
        Boolean createAccount,
        String description,
        Integer amount,
        ChargeRequest.Currency currency,
        String stripeEmail,
        String stripeToken

) {
}
