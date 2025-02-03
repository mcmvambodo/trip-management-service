package com.doit_well.trip_service.entity.customer;

import com.doit_well.trip_service.entity.User;
import com.doit_well.trip_service.entity.reservation.Reservation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Set;


@Data
@AllArgsConstructor
public class Customer extends User {
    @Column(nullable = false)
    private String tel;
    private String country = "Cameroon";
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String address;
    private String profileImage;
    @Column(nullable = false)
    private String cniNumber;
    private String cniImage;
    private LocalDateTime createdAt;
    private Boolean hasAccount;
    @OneToMany(mappedBy = "customer")
    private Set<Reservation> reservations;

    public Customer(){
        createdAt = LocalDateTime.now(ZoneId.of("ECT"));
    }
}
