package com.ditros.mcd.dao;

import com.ditros.mcd.model.entity.AlcoholTestResult;
import com.ditros.mcd.model.entity.SeatingRange;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatingRangeDao extends JpaRepository<SeatingRange, Long> {
    List<SeatingRange> findByLang(String lang);

}
