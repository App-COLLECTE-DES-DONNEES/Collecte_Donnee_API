package com.ditros.mcd.dao;

import com.ditros.mcd.model.entity.AccidentBrightnessCondition;
import com.ditros.mcd.model.entity.AlcoholTestResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccidentBrightnessConditionDao extends JpaRepository<AccidentBrightnessCondition, Long> {
    List<AccidentBrightnessCondition> findByLang(String lang);

}
