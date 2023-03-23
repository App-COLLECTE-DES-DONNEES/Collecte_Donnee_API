package com.ditros.mcd.dao;

import com.ditros.mcd.model.entity.AlcoholTestResult;
import com.ditros.mcd.model.entity.AlcoholTestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlcoholTestStatusDao extends JpaRepository<AlcoholTestStatus, Long> {
    List<AlcoholTestStatus> findByLang(String lang);

}
