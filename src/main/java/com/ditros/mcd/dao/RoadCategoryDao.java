package com.ditros.mcd.dao;

import com.ditros.mcd.model.entity.AlcoholTestResult;
import com.ditros.mcd.model.entity.RoadCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoadCategoryDao extends JpaRepository<RoadCategory, Long> {
    List<RoadCategory> findByLang(String lang);

}
