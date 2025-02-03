package com.doit_well.trip_service.entity.customer;

import com.doit_well.trip_service.entity.trip.Trip;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Table(name = "passenger")
@Entity
@AllArgsConstructor
@Data
public class Passenger
{
    @Id
    @GeneratedValue
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String tel;
    private String address;
    private String country;
    private String city;
    private String profileImage;
    private String cniNumber;
    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;
}
