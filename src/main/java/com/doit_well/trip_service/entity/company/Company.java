package com.doit_well.trip_service.entity.company;

import com.doit_well.trip_service.entity.agency.Agency;
import com.doit_well.trip_service.entity.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Company {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String description;
    private String image;
    @Embedded
    private Address address;
    @OneToMany(mappedBy = "company")
    private Set<Agency> agencies;
}
