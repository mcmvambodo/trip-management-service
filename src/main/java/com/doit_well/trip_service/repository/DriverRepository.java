package com.doit_well.trip_service.repository;

import com.doit_well.trip_service.entity.driver.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver,Integer> {
}
