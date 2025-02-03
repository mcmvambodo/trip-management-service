package com.doit_well.trip_service.entity.reservation;

import lombok.Data;

@Data
public class ChargeRequest {


    public enum Currency {
        EUR, USD;
    }
    private String description;
    private int amount;
    private Currency currency;
    private String stripeEmail;
    private String stripeToken;
}
