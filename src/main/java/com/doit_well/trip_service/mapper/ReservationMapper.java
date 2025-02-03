package com.doit_well.trip_service.mapper;

import com.doit_well.trip_service.entity.customer.CreateCustomerRequest;
import com.doit_well.trip_service.entity.reservation.ChargeRequest;
import com.doit_well.trip_service.entity.reservation.Reservation;
import com.doit_well.trip_service.entity.reservation.ReservationDto;
import com.doit_well.trip_service.entity.reservation.ReservationRequest;
import org.mapstruct.Mapper;

@Mapper
public interface ReservationMapper {

    ReservationDto toDto(Reservation reservation);
    CreateCustomerRequest toCustomerRequest(ReservationRequest reservation);
    ChargeRequest toChargeRequest(ReservationRequest reservation);
}
