package com.doit_well.trip_service.repository;

import com.doit_well.trip_service.entity.agency.Agency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgencyRepository extends JpaRepository<Agency,Integer> {
    Optional<Agency> findByCode(String code);
}
