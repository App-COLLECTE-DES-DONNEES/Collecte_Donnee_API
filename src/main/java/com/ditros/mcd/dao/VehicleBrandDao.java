package com.ditros.mcd.dao;

import com.ditros.mcd.model.entity.AlcoholTestResult;
import com.ditros.mcd.model.entity.VehicleBrand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleBrandDao extends JpaRepository<VehicleBrand, Long> {
    List<VehicleBrand> findByLang(String lang);

}
