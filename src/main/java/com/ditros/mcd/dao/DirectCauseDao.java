package com.ditros.mcd.dao;

import com.ditros.mcd.model.entity.AlcoholTestResult;
import com.ditros.mcd.model.entity.DirectCause;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DirectCauseDao extends JpaRepository<DirectCause, Long> {
    List<DirectCause> findByLang(String lang);

}
