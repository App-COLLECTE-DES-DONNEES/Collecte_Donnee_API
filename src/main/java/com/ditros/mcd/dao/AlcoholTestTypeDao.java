package com.ditros.mcd.dao;

import com.ditros.mcd.model.entity.AlcoholTestResult;
import com.ditros.mcd.model.entity.AlcoholTestType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlcoholTestTypeDao extends JpaRepository<AlcoholTestType, Long> {
    List<AlcoholTestType> findByLang(String lang);

}
