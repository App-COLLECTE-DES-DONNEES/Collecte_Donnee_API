package com.ditros.mcd.dao;

import com.ditros.mcd.model.entity.AlcoholTestResult;
import com.ditros.mcd.model.entity.RoadType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoadTypeDao extends JpaRepository<RoadType, Long> {
    List<RoadType> findByLang(String lang);

}
