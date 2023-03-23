package com.ditros.mcd.dao;

import com.ditros.mcd.model.entity.AccidentSeverity;
import com.ditros.mcd.model.entity.AccidentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccidentTypeDao extends JpaRepository<AccidentType, Long> {
    List<AccidentType> findByLang(String lang);
}
