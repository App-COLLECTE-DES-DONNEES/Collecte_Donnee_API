package com.ditros.mcd.dao;

import com.ditros.mcd.model.entity.AlcoholTestResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlcoholTestResultDao extends JpaRepository<AlcoholTestResult,Long> {
    List<AlcoholTestResult> findByLang(String lang);
}
