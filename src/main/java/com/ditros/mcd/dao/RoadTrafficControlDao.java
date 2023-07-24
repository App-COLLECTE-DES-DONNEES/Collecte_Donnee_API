package com.ditros.mcd.dao;

import com.ditros.mcd.model.entity.AlcoholTestResult;
import com.ditros.mcd.model.entity.RoadTrafficControl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoadTrafficControlDao extends JpaRepository<RoadTrafficControl, Long> {
    List<RoadTrafficControl> findByLang(String lang);

}
