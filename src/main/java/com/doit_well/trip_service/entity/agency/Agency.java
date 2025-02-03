package com.doit_well.trip_service.entity.agency;

import com.doit_well.trip_service.entity.Address;
import com.doit_well.trip_service.entity.company.Company;
import com.doit_well.trip_service.entity.trip.Trip;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "agency")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Agency {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(unique = true)
    private String code;
    private String name;
    private String description;
    private String image;
    @Embedded
    private Address address;
    @ManyToOne(optional = false)
    @JoinColumn(name = "company_id")
    private Company company;
    @OneToMany(mappedBy = "agency")
    private List<Trip> trips;
}
