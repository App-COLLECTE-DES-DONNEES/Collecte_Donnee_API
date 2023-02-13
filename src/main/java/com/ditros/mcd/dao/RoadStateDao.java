package com.ditros.mcd.dao;

import com.ditros.mcd.model.entity.AlcoholTestResult;
import com.ditros.mcd.model.entity.RoadState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoadStateDao extends JpaRepository<RoadState, Long> {
    List<RoadState> findByLang(String lang);

}
