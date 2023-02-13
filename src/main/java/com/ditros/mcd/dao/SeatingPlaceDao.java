package com.ditros.mcd.dao;

import com.ditros.mcd.model.entity.AlcoholTestResult;
import com.ditros.mcd.model.entity.SeatingPlace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatingPlaceDao extends JpaRepository<SeatingPlace, Long> {
    List<SeatingPlace> findByLang(String lang);

}
