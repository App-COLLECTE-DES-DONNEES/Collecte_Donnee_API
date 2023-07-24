package com.ditros.mcd.dao;

import com.ditros.mcd.model.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryDao  extends JpaRepository<Country, Long> {
}
