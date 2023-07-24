package com.ditros.mcd.dao;

import com.ditros.mcd.model.entity.PersonAction;
import com.ditros.mcd.model.entity.PersonAlcoholConsumption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonAlcoholConsumptionDao extends JpaRepository<PersonAlcoholConsumption, Long> {
    List<PersonAlcoholConsumption> findByLang(String lang);
}
