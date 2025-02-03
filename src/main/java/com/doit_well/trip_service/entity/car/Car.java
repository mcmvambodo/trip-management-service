package com.doit_well.trip_service.entity.car;

import com.doit_well.trip_service.entity.trip.Trip;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "car")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Car {
    @Id
    @GeneratedValue
    private Integer id;
    private String brand;
    private String model;
    private CarType type;
    private Integer numberOfSeat;
    private String licenseNumber;
    private Integer mileAge;
    private Integer manufacturingYear;
    private String agencyCode;
    @OneToMany(mappedBy = "car")
    private List<Trip> trips;
}
