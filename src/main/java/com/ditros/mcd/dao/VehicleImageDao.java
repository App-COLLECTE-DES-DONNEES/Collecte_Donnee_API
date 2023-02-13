package com.ditros.mcd.dao;

import com.ditros.mcd.model.entity.VehicleImage;
import com.ditros.mcd.model.entity.VehicleModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleImageDao extends JpaRepository<VehicleImage, Long> {
}
