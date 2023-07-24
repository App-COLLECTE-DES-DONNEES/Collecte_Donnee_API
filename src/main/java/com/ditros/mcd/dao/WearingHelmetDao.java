package com.ditros.mcd.dao;

import com.ditros.mcd.model.entity.AlcoholTestResult;
import com.ditros.mcd.model.entity.WearingHelmet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WearingHelmetDao extends JpaRepository<WearingHelmet, Long> {
    List<WearingHelmet> findByLang(String lang);

}
