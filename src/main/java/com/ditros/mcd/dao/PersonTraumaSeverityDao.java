package com.ditros.mcd.dao;

import com.ditros.mcd.model.entity.AlcoholTestResult;
import com.ditros.mcd.model.entity.PersonTraumaSeverity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonTraumaSeverityDao extends JpaRepository<PersonTraumaSeverity, Long> {
    List<PersonTraumaSeverity> findByLang(String lang);

}
