package com.ditros.mcd.dao;

import com.ditros.mcd.model.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonDao extends JpaRepository<Person, Long> {
}
