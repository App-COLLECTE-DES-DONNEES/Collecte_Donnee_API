package com.ditros.mcd.dao;

import com.ditros.mcd.model.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleDao extends JpaRepository<Vehicle, Long> {
}
