package com.doit_well.trip_service.entity.trip;

import com.doit_well.trip_service.entity.agency.Agency;
import com.doit_well.trip_service.entity.car.Car;
import com.doit_well.trip_service.entity.customer.Passenger;
import com.doit_well.trip_service.entity.driver.Driver;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Table(name = "trips")
@Entity
@AllArgsConstructor
@Data
public class Trip {
    @Id
    @GeneratedValue
    private Integer id;
    private String departure;
    private String destination;
    private LocalDateTime dateOfTrip;
    private LocalDateTime createdAt;
    private Double price;
    private String fares;
    private Integer seats;
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;
    private String stops;
    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;
    @ManyToOne
    @JoinColumn(name = "agency_id")
    private Agency agency;

    public Trip(){
        this.createdAt = LocalDateTime.now(ZoneId.of("Europe/Paris"));
    }

    public void calculateSeats(Integer bookedPlaces){
        this.seats -= bookedPlaces;
    }
}
