package com.ditros.mcd.dao;

import com.ditros.mcd.model.entity.AlcoholTestResult;
import com.ditros.mcd.model.entity.PersonGender;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonGenderDao extends JpaRepository<PersonGender, Long> {
    List<PersonGender> findByLang(String lang);
}
