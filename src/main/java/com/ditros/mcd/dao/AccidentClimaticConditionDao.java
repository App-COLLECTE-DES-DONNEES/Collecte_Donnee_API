package com.ditros.mcd.dao;

import com.ditros.mcd.model.entity.AccidentClimaticCondition;
import com.ditros.mcd.model.entity.AlcoholTestResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccidentClimaticConditionDao extends JpaRepository<AccidentClimaticCondition, Long> {
    List<AccidentClimaticCondition> findByLang(String lang);

}
