package com.doit_well.trip_service.entity.reservation;

import com.doit_well.trip_service.entity.Comfort;
import com.doit_well.trip_service.entity.Reservationstatus;
import com.doit_well.trip_service.entity.trip.Trip;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Set;

@Entity
@Table
@AllArgsConstructor
@Builder
@Data
public class Reservation {
    @Id
    @GeneratedValue
    private Integer id;
    private String referenceNumber;
    private LocalDateTime createdAt;
    private Reservationstatus status;
    @ManyToOne
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;
    private Integer customerId;
    private Set<Comfort> comforts;
    private String billEmail;

    public Reservation(){
        this.createdAt = LocalDateTime.now(ZoneId.of("Europe/Paris"));
    }
}
