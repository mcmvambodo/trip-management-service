package com.doit_well.trip_service.entity.trip;

public enum TripFare {
    LIGHT("ECONOMY LIGHT"), // rebooking cost a certain price, no refund, 1 bag, Meal
    FLEX("ECONOMY FLEX"); // // rebooking free,refund, 3 bag, Meal

    TripFare(String value){
    }
}
