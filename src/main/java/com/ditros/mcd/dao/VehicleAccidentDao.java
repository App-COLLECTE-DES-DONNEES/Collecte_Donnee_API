package com.ditros.mcd.dao;

import com.ditros.mcd.model.entity.Accident;
import com.ditros.mcd.model.entity.VehicleAccident;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VehicleAccidentDao extends JpaRepository<VehicleAccident, Long> {
}
