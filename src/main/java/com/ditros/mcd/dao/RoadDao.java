package com.ditros.mcd.dao;

import com.ditros.mcd.model.entity.AlcoholTestResult;
import com.ditros.mcd.model.entity.Road;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoadDao extends JpaRepository<Road, Long> {
    List<Road> findByLang(String lang);

}
