package com.ditros.mcd.dao;

import com.ditros.mcd.model.entity.AccidentImpactType;
import com.ditros.mcd.model.entity.AlcoholTestResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccidentImpactTypeDao extends JpaRepository<AccidentImpactType,Long> {
    List<AccidentImpactType> findByLang(String lang);
}
