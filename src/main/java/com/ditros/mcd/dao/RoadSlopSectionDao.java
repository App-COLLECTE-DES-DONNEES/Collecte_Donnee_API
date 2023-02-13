package com.ditros.mcd.dao;

import com.ditros.mcd.model.entity.AlcoholTestResult;
import com.ditros.mcd.model.entity.RoadSlopSection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoadSlopSectionDao extends JpaRepository<RoadSlopSection, Long> {
    List<RoadSlopSection> findByLang(String lang);

}
