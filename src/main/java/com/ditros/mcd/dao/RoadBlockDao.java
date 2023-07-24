package com.ditros.mcd.dao;

import com.ditros.mcd.model.entity.AlcoholTestResult;
import com.ditros.mcd.model.entity.RoadBlock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoadBlockDao extends JpaRepository<RoadBlock, Long> {
    List<RoadBlock> findByLang(String lang);

}
