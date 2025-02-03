package com.doit_well.trip_service.entity.reservation;

import com.doit_well.trip_service.entity.Comfort;
import com.doit_well.trip_service.entity.Reservationstatus;
import java.util.Set;

public record ReservationDto (
        Integer id,
        String referenceNumber,
        Reservationstatus status,
        Integer customerId
){
}
