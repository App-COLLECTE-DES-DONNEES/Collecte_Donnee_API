package com.ditros.mcd.dao;

import com.ditros.mcd.model.entity.AlcoholTestResult;
import com.ditros.mcd.model.entity.VehicleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleModelDao extends JpaRepository<VehicleModel, Long> {
    List<VehicleModel> findByLang(String lang);

}
