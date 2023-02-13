package com.ditros.mcd.dao;

import com.ditros.mcd.model.entity.PersonAccident;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonAccidentDao  extends JpaRepository<PersonAccident, Long> {
}
