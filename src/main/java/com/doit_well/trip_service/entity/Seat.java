package com.doit_well.trip_service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Seat extends Comfort{
    private Integer seatNumber;
}
