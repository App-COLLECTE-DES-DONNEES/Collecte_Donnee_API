package com.ditros.mcd.dao;

import com.ditros.mcd.model.entity.AlcoholTestResult;
import com.ditros.mcd.model.entity.Virage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VirageDao extends JpaRepository<Virage, Long> {
    List<Virage> findByLang(String lang);

}
