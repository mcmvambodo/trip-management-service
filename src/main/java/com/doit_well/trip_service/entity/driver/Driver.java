package com.doit_well.trip_service.entity.driver;

import com.doit_well.trip_service.entity.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Driver{
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Integer id;
        private String firstname;
        private String lastname;
        @Column(unique = true)
        private String email;
        @Column(unique = true)
        private String phone;
        @Embedded
        private Address address;
        private String identificationCardNumber;
        private String drivingLicenceNumber;
        private String companyName;
}
