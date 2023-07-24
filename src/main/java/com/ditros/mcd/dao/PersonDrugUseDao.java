package com.ditros.mcd.dao;

import com.ditros.mcd.model.entity.AlcoholTestResult;
import com.ditros.mcd.model.entity.PersonDrugUse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonDrugUseDao extends JpaRepository<PersonDrugUse, Long> {
    List<PersonDrugUse> findByLang(String lang);

}
