package com.lld.automobile.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lld.automobile.db.entity.VehicleStation;

@Repository
public interface VehicleStationRepository extends JpaRepository<VehicleStation, String> {

}
