package com.ditros.mcd.dao;

import com.ditros.mcd.model.entity.AccidentSeverity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccidentSeverityDao extends JpaRepository<AccidentSeverity, Long> {
    List<AccidentSeverity> findByLang(String lang);
}
