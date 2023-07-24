package com.ditros.mcd.dao;

import com.ditros.mcd.model.entity.AlcoholTestResult;
import com.ditros.mcd.model.entity.VehicleSpecialFunction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleSpecialFunctionDao extends JpaRepository<VehicleSpecialFunction, Long> {
    List<VehicleSpecialFunction> findByLang(String lang);

}
