package com.ditros.mcd.dao;

import com.ditros.mcd.model.entity.AlcoholTestResult;
import com.ditros.mcd.model.entity.VehicleAction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleActionDao extends JpaRepository<VehicleAction, Long> {
    List<VehicleAction> findByLang(String lang);

}
