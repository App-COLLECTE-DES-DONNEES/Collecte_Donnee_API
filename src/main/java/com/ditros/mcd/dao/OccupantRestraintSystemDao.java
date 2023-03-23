package com.ditros.mcd.dao;

import com.ditros.mcd.model.entity.AlcoholTestResult;
import com.ditros.mcd.model.entity.OccupantRestraintSystem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OccupantRestraintSystemDao extends JpaRepository<OccupantRestraintSystem, Long> {
    List<OccupantRestraintSystem> findByLang(String lang);
}
