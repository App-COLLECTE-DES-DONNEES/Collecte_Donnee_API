package com.ditros.mcd.dao;

import com.ditros.mcd.model.entity.AccidentSeverity;
import com.ditros.mcd.model.entity.PersonAction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonActionDao extends JpaRepository<PersonAction,Long> {
    List<PersonAction> findByLang(String lang);
}
