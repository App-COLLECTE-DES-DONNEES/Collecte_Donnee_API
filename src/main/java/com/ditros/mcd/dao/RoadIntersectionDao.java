package com.ditros.mcd.dao;

import com.ditros.mcd.model.entity.AlcoholTestResult;
import com.ditros.mcd.model.entity.RoadIntersection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoadIntersectionDao extends JpaRepository<RoadIntersection, Long> {
    List<RoadIntersection> findByLang(String lang);

}
