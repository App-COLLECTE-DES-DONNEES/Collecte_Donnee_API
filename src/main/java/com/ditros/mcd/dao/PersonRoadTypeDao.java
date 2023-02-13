package com.ditros.mcd.dao;

import com.ditros.mcd.model.entity.AlcoholTestResult;
import com.ditros.mcd.model.entity.PersonRoadType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRoadTypeDao extends JpaRepository<PersonRoadType, Long> {
    List<PersonRoadType> findByLang(String lang);

}
